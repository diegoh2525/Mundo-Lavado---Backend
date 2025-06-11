package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaEmpleadoResponse {
    private Long id;
    private Long reservaId;
    private String nombreUsuario;
    private Long empleadoId;
    private String nombreEmpleado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaEliminacion;
}