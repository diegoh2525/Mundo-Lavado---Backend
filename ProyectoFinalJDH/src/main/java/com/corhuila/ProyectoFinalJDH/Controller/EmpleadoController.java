package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.EmpleadoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.EmpleadoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Empleado;
import com.corhuila.ProyectoFinalJDH.Mapper.EmpleadoMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    // Obtener todos los empleados (que no estén eliminados)
    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> getAll() {
        List<Empleado> empleados = empleadoService.all();
        List<EmpleadoResponse> response = empleados.stream()
                .filter(e -> e.getFechaEliminacion() == null)
                .map(EmpleadoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> getById(@PathVariable Long id) {
        return empleadoService.findById(id)
                .filter(e -> e.getFechaEliminacion() == null)
                .map(EmpleadoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo empleado
    @PostMapping
    public ResponseEntity<EmpleadoResponse> create(@RequestBody EmpleadoRequest request) {
        Empleado empleado = EmpleadoMapper.toEntity(request);
        Empleado creado = empleadoService.save(empleado);
        return new ResponseEntity<>(EmpleadoMapper.toResponse(creado), HttpStatus.CREATED);
    }

    // Actualizar empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmpleadoRequest request) {
        Optional<Empleado> optionalEmpleado = empleadoService.findById(id);
        if (optionalEmpleado.isEmpty() || optionalEmpleado.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        Empleado empleado = EmpleadoMapper.toEntity(request);
        empleadoService.update(empleado, id);
        return ResponseEntity.noContent().build();
    }

    // Eliminación lógica
    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        Optional<Empleado> optionalEmpleado = empleadoService.findById(id);
        if (optionalEmpleado.isEmpty() || optionalEmpleado.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        empleadoService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminación física
    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        if (empleadoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        empleadoService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }
}
