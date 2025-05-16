package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String correo;
    private List<VehiculoResponse> vehiculos;
}
