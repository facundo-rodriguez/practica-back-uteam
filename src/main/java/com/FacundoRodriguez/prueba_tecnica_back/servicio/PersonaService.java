/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FacundoRodriguez.prueba_tecnica_back.servicio;

import com.FacundoRodriguez.prueba_tecnica_back.modelo.Pelicula;
import com.FacundoRodriguez.prueba_tecnica_back.modelo.Persona;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author FACU
 */

@Service
public class PersonaService {
    
    private List<Persona> personas;
    
    
    public PersonaService(){
        
        this.personas=new ArrayList();
        
        Persona persona1 = new Persona();
        persona1.setId_persona(1);
        persona1.setFirstName("Juan");
        persona1.setLastName("Pérez");
        persona1.setBirthdate(LocalDate.of(1990, 1, 1));
        persona1.setInsurance(true);
        persona1.setLimitPeliculas(3);
        
        this.personas.add(persona1);

    }
    
    
    
    public List<Persona> listaPersonas(){
        
        personas.sort(
        // Comparo por apellido
             Comparator.comparing(Persona::getLastName)
                // Si la comparación anterior resulta en igualdad comparo por nombre
                .thenComparing(Persona::getFirstName)
        );
        
        return this.personas;
    }
    
    public Persona agregarPersona(Persona persona){
    
        Persona nuevaPersona=new Persona();
        nuevaPersona.setId_persona(persona.getId_persona());
        nuevaPersona.setFirstName(persona.getFirstName());
        nuevaPersona.setLastName(persona.getLastName());
        nuevaPersona.setInsurance(persona.getInsurance());
        nuevaPersona.setBirthdate(persona.getBirthdate());
        
        this.personas.add(nuevaPersona);
                
        return nuevaPersona;
    }
    
    public Persona buscarPersona(int id){
        
       return this.personas.stream()
        .filter(p -> p.getId_persona() == id)
        .findFirst()
        .orElse(null);
    
    }
    
    
    public Persona buscarPersona(String nombre){
    
        return this.personas.stream()
                .filter( p -> p.getFirstName().equals(nombre))
                .findFirst()
                .orElse(null);
    
    }
    
    
    public Boolean borrarPersona(int id){
    
        Persona personaBorrar=this.buscarPersona(id);
        return this.personas.remove(personaBorrar);
        
    }
            
    
    public Persona modificarPersona(int id, Persona persona){
    
        Persona personaMod=this.buscarPersona(id);
        
        if(personaMod != null){
            
            if(persona.getFirstName() !=null && !persona.getFirstName().trim().isEmpty() ){
                personaMod.setFirstName(persona.getFirstName().trim());
            }

            if(persona.getLastName() !=null && !persona.getLastName().trim().isEmpty() ){
                personaMod.setLastName(persona.getLastName().trim());
            }

            if(persona.getBirthdate() !=null && !persona.getBirthdate().toString().isEmpty() ){
                personaMod.setBirthdate(persona.getBirthdate());
            }    

            if(persona.getInsurance() !=null ){
                personaMod.setInsurance(persona.getInsurance());
            }
            
            if(persona.getLimitPeliculas() !=null && persona.getLimitPeliculas() > 0 ){
                personaMod.setLimitPeliculas(persona.getLimitPeliculas() );
            }
            
        }
        
        return personaMod;
    }
    
    
    public List<Pelicula> peliculasPorPersona(int id_persona){
    
        Persona persona=this.buscarPersona(id_persona);
        
        if(persona == null) return  Collections.emptyList();;
        
        return persona.getPeliculas();
    
    }
    
    
    public Boolean agregarPeliculaApersona(int id_persona, Pelicula pelicula){
    
        Persona persona=this.buscarPersona(id_persona);
        
        if(persona == null) return null;
        
        Boolean tienePelicula=persona.getPeliculas()
                .stream()
                .anyMatch( p -> p.getId() == pelicula.getId());
        
        if(!tienePelicula){
            
            Pelicula peliculaAgregar=new Pelicula(pelicula.getId(), pelicula.getGenre(), pelicula.getTitle());
        
            return persona.agregarPelicula(peliculaAgregar);
        }
        
        return false;
    }
    
    
    public Boolean quitarPeliculaDePersona(int id_persona, int id_pelicula){
    
         Persona persona=this.buscarPersona(id_persona);
        
        if(persona == null) return null;
        
        /*Pelicula tienePelicula=persona.getPeliculas()
                .stream()
                .filter( p -> p.getId() == id_pelicula)
                .findFirst()
                .orElse(null);
        
        if(tienePelicula==null) return false;
        */
        
       return  persona.getPeliculas().removeIf(p -> p.getId() == id_pelicula);
                
    }
    
    
}
