package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class AlojamientoRequest {
    private String nombre;
    private String tipo;
    private String descripcion;
    private int costo;
}
