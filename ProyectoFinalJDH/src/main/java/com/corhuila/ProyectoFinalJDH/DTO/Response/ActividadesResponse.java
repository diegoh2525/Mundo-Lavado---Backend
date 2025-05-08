package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data
public class ActividadesResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private int costo;
}
