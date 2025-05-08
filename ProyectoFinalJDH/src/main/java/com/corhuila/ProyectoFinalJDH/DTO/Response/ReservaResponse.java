package com.corhuila.ProyectoFinalJDH.DTO.Response;

import java.util.List;

public class ReservaResponse {

    private Long id;
    private String usuarioNombre;
    private List<String> transportes;
    private List<String> alojamientos;
    private List<String> actividades;
    private int costoTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public List<String> getTransportes() {
        return transportes;
    }

    public void setTransportes(List<String> transportes) {
        this.transportes = transportes;
    }

    public List<String> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(List<String> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public List<String> getActividades() {
        return actividades;
    }

    public void setActividades(List<String> actividades) {
        this.actividades = actividades;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }
}
