package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ReservaEmpleadoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaEmpleadoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;
import com.corhuila.ProyectoFinalJDH.Mapper.ReservaEmpleadoMapper;
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
    private IReservaEmpleadoService service;

    @Autowired
    private ReservaEmpleadoMapper mapper;

    // ✅ GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<ReservaEmpleadoResponse>> getAll() {
        List<ReservaEmpleado> lista = service.all();
        return ResponseEntity.ok(mapper.toResponseList(lista));
    }

    // ✅ GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaEmpleadoResponse> getById(@PathVariable Long id) {
        Optional<ReservaEmpleado> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toResponse(optional.get()));
    }

    // ✅ POST: Crear nuevo
    @PostMapping
    public ResponseEntity<ReservaEmpleadoResponse> create(@RequestBody ReservaEmpleadoRequest request) {
        ReservaEmpleado entity = mapper.toEntity(request);
        ReservaEmpleado saved = service.save(entity);
        return new ResponseEntity<>(mapper.toResponse(saved), HttpStatus.CREATED);
    }

    // ✅ PUT: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaEmpleadoRequest request, @PathVariable Long id) {
        ReservaEmpleado entity = mapper.toEntity(request);
        service.update(entity, id);
        return ResponseEntity.noContent().build();
    }

    // ✅ DELETE lógico
    @DeleteMapping("/logical/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        service.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ DELETE físico
    @DeleteMapping("/physical/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        service.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }
}
