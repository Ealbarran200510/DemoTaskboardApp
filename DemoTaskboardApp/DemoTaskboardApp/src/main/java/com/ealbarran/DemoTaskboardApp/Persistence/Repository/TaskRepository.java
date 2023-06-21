package com.ealbarran.DemoTaskboardApp.Persistence.Repository;

import com.ealbarran.DemoTaskboardApp.Persistence.Entity.Task;
import com.ealbarran.DemoTaskboardApp.Persistence.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Indicando que el Repositorio extiende de la entidad de JPA, recibe dos valores, el nombre de la entidad para la cual persistirá valores y un ID que forzosamente coincida con el atributo ID de la entidad
*/
public interface TaskRepository extends JpaRepository<Task, Long> {

/*
Creando una query utilizando Spring Data JPA
 */

    public List<Task> findAllByTaskStatus(TaskStatus status);

    /*
    Creando una query nativa usando JPA Repository
    El método debe recibir la tarea a modificar.
    */

    @Modifying
    /*
    La anotación Modifying indica que esta es una query de actualziación, no de consulta.
    */
    @Query(value="UPDATE TASK SET FINISHED=true WHERE ID=id", nativeQuery = true)
    /*
    La anotación Query permite ejecutar consultas en Spring
    Recibe el valor de la query que se ejecutará y si es una query nativa o no
    */
    public void markTaskAsFinished(@Param("id") Long id);
    /*
    La anotación Param indica el parámetro que el método va a utilizar
    */

}
