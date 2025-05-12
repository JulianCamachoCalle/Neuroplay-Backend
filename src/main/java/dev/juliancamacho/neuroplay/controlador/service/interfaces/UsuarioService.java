package dev.juliancamacho.neuroplay.controlador.service.interfaces;

import dev.juliancamacho.neuroplay.modelo.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.modelo.enums.Role;

import java.util.List;

public interface UsuarioService {

    // CREATE
    UsuarioDto createUsuario(UsuarioDto usuarioDto);

    // SELECT BY ID
    UsuarioDto getUsuario(Integer id);

    // SELECT BY USERNAME/EMAIL
    UsuarioDto getUsuarioByUsername(String username);

    // SELECT ALL
    List<UsuarioDto> getAllUsuarios();

    // SELECT BY ROLE
    List<UsuarioDto> getUsuariosByRole(Role role);

    // UPDATE
    UsuarioDto updateUsuario(Integer id, UsuarioDto usuarioDto);

    // DELETE BY ID
    void deleteUsuarioById(Integer id);

    boolean checkUsernameExists(String username);

    boolean checkTelefonoExists(String telefono);
}
