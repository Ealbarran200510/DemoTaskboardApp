package com.ealbarran.DemoTaskboardApp.Mapper;

/*
Creando interfaz genérica para todos los mapper.
Recibe un dato de entrada, lo mapea y responde un objeto de salida
*/
public interface IMapper <I, O> {

    public O map(I in);

}
