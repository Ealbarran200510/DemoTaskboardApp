package com.ealbarran.DemoTaskboardApp.Persistence.Entity;


import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
/*
La anotación Data permite obtener set y get a través de Lombok
*/
@Entity
/*
La anotación Entity indica que esto es una entidad de la DB
*/
@Table(name="task")
/*
La entidad Table indica que la Entidad se convertirá en una tabla de la DB
*/

public class Task {

    @Id
    /*
    Definiendo el ID de la entidad con la anotación
    */
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*
    La anotación Generated Value genera de manera automática e incremental valores para un atributo
    Se define la estrategia como automática para que Spring defina la estrategia más adecuada en función del tipo de base de datos que se utilizará
    */

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime estimatedFinish;
    private boolean finished;
    private TaskStatus taskStatus;

}
