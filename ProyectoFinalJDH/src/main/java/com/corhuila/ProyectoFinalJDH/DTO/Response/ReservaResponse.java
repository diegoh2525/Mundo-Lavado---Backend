package com.corhuila.ProyectoFinalJDH.DTO.Response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaResponse {
    private Long id;
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String estado;
    private String ubicacion;
    private UsuarioResponse usuario;
    private VehiculoResponse vehiculo;
    private ServicioResponse servicio;
}