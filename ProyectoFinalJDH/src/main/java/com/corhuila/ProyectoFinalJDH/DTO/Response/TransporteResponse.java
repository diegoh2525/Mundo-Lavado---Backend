package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data
public class TransporteResponse {
    private Long id;
    private String tipo;
    private String empresa;
    private int costo;
}
