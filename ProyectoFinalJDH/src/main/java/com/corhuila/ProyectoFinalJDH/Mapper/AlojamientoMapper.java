package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;
import com.corhuila.ProyectoFinalJDH.DTO.Response.AlojamientoResponse;
import com.corhuila.ProyectoFinalJDH.DTO.Request.AlojamientoRequest;

public class AlojamientoMapper {

    public static AlojamientoResponse toResponse(Alojamiento alojamiento) {
        AlojamientoResponse response = new AlojamientoResponse();
        response.setId(alojamiento.getId());
        response.setNombre(alojamiento.getNombre());
        response.setTipo(alojamiento.getTipo());
        response.setDescripcion(alojamiento.getDescripcion());
        response.setCosto(alojamiento.getCosto());
        return response;
    }

    public static Alojamiento toEntity(AlojamientoRequest request) {
        Alojamiento alojamiento = new Alojamiento();
        alojamiento.setNombre(request.getNombre());
        alojamiento.setTipo(request.getTipo());
        alojamiento.setDescripcion(request.getDescripcion());
        alojamiento.setCosto(request.getCosto());
        return alojamiento;
    }
}
