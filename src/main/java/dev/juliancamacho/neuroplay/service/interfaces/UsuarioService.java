package dev.juliancamacho.neuroplay.service.interfaces;

import dev.juliancamacho.neuroplay.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

    // CREATE
    UsuarioDto createUsuario(UsuarioDto usuarioDto);

    // SELECT BY ID
    UsuarioDto getUsuario(Integer id);

    // SELECT ALL
    List<UsuarioDto> getAllUsuarios();

    // UPDATE
    UsuarioDto updateUsuario(Integer id, UsuarioDto usuarioDto);

    // DELETE BY ID
    void deleteUsuarioById(Integer id);

    boolean checkUsernameExists(String username);

    boolean checkTelefonoExists(String telefono);
}
