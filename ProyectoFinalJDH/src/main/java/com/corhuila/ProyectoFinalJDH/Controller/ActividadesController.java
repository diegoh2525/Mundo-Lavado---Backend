package com.corhuila.ProyectoFinalJDH.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ActividadesRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ActividadesResponse;
import com.corhuila.ProyectoFinalJDH.Mapper.ActividadesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.corhuila.ProyectoFinalJDH.Entity.Actividades;
import com.corhuila.ProyectoFinalJDH.Service.IService.IActividadesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Actividades")
public class ActividadesController {

    @Autowired
    private IActividadesService service;

    @GetMapping()
    public List<ActividadesResponse> all() {
        return service.all().stream()
                .map(ActividadesMapper::toActividadesResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ActividadesResponse findById(@PathVariable Long id) {
        Optional<Actividades> actividad = service.findById(id);
        return actividad.map(ActividadesMapper::toActividadesResponse)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActividadesResponse save(@RequestBody ActividadesRequest request) {
        Actividades actividad = ActividadesMapper.toActividadesEntity(request);
        return ActividadesMapper.toActividadesResponse(service.save(actividad));
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody ActividadesRequest request, @PathVariable Long id) {
        Actividades actividad = ActividadesMapper.toActividadesEntity(request);
        service.update(actividad, id);
    }

    @PutMapping("deleteLogical/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteLogical(@PathVariable Long id) {
        service.deleteLogical(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePhysical(@PathVariable Long id) {
        service.deletePhysical(id);
    }
}
