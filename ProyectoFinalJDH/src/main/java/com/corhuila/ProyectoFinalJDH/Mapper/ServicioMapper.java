package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ServicioRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ServicioResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Servicio;

public class ServicioMapper {

    public static Servicio toEntity(ServicioRequest dto) {
        Servicio servicio = new Servicio();
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(dto.getPrecio());
        return servicio;
    }

    public static ServicioResponse toResponse(Servicio entity) {
        ServicioResponse dto = new ServicioResponse();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        return dto;
    }
}
