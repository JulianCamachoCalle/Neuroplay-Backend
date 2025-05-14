package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.controlador.service.interfaces.EjercicioService;
import dev.juliancamacho.neuroplay.modelo.dto.EjerciciosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ejercicios")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class EjercicioController {

    private final EjercicioService ejercicioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EjerciciosDto createEjercicio(@RequestBody EjerciciosDto ejercicioDto) {
        return ejercicioService.createEjercicio(ejercicioDto);
    }

    @GetMapping("/{id}")
    public EjerciciosDto getEjercicioById(@PathVariable Integer id) {
        return ejercicioService.getEjercicioById(id);
    }

    @GetMapping("/terapia/{terapiaId}")
    public List<EjerciciosDto> getEjerciciosByTerapia(@PathVariable Integer terapiaId) {
        return ejercicioService.getEjerciciosByTerapia(terapiaId);
    }

    @GetMapping("/tipo/{tipo}")
    public List<EjerciciosDto> filterEjerciciosByTipo(@PathVariable String tipo) {
        return ejercicioService.filterEjerciciosByTipo(tipo);
    }

    @PutMapping("/{id}")
    public EjerciciosDto updateEjercicio(@PathVariable Integer id, @RequestBody EjerciciosDto ejercicioDto) {
        return ejercicioService.updateEjercicio(id, ejercicioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEjercicio(@PathVariable Integer id) {
        ejercicioService.deleteEjercicio(id);
    }
}
