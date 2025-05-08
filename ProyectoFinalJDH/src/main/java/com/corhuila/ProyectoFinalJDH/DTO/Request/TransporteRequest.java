package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class TransporteRequest {
    private String tipo;
    private String empresa;
    private int costo;
}
