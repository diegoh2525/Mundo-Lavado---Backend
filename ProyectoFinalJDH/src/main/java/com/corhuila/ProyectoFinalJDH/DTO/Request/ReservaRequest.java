package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReservaRequest {
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String estado;
    private String ubicacion;
    private Long usuarioId;
    private Long vehiculoId;
    private Long servicioId;
}