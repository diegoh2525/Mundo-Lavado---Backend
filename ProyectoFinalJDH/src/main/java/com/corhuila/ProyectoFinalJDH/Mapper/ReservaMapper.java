package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ReservaRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Reserva;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import com.corhuila.ProyectoFinalJDH.Entity.Servicio;

public class ReservaMapper {

    public static Reserva toEntity(ReservaRequest dto, Usuario usuario, Vehiculo vehiculo, Servicio servicio) {
        Reserva reserva = new Reserva();
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setHoraReserva(dto.getHoraReserva());
        reserva.setEstado(dto.getEstado());
        reserva.setUbicacion(dto.getUbicacion());
        reserva.setUsuario(usuario);
        reserva.setVehiculo(vehiculo);
        reserva.setServicio(servicio);
        return reserva;
    }

    public static ReservaResponse toResponse(Reserva reserva) {
        ReservaResponse dto = new ReservaResponse();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setHoraReserva(reserva.getHoraReserva());
        dto.setEstado(reserva.getEstado());
        dto.setUbicacion(reserva.getUbicacion());
        dto.setUsuario(UsuarioMapper.toResponse(reserva.getUsuario()));
        dto.setVehiculo(VehiculoMapper.toResponse(reserva.getVehiculo()));
        dto.setServicio(ServicioMapper.toResponse(reserva.getServicio()));
        return dto;
    }
}
