package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.modelo.entity.Terapeutas;
import dev.juliancamacho.neuroplay.modelo.mapper.TerapeutasMapper;
import dev.juliancamacho.neuroplay.controlador.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapeutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerapeutasServiceImpl implements TerapeutasService {

    @Autowired
    private TerapeutasRepository terapeutasRepository;

    @Autowired
    private TerapeutasMapper terapeutasMapper;

    // CREATE
    @Override
    public TerapeutasDto createTerapeutas(TerapeutasDto terapeutasDto) {
        Terapeutas terapeutas = terapeutasMapper.terapeutasDtoToTerapeutas(terapeutasDto);
        Terapeutas savedTerapeutas = terapeutasRepository.save(terapeutas);
        return terapeutasMapper.terapeutasToTerapeutasDto(savedTerapeutas);
    }

    // SELECT BY ID
    @Override
    public TerapeutasDto getTerapeutas(Integer id) {
        Terapeutas terapeutas = terapeutasRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un terapeutas con ese ID"));
        return terapeutasMapper.terapeutasToTerapeutasDto(terapeutas);
    }

    // SELECT ALL
    @Override
    public List<TerapeutasDto> getAllTerapeutas() {
        List<Terapeutas> terapeutass = terapeutasRepository.findAll();

        return terapeutass.stream().map(
                terapeutasMapper::terapeutasToTerapeutasDto).collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public TerapeutasDto updateTerapeutas(Integer id, TerapeutasDto terapeutasDto) {
        Terapeutas terapeutas = terapeutasRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un terapeutas con ese ID"));

        terapeutas.setEspecialidad(terapeutasDto.getEspecialidad());
        terapeutas.setLicencia(terapeutasDto.getLicencia());
        terapeutas.setBiografia(terapeutasDto.getBiografia());

        Terapeutas updatedTerapeutas = terapeutasRepository.save(terapeutas);

        return terapeutasMapper.terapeutasToTerapeutasDto(updatedTerapeutas);
    }

    // DELETE BY ID
    @Override
    public void deleteTerapeutasById(Integer id) {
        terapeutasRepository.deleteById(id);
    }
}
