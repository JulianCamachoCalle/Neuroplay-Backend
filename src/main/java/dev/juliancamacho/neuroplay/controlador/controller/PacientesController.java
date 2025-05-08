package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {

    @Autowired
    private PacientesService pacientesService;

    // CREATE
    @PostMapping()
    public ResponseEntity<PacientesDto> createPacientes(@RequestBody PacientesDto pacientesDto) {
        return new ResponseEntity<>(pacientesService.createPacientes(pacientesDto), HttpStatus.CREATED);
    }

    // SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PacientesDto> getPacientes(@PathVariable Integer id) {
        return new ResponseEntity<>(pacientesService.getPacientes(id), HttpStatus.OK);
    }

    // SELECT ALL
    @GetMapping()
    public ResponseEntity<List<PacientesDto>> getPacientess() {
        List<PacientesDto> pacientess = pacientesService.getAllPacientes();
        return new ResponseEntity<>(pacientess, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PacientesDto> updatePacientes(@PathVariable Integer id, @RequestBody PacientesDto pacientesDto) {
        return new ResponseEntity<>(pacientesService.updatePacientes(id, pacientesDto), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePacientes(@PathVariable Integer id) {
        pacientesService.deletePacientesById(id);
        return new ResponseEntity<>("Pacientes eliminado con exito!", HttpStatus.OK);
    }
}
