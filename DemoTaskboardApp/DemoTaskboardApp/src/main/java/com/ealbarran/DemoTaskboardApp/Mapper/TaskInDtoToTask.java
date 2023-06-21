package com.ealbarran.DemoTaskboardApp.Mapper;

import com.ealbarran.DemoTaskboardApp.Persistence.Entity.Task;
import com.ealbarran.DemoTaskboardApp.Persistence.Entity.TaskStatus;
import com.ealbarran.DemoTaskboardApp.Service.Dto.TaskInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
La anotación Componente declara al Mapper como tal para poder inyectarlo en cualquier otra clase
*/
@Component
/*
Indicando a la clase que implemente el Mapper genérico, especificando el origen y destino de la salida
*/
public class TaskInDtoToTask implements IMapper<TaskInDto, Task> {

    @Override
    public Task map(TaskInDto in) {

        Task task = new Task();
        /*
        Declarando el objeto que el Mapper devolverá a la salida
        */
        task.setTitle(in.getTitle());
        /*
        Set para titulo que obtendrá del dato que ingrese el usuario.
        */
        task.setDescription(in.getDescription());
        task.setEstimatedFinish(in.getEstimatedFinish());

        /*
        Set para los datos que NO ingresa el usuario, pero sabemos que forman parte de la DB
        */
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);

        return task;

    }
}
