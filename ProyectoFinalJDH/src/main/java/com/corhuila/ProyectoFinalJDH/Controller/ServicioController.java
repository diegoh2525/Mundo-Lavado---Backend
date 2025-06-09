package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ServicioRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ServicioResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Servicio;
import com.corhuila.ProyectoFinalJDH.Mapper.ServicioMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    // Obtener todos los servicios
    @GetMapping
    public ResponseEntity<List<ServicioResponse>> getAll() {
        List<Servicio> servicios = servicioService.all();
        List<ServicioResponse> response = servicios.stream()
                .filter(s -> s.getFechaEliminacion() == null)
                .map(ServicioMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponse> getById(@PathVariable Long id) {
        return servicioService.findById(id)
                .filter(s -> s.getFechaEliminacion() == null)
                .map(ServicioMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<ServicioResponse> create(@RequestBody ServicioRequest request) {
        Servicio servicio = ServicioMapper.toEntity(request);
        Servicio creado = servicioService.save(servicio);
        return new ResponseEntity<>(ServicioMapper.toResponse(creado), HttpStatus.CREATED);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ServicioRequest request) {
        Optional<Servicio> optionalServicio = servicioService.findById(id);
        if (optionalServicio.isEmpty() || optionalServicio.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        Servicio servicio = ServicioMapper.toEntity(request);
        servicioService.update(servicio, id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar lógico
    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        Optional<Servicio> optionalServicio = servicioService.findById(id);
        if (optionalServicio.isEmpty() || optionalServicio.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        servicioService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar físico
    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        if (servicioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        servicioService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }
}