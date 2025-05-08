package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.UsuarioRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.UsuarioResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Mapper.UsuarioMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping()
    public List<UsuarioResponse> all() {
        return service.all()
                .stream()
                .map(UsuarioMapper::toResponse) // Usamos toResponse
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        Optional<Usuario> usuario = service.findById(id);
        return usuario.map(UsuarioMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        return UsuarioMapper.toResponse(service.save(usuario));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UsuarioRequest request, @PathVariable Long id) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        service.update(usuario, id);
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

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody UsuarioRequest request) {
        Optional<Usuario> usuario = service.login(request.getCorreo(), request.getPassword());

        return usuario.map(UsuarioMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
    }

}
