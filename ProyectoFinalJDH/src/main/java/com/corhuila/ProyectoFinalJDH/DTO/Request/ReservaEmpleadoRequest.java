package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

@Data
public class ReservaEmpleadoRequest {
    private Long reservaId;
    private Long empleadoId;
}