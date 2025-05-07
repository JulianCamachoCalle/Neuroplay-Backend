package dev.juliancamacho.neuroplay.controller;

import dev.juliancamacho.neuroplay.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController
{
    private final UsuarioService usuarioService;

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
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.noContent().build();
    }


    // VERIFICAR USERNAME
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String username) {
        boolean exists = usuarioService.checkUsernameExists(username);
        return ResponseEntity.ok(exists);
    }

    // VERIFICAR TELEFONO
    @GetMapping("/check-telefono/{telefono}")
    public ResponseEntity<Boolean> checkTelefono(@PathVariable String telefono) {
        boolean exists = usuarioService.checkTelefonoExists(telefono);
        return ResponseEntity.ok(exists);
    }
}
