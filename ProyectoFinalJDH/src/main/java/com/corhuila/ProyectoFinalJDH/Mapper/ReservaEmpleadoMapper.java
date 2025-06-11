package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ReservaEmpleadoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaEmpleadoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;
import com.corhuila.ProyectoFinalJDH.Service.IService.IEmpleadoService;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservaEmpleadoMapper {

    @Autowired
    private IReservaService reservaService;

    @Autowired
    private IEmpleadoService empleadoService;

    public ReservaEmpleado toEntity(ReservaEmpleadoRequest request) {
        ReservaEmpleado entity = new ReservaEmpleado();
        entity.setReserva(reservaService.findById(request.getReservaId()).orElse(null));
        entity.setEmpleado(empleadoService.findById(request.getEmpleadoId()).orElse(null));
        entity.setFechaCreacion(LocalDateTime.now());
        return entity;
    }

    public ReservaEmpleadoResponse toResponse(ReservaEmpleado entity) {
        ReservaEmpleadoResponse response = new ReservaEmpleadoResponse();
        response.setId(entity.getId());
        response.setReservaId(entity.getReserva().getId());
        response.setNombreUsuario(entity.getReserva().getUsuario().getNombre());
        response.setEmpleadoId(entity.getEmpleado().getId());
        response.setNombreEmpleado(entity.getEmpleado().getNombre());
        response.setFechaCreacion(entity.getFechaCreacion());
        response.setFechaModificacion(entity.getFechaModificacion());
        response.setFechaEliminacion(entity.getFechaEliminacion());
        return response;
    }

    public List<ReservaEmpleadoResponse> toResponseList(List<ReservaEmpleado> lista) {
        return lista.stream().map(this::toResponse).collect(Collectors.toList());
    }
}