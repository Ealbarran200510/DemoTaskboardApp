package com.ealbarran.DemoTaskboardApp.Controller;

import com.ealbarran.DemoTaskboardApp.Persistence.Entity.Task;
import com.ealbarran.DemoTaskboardApp.Persistence.Entity.TaskStatus;
import com.ealbarran.DemoTaskboardApp.Service.Dto.TaskInDto;
import com.ealbarran.DemoTaskboardApp.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
/*
La anotación Request Mapping nos permite definir la ruta del controlador.
*/

public class TaskController {

    private final TaskService taskService;
    /*
    Indicando la comunicación con la capa de Servicio
    */

    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }

    @PostMapping

    /*
    La anotación PostMapping permite crear elementos.
    */

    public Task createTask(@RequestBody TaskInDto taskInDto){
        /*
        La anotación RequestBody
        Necesita como parámetros el objeto que se ha creado.
        */

        return this.taskService.createTask(taskInDto);
        /*
        Devuelve el método taskInDto que hemos creado.
        */

    }

    /*
    Implementando segunda funcionalidad
    */
    @GetMapping

    public List<Task> findAll() {

        return this.taskService.findAll();

    }

    /*
    Creando una ruta con la variable status
    La anotación Get Mapping no puede ser igual que la anterior, aunque ambas apliquen una consulta.
    */
    @GetMapping("/status/{status}")

    public List<Task> findAllByStatus(@PathVariable("status")TaskStatus status) {

        /*
        La anotación PathVariable permite leer una variable que se encuentre en la ruta especificada.
        La variable se llamará status y será del tipo TaskStatus
        */

        return this.taskService.findAllByTaskStatus(status);

    }

    @PatchMapping("/markAsFinished/{id}")
    /*
    PatchMapping es un método HTTP que permite actualizar parcialmente los datos en la DB
    Recibe la ruta específica para modificar el estátus.
    */

    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {

        /*
        ResponseEntity es un método HTTP, recibe con PathVariable un elemento como variable
        Necesita especificar el campo a actualizar y el tipo de dato.
        */

        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();

        /*
        ResponseEntity no necesariamente necesita return, sin embargo devolvemos un estado HTTP par confirmar el cambio en la DB.
        */

    }

    /*
        Delete Mapping permite eliminar elementos recibiendo un dato del mismo
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
