package com.ealbarran.DemoTaskboardApp.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToDoExceptions extends RuntimeException{

    /*
    Definiendo los atributos de la excepción. Mensaje y estátus HTTP.
     */

    private String message;
    private HttpStatus httpStatus;

    /*
    Sobrecargando el método para poder pasar el mensaje a otras clases.
    */

    public ToDoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
