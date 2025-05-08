package com.corhuila.ProyectoFinalJDH.DTO.Request;

import lombok.Data;
import java.util.List;

@Data
public class ReservaRequest {
    private Long usuarioId;
    private List<Long> transportesIds;
    private List<Long> alojamientosIds;
    private List<Long> actividadesIds;
    private String fechaViaje;
}
