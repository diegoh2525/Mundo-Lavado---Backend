package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.EmpleadoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.EmpleadoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Empleado;

public class EmpleadoMapper {

    public static Empleado toEntity(EmpleadoRequest dto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setTelefono(dto.getTelefono());
        empleado.setDisponible(dto.getDisponible());
        return empleado;
    }

    public static EmpleadoResponse toResponse(Empleado entity) {
        EmpleadoResponse dto = new EmpleadoResponse();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setTelefono(entity.getTelefono());
        dto.setDisponible(entity.getDisponible());
        return dto;
    }
}
