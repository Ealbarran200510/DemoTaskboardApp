package com.ealbarran.DemoTaskboardApp.Service;

import com.ealbarran.DemoTaskboardApp.Exceptions.ToDoExceptions;
import com.ealbarran.DemoTaskboardApp.Mapper.TaskInDtoToTask;
import com.ealbarran.DemoTaskboardApp.Persistence.Entity.Task;
import com.ealbarran.DemoTaskboardApp.Persistence.Entity.TaskStatus;
import com.ealbarran.DemoTaskboardApp.Persistence.Repository.TaskRepository;
import com.ealbarran.DemoTaskboardApp.Service.Dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
/*
La clase Service le indica a Spring que cree un bin para esta clase que puede insertarse donde se necesite
*/
public class TaskService {

    /*
    Inyección de dependencia. Inyectando un objeto de una clase en otra.
    */
    private final TaskRepository repository;
    /*
    Inyectando el Mapper creado
    */
    private final TaskInDtoToTask mapper;

    public TaskService(TaskRepository repository, TaskInDtoToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;

    }

    /*
    Implementando primer funcionalidad. Crear una tarea.
    */
    public Task createTask(TaskInDto taskInDto) {

        /*
        Aplicando Objeto de Transferencia de Datos DTO, permite elegir los datos a mover entre capas de la aplicación sin necesidad de trasladar todos los atributos.
        Dto define los datos de entrada que pedirá al usuario.
        */

        Task task = mapper.map(taskInDto);
        /*
        Mapeando un objeto a otro objeto con Mapper.
        */
        return this.repository.save(task);
        /*
        Accediendo al Repositorio para guardad datos en la DB.
        El método save devuelve la Entidad que se guardó.
        */

    }

    /*
    Implementando la funcionalidad para obtener las tareas
    No es necesario implementar en Persistencia (Repositorio), ya que JPA ya contiene el método findAll
     */

    public List<Task> findAll() {

        return this.repository.findAll();

    }

    /*
    Implementando consulta por estátus.
    */

    public List<Task> findAllByTaskStatus(TaskStatus status) {

        return this.repository.findAllByTaskStatus(status);

    }

    /*
    Implementando modificar el estátus de una tarea.
    */

    @Transactional
    public void updateTaskAsFinished(Long id) {

        this.repository.markTaskAsFinished(id);

        /*
        Agregando la excepción a la modificación de estátus de tarea
        Este método es opcional con If
        Si el opcional está vacío, entonces se lanza la excepción especificando el mensaje a mostrar y el estátus HTTP.
        */
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskAsFinished(id);

    }

        /*
        Implementando eliminar tarea
        JPA también contiene el método deleteById, por lo que tampoco es necesario implementar en Repositorio
        */
    public void deleteById(Long id) {

        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);

    }

}
