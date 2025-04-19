/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FacundoRodriguez.prueba_tecnica_back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
/*import lombok.Getter;
import lombok.Setter;
*/

/**
 *
 * @author FACU
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
/*@Getter
@Setter
*/

public class RespuestaDTO {
    
    private String mensaje;
    private int status;
    private Object data;
    private String error;

    
    public RespuestaDTO(String mensaje, int status, Object data, String error) {
        this.mensaje = mensaje;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    

}
