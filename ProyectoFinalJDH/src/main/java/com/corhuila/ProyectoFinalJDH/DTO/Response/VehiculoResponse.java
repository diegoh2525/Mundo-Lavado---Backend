package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

@Data

public class VehiculoResponse {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private String tipo;
    private UsuarioResponse usuario;
}