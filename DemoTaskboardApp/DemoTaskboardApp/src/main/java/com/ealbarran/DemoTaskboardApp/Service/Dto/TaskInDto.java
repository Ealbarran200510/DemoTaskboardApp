package com.ealbarran.DemoTaskboardApp.Service.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
/*
Seleccionando datos que DTO pedirá al usuario.
*/
public class TaskInDto {

    private String title;
    private String description;
    private LocalDateTime estimatedFinish;

}
