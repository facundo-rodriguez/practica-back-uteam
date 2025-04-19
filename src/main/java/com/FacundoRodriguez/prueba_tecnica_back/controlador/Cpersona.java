
package com.FacundoRodriguez.prueba_tecnica_back.controlador;

import com.FacundoRodriguez.prueba_tecnica_back.dto.RespuestaDTO;
import com.FacundoRodriguez.prueba_tecnica_back.modelo.Pelicula;
import com.FacundoRodriguez.prueba_tecnica_back.modelo.Persona;
import com.FacundoRodriguez.prueba_tecnica_back.servicio.PersonaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FACU
 */

@RestController
@RequestMapping("persona")
public class Cpersona {
    
    private RespuestaDTO respuesta;
    
    //@Autowired
    private PersonaService personaService;
    
    
    public Cpersona(PersonaService personaService){
    
        this.personaService=personaService;
        
        //System.out.println("PersonaService inyectado: " + this.personaService);
    }
    
    
    @GetMapping("/")
    public RespuestaDTO listaPersonas(){
    
        try{
            
            List<Persona> personas=this.personaService.listaPersonas();
        
            this.respuesta=new RespuestaDTO("Lista de personas", 200, personas, null);
        
            return this.respuesta;
            
        }catch(Exception e){
        
            this.respuesta = new RespuestaDTO("Ocurrio un error", 500, null, e.getMessage()); 
            
            return respuesta;
        }

    }
    
    
    @GetMapping("id/{id}")
    public ResponseEntity<RespuestaDTO> buscarPersonaPorId(@PathVariable int id){
        
         try{
             
            Persona persona= this.personaService.buscarPersona(id);
            
            if(persona==null){
                
                this.respuesta=new RespuestaDTO("Persona no encontrada", 404, null, null);
                return ResponseEntity.status(404).body(this.respuesta);
            }
            
            this.respuesta=new RespuestaDTO("Persona encontrada", 200, persona, null);

            return ResponseEntity.status(200).body(this.respuesta);
            
        }catch(Exception e){
        
            this.respuesta = new RespuestaDTO("Ocurrio un error", 500, null, e.getMessage()); 
            
            return ResponseEntity.internalServerError().body(respuesta);
        }
 
    }
    
    
    @GetMapping("nombre/{nombre}")
    public ResponseEntity<RespuestaDTO> buscarPersonaPorNombre(@PathVariable String nombre){
        
         try{
             
            Persona persona= this.personaService.buscarPersona(nombre);
            
            if(persona==null){
                
                this.respuesta=new RespuestaDTO("Persona no encontrada", 404, null, null);
                return ResponseEntity.status(404).body(this.respuesta);
            }
        
            this.respuesta=new RespuestaDTO("Persona encontrada", 200, persona, null);
        
            return ResponseEntity.status(200).body(this.respuesta);
            
        }catch(Exception e){
        
            this.respuesta = new RespuestaDTO("Ocurrio un error", 500, null, e.getMessage()); 
            
            return ResponseEntity.internalServerError().body(respuesta);
        }
 
    }
    
    
    @PostMapping("/")
    public ResponseEntity<RespuestaDTO> crearPersona(@RequestBody  @Valid Persona persona, BindingResult result){
        
        try{
             System.out.println("entro al metodo");
           if (result.hasErrors()) {
     
                this.respuesta=new RespuestaDTO("los datos ingresados no son correctos", 400, result.getAllErrors(), null ); 
                
                return ResponseEntity.badRequest().body(respuesta);
            }
            
            Persona personaCreada=this.personaService.agregarPersona(persona);
            
            this.respuesta = new RespuestaDTO("Persona creada con éxito", 201, personaCreada, null); 

            
            return ResponseEntity.ok(respuesta);
            
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, null, e.getMessage()); 
            
           return ResponseEntity.internalServerError().body(respuesta);
            
        }
    
    }
    
    
    @DeleteMapping("borrar/{id}")
    public ResponseEntity<RespuestaDTO> borrarPersona(@PathVariable int id){
       
        try{
            
           boolean borrado=this.personaService.borrarPersona(id);
        
           if(borrado)return ResponseEntity.ok(new RespuestaDTO("Persona borrada", 204, null, null));
        
           return ResponseEntity.ok(new RespuestaDTO("La persona no se borró", 204, null, null)); 
        
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, null, e.getMessage()); 
            
           return ResponseEntity.internalServerError().body(respuesta);
        }
            
    }
    
    
    
    @PatchMapping("/{id}")
    public ResponseEntity<RespuestaDTO> modificarPersona(@PathVariable int id, @RequestBody Persona persona){
    
        try{
            Persona personaMod=this.personaService.modificarPersona(id, persona);
            
            if(personaMod != null) return ResponseEntity.ok(new RespuestaDTO("persona modificada", 201, personaMod, null));
            
            return ResponseEntity.ok(new RespuestaDTO("persona no modificada", 201, null, null));
        
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, e.getStackTrace(), e.getMessage().toString() ); 
            
           return ResponseEntity.internalServerError().body(respuesta);
        }
    
    }
    
    
    
    @PostMapping("/{id_persona}/peliculas")
    public ResponseEntity<RespuestaDTO> agregarPeliculaPersona(@PathVariable int id_persona, @RequestBody @Valid Pelicula pelicula, BindingResult result){
    
        try{
            
            if (result.hasErrors()) {
     
                this.respuesta=new RespuestaDTO("los datos ingresados no son correctos", 400, result.getAllErrors(), null ); 
                
                return ResponseEntity.badRequest().body(respuesta);
            }
            
            Boolean peliAgregada=this.personaService.agregarPeliculaApersona(id_persona, pelicula);
            
            
            if(peliAgregada == null) {
                return ResponseEntity.status(404).body(new RespuestaDTO("La persona no fue encontrada", 404, null, null));
            }
            
            if(peliAgregada) {
                return ResponseEntity.status(201).body(new RespuestaDTO("Se agregó la película a la persona", 201, true, null));

            }else {
               return ResponseEntity.ok(new RespuestaDTO("No se pudo agregar la película (límite alcanzado o ya existe)", 200, false, null));
            }
            
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, e.getStackTrace(), e.getMessage().toString() ); 
            
           return ResponseEntity.internalServerError().body(respuesta);
        }
    
    }
    
    
    @GetMapping("/{id_persona}/peliculas")
    public ResponseEntity<RespuestaDTO> peliculasPorPersona(@PathVariable int id_persona){
    
       try{
            
            Persona persona= this.personaService.buscarPersona(id_persona);
            
            if(persona==null){
                
                this.respuesta=new RespuestaDTO("Persona no encontrada", 404, null, null);
                return ResponseEntity.status(404).body(this.respuesta);
            }
            
            return ResponseEntity.ok(new RespuestaDTO("Lista de peliculas de " + persona.getFirstName(), 200, persona.getPeliculas(), null));

            
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, e.getStackTrace(), e.getMessage().toString() ); 
            
           return ResponseEntity.internalServerError().body(respuesta);
        }
    
    }
    
    
    
    @DeleteMapping("/{id_persona}/peliculas/{id_pelicula}")
    public ResponseEntity<RespuestaDTO> quitarPeliculaDePersona(@PathVariable int id_persona, @PathVariable int id_pelicula){
       
        try{
        
            Boolean peliQuitada= this.personaService.quitarPeliculaDePersona(id_persona, id_pelicula);

            if(peliQuitada==null){

                this.respuesta=new RespuestaDTO("Persona no encontrada", 404, null, null);
                return ResponseEntity.status(404).body(this.respuesta);
            }

            if(peliQuitada){
                return ResponseEntity.status(201).body(new RespuestaDTO("Se quitó la película de la persona", 201, true, null));

            }else {
                return ResponseEntity.ok(new RespuestaDTO("No se pudo quitar la película (no está en la lista)", 200, false, null));
            }
    
        }catch(Exception e){
            
           this.respuesta = new RespuestaDTO("Ocurrio un error", 500, e.getStackTrace(), e.getMessage().toString() ); 
            
           return ResponseEntity.internalServerError().body(respuesta);
        }
        
        
    }
    
    
    /*
     {
        "id_persona":2,
        "firstName":"Alberto",    
        "lastName":"Pérez",
        "birthdate":"16-12-1995",
        "insurance":true
    }
    
    {
        "id":1,
        "title":"Titanic",
        "genre":"drama"
    }
    
    */
    
}
