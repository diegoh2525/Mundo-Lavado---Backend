package com.corhuila.ProyectoFinalJDH.Mapper;

import com.corhuila.ProyectoFinalJDH.DTO.Request.UsuarioRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.UsuarioResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;

public class UsuarioMapper {

    // Convertir de entidad a DTO de respuesta
    public static UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setCorreo(usuario.getCorreo());
        return response;
    }

    // Convertir de DTO de solicitud a entidad
    public static Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(request.getPassword());
        usuario.setEstado(request.isEstado());
        return usuario;
    }
}
