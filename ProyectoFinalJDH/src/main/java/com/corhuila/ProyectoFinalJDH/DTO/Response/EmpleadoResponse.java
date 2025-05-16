package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data
public class EmpleadoResponse {
    private Long id;
    private String nombre;
    private String telefono;
    private String disponible;
}