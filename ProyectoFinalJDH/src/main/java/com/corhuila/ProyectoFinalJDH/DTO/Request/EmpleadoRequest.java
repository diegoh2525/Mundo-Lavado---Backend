package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class EmpleadoRequest {
    private String nombre;
    private String telefono;
    private String disponible;
}