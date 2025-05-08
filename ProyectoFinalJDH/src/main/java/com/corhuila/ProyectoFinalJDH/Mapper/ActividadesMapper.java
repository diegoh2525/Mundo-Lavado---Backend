package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ActividadesRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ActividadesResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Actividades;

public class ActividadesMapper {

    public static ActividadesResponse toActividadesResponse(Actividades actividades) {
        ActividadesResponse dto = new ActividadesResponse();
        dto.setId(actividades.getId());
        dto.setNombre(actividades.getNombre());
        dto.setDescripcion(actividades.getDescripcion());
        dto.setCosto(actividades.getCosto());
        return dto;
    }

    public static Actividades toActividadesEntity(ActividadesRequest request) {
        Actividades entity = new Actividades();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setCosto(request.getCosto());
        return entity;
    }

}
