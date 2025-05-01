package dev.juliancamacho.neuroplay.controller;

import dev.juliancamacho.neuroplay.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioController
{
    @Autowired
    private UsuarioService usuarioService;

    // CREATE
    @PostMapping()
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService.createUsuario(usuarioDto), HttpStatus.CREATED);
    }

    // SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuario(@PathVariable Integer id) {
        return new ResponseEntity<>(usuarioService.getUsuario(id), HttpStatus.OK);
    }

    // SELECT ALL
    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        List<UsuarioDto> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService.updateUsuario(id, usuarioDto), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
        return new ResponseEntity<>("Usuario eliminado con exito!", HttpStatus.OK);
    }
}
