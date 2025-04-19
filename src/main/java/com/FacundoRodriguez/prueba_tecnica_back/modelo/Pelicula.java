
package com.FacundoRodriguez.prueba_tecnica_back.modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*import lombok.Getter;
import lombok.Setter;
*/

/**
 *
 * @author FACU
 */

/*@Getter
@Setter
*/
public class Pelicula {
    
    @NotNull(message = "El id no puede estar vacío")
    private int id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String title;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String genre;

    
    
    
    public Pelicula(){}

    public Pelicula(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
    
    
}
