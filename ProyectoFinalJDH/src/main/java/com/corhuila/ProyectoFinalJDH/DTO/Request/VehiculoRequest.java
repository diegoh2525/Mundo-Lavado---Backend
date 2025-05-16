package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class VehiculoRequest {
    private String marca;
    private String modelo;
    private String placa;
    private String tipo;
    private Long usuarioId; // ID del usuario que se asocia al vehículo
}