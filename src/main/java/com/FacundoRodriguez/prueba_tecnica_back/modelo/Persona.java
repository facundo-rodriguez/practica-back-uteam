

package com.FacundoRodriguez.prueba_tecnica_back.modelo;


import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author FACU
 */

public class Persona {
    
    @NotNull(message = "El id no puede estar vacío")
    private int id_persona;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    
    @NotNull(message = "La fecha de nacimiento no puede estar vacío")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;
    
    @NotNull(message = "El campo asegurado no puede estar vacío")
    private Boolean insurance;
    
    //@Size(max = 10, message = "No puedes tener más de 10 peliculas.")
    private List<Pelicula> peliculas= new ArrayList<>();

    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer limitPeliculas=3;
    
    
    public Persona(){}

    public Persona(int id_persona, String firstName, String lastName, LocalDate birthdate, boolean insurance, Integer limitPeliculas) {
        this.id_persona = id_persona;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.insurance = insurance;
        //this.peliculas = peliculas;
        this.limitPeliculas=limitPeliculas;
    }   
    
    

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public Boolean getInsurance(){
    
        return this.insurance;
    }
    
    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
    public Boolean agregarPelicula(Pelicula pelicula){
        
        if( this.peliculas.size() < this.getLimitPeliculas()){
            
            this.peliculas.add(pelicula);
            
            return true;
        } 
        
        return false;
    }
    

    public Integer getLimitPeliculas() {
        return limitPeliculas;
    }

    public void setLimitPeliculas(int limitPeliculas) {
        this.limitPeliculas = limitPeliculas;
    }
  
    
    
    
}   
