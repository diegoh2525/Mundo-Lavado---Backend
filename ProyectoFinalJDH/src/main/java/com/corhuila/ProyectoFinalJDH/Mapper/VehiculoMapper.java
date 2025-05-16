package com.corhuila.ProyectoFinalJDH.Mapper;


import com.corhuila.ProyectoFinalJDH.DTO.Request.VehiculoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.VehiculoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;

public class VehiculoMapper {

    public static Vehiculo toEntity(VehiculoRequest dto, Usuario usuario) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setTipo(dto.getTipo());
        vehiculo.setUsuario(usuario);
        return vehiculo;
    }

    public static VehiculoResponse toResponse(Vehiculo entity) {
        VehiculoResponse dto = new VehiculoResponse();
        dto.setId(entity.getId());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setPlaca(entity.getPlaca());
        dto.setTipo(entity.getTipo());
        dto.setUsuario(UsuarioMapper.toResponse(entity.getUsuario()));
        return dto;
    }
}

