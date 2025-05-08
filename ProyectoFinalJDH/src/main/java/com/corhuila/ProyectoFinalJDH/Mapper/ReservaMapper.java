package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ReservaRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaResponse;
import com.corhuila.ProyectoFinalJDH.Entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaMapper {

    // Convertir de Reserva (entidad) a ReservaResponse (DTO)
    public static ReservaResponse toDTO(Reserva reserva) {
        ReservaResponse dto = new ReservaResponse();

        dto.setId(reserva.getId());
        dto.setUsuarioNombre(reserva.getUsuario().getNombre());

        dto.setTransportes(
                reserva.getTransportes().stream()
                        .map(t -> t.getTipo() + " - " + t.getEmpresa())
                        .collect(Collectors.toList())
        );

        dto.setAlojamientos(
                reserva.getAlojamientos().stream()
                        .map(Alojamiento::getNombre)
                        .collect(Collectors.toList())
        );

        dto.setActividades(
                reserva.getActividades().stream()
                        .map(Actividades::getNombre)
                        .collect(Collectors.toList())
        );

        dto.setCostoTotal(calcularCostoTotal(reserva));

        return dto;
    }

    // Convertir de ReservaRequest a Reserva (entidad)
    public static Reserva toEntity(ReservaRequest request, Usuario usuario,
                                   List<Transporte> transportes,
                                   List<Alojamiento> alojamientos,
                                   List<Actividades> actividades) {

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setTransportes(transportes);
        reserva.setAlojamientos(alojamientos);
        reserva.setActividades(actividades);
        return reserva;
    }

    // Método privado para calcular el costo total
    private static int calcularCostoTotal(Reserva reserva) {
        int costoTransportes = reserva.getTransportes().stream()
                .mapToInt(Transporte::getCosto)
                .sum();

        int costoAlojamientos = reserva.getAlojamientos().stream()
                .mapToInt(Alojamiento::getCosto)
                .sum();

        int costoActividades = reserva.getActividades().stream()
                .mapToInt(Actividades::getCosto)
                .sum();

        return costoTransportes + costoAlojamientos + costoActividades;
    }
}
