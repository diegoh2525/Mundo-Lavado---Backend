package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.VehiculoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.VehiculoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import com.corhuila.ProyectoFinalJDH.Mapper.VehiculoMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.IUsuarioService;
import com.corhuila.ProyectoFinalJDH.Service.IService.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> getAll() {
        List<VehiculoResponse> responseList = vehiculoService.all()
                .stream()
                .filter(v -> v.getFechaEliminacion() == null) // Filtra eliminados lógicamente
                .map(VehiculoMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponse> getById(@PathVariable Long id) {
        Optional<Vehiculo> optional = vehiculoService.findById(id);
        if (optional.isEmpty() || optional.get().getFechaEliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(VehiculoMapper.toResponse(optional.get()));
    }

    @PostMapping
    public ResponseEntity<VehiculoResponse> create(@RequestBody VehiculoRequest request) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(request.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Vehiculo vehiculo = VehiculoMapper.toEntity(request, usuarioOptional.get());
        Vehiculo nuevo = vehiculoService.save(vehiculo);

        return ResponseEntity.ok(VehiculoMapper.toResponse(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody VehiculoRequest request) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(request.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Vehiculo vehiculo = VehiculoMapper.toEntity(request, usuarioOptional.get());
        vehiculoService.update(vehiculo, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        vehiculoService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        vehiculoService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }
}
