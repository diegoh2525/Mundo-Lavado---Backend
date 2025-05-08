package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data
public class AlojamientoResponse {
    private Long id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int costo;
}
