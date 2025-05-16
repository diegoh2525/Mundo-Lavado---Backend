package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data
public class ServicioResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
}