package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.VehiculoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.VehiculoResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import com.corhuila.ProyectoFinalJDH.Mapper.VehiculoMapper;
import com.corhuila.ProyectoFinalJDH.Repository.UsuarioRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService service;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<VehiculoResponse> all() {
        return service.all()
                .stream()
                .map(VehiculoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public VehiculoResponse findById(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = service.findById(id);
        return vehiculo.map(VehiculoMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculoResponse save(@RequestBody VehiculoRequest request) {
        Vehiculo vehiculo = VehiculoMapper.toEntity(request, usuario);
        return VehiculoMapper.toResponse(service.save(vehiculo));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody VehiculoRequest request, @PathVariable Long id) {
        Vehiculo vehiculo = VehiculoMapper.toEntity(request, usuario);
        service.update(vehiculo, id);
    }

    @PutMapping("deleteLogical/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLogical(@PathVariable Long id) {
        service.deleteLogical(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhysical(@PathVariable Long id) {
        service.deletePhysical(id);
    }
}
