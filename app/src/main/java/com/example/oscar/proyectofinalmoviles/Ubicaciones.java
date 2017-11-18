package com.example.oscar.proyectofinalmoviles;

/**
 * Created by Oscar on 18/11/2017.
 */

public class Ubicaciones {
    public String nombre ,codigo, contraseña;

    public Ubicaciones (String latitud, String longitud, String descripcion){
        this.nombre=latitud;
        this.codigo=longitud;
        this.contraseña=descripcion;
    }
}
