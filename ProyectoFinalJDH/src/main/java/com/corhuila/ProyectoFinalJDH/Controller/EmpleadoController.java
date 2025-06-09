package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.EmpleadoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.EmpleadoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Empleado;
import com.corhuila.ProyectoFinalJDH.Service.IService.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.all();
        List<EmpleadoResponse> response = empleados.stream()
                .filter(e -> e.getFechaEliminacion() == null)
                .map(this::convertToResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> getEmpleadoById(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.findById(id);
        if (empleado.isEmpty() || empleado.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(empleado.get()));
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> createEmpleado(@RequestBody EmpleadoRequest request) {
        Empleado empleado = convertToEntity(request);
        Empleado saved = empleadoService.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmpleado(@RequestBody EmpleadoRequest request, @PathVariable Long id) {
        Optional<Empleado> empleadoExistente = empleadoService.findById(id);
        if (empleadoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Empleado empleado = convertToEntity(request);
        empleadoService.update(empleado, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deleteEmpleadoPhysical(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.findById(id);
        if (empleado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteEmpleadoLogical(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.findById(id);
        if (empleado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para convertir entre Entidad y DTO

    private EmpleadoResponse convertToResponse(Empleado empleado) {
        EmpleadoResponse response = new EmpleadoResponse();
        response.setId(empleado.getId());
        response.setNombre(empleado.getNombre());
        response.setTelefono(empleado.getTelefono());
        response.setDisponible(empleado.getDisponible());
        return response;
    }

    private Empleado convertToEntity(EmpleadoRequest request) {
        Empleado empleado = new Empleado();
        empleado.setNombre(request.getNombre());
        empleado.setTelefono(request.getTelefono());
        empleado.setDisponible(request.getDisponible());
        return empleado;
    }
}