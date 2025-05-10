package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapeutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terapeutas")
public class TerapeutasController {

    @Autowired
    private TerapeutasService terapeutasService;

    // CREATE
    @PostMapping()
    public ResponseEntity<TerapeutasDto> createTerapeutas(@RequestBody TerapeutasDto terapeutasDto) {
        return new ResponseEntity<>(terapeutasService.createTerapeutas(terapeutasDto), HttpStatus.CREATED);
    }

    // SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TerapeutasDto> getTerapeutas(@PathVariable Integer id) {
        return new ResponseEntity<>(terapeutasService.getTerapeutas(id), HttpStatus.OK);
    }

    // SELECT ALL
    @GetMapping()
    public ResponseEntity<List<TerapeutasDto>> getTerapeutass() {
        List<TerapeutasDto> terapeutass = terapeutasService.getAllTerapeutas();
        return new ResponseEntity<>(terapeutass, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TerapeutasDto> updateTerapeutas(@PathVariable Integer id, @RequestBody TerapeutasDto terapeutasDto) {
        return new ResponseEntity<>(terapeutasService.updateTerapeutas(id, terapeutasDto), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTerapeutas(@PathVariable Integer id) {
        terapeutasService.deleteTerapeutasById(id);
        return new ResponseEntity<>("Terapeutas eliminado con exito!", HttpStatus.OK);
    }
}
