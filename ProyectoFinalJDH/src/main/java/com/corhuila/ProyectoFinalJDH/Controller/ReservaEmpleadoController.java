package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva-empleado")
@CrossOrigin(origins = "*")
public class ReservaEmpleadoController {

    @Autowired
    private IReservaEmpleadoService reservaEmpleadoService;

    // Obtener todos (excluyendo eliminados lógicamente)
    @GetMapping
    public ResponseEntity<List<ReservaEmpleado>> getAll() {
        List<ReservaEmpleado> lista = reservaEmpleadoService.all().stream()
                .filter(re -> re.getFechaEliminacion() == null)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaEmpleado> getById(@PathVariable Long id) {
        Optional<ReservaEmpleado> op = reservaEmpleadoService.findById(id);
        if (op.isEmpty() || op.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(op.get());
    }

    // Crear
    @PostMapping
    public ResponseEntity<ReservaEmpleado> create(@RequestBody ReservaEmpleado reservaEmpleado) {
        ReservaEmpleado saved = reservaEmpleadoService.save(reservaEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaEmpleado reservaEmpleado, @PathVariable Long id) {
        Optional<ReservaEmpleado> op = reservaEmpleadoService.findById(id);
        if (op.isEmpty() || op.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }
        reservaEmpleadoService.update(reservaEmpleado, id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar físico
    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        Optional<ReservaEmpleado> op = reservaEmpleadoService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservaEmpleadoService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar lógico
    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        Optional<ReservaEmpleado> op = reservaEmpleadoService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservaEmpleadoService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }
}
