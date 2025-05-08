package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.Entity.Transporte;
import com.corhuila.ProyectoFinalJDH.DTO.Response.TransporteResponse;
import com.corhuila.ProyectoFinalJDH.DTO.Request.TransporteRequest;

public class TransporteMapper {

    public static TransporteResponse toResponse(Transporte transporte) {
        TransporteResponse response = new TransporteResponse();
        response.setId(transporte.getId());
        response.setTipo(transporte.getTipo());
        response.setEmpresa(transporte.getEmpresa());
        response.setCosto(transporte.getCosto());
        return response;
    }

    public static Transporte toEntity(TransporteRequest request) {
        Transporte transporte = new Transporte();
        transporte.setTipo(request.getTipo());
        transporte.setEmpresa(request.getEmpresa());
        transporte.setCosto(request.getCosto());
        return transporte;
    }
}
