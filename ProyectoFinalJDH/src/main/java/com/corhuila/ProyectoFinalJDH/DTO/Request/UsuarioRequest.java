package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nombre;
    private String correo;
    private String password;
    private boolean estado = true;
}
