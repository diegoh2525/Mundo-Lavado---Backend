package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ServicioRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ServicioResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Servicio;
import com.corhuila.ProyectoFinalJDH.Service.IService.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioResponse>> getAllServicios() {
        List<Servicio> servicios = servicioService.all();
        List<ServicioResponse> response = servicios.stream().map(this::convertToResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponse> getServicioById(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.findById(id);
        if (servicio.isEmpty() || servicio.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(servicio.get()));
    }

    @PostMapping
    public ResponseEntity<ServicioResponse> createServicio(@RequestBody ServicioRequest request) {
        Servicio servicio = convertToEntity(request);
        Servicio saved = servicioService.save(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateServicio(@RequestBody ServicioRequest request, @PathVariable Long id) {
        Optional<Servicio> servicioExistente = servicioService.findById(id);
        if (servicioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Servicio servicio = convertToEntity(request);
        servicioService.update(servicio, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deleteServicioPhysical(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.findById(id);
        if (servicio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicioService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteServicioLogical(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.findById(id);
        if (servicio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicioService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para convertir entre Entidad y DTO

    private ServicioResponse convertToResponse(Servicio servicio) {
        ServicioResponse response = new ServicioResponse();
        response.setId(servicio.getId());
        response.setNombre(servicio.getNombre());
        response.setDescripcion(servicio.getDescripcion());
        response.setPrecio(servicio.getPrecio());
        return response;
    }

    private Servicio convertToEntity(ServicioRequest request) {
        Servicio servicio = new Servicio();
        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setPrecio(request.getPrecio());
        return servicio;
    }
}
