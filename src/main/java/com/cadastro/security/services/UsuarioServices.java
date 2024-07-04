package com.cadastro.security.services;

import com.cadastro.security.dtos.UsuariosDto;
import com.cadastro.security.models.Usuario;
import com.cadastro.security.repositories.UsuarioRepository;

import java.util.List;

public interface UsuarioServices {
    public UsuariosDto salvarUsuario(UsuariosDto usuariosDto);
    public List<Usuario> buscarUsuario();
}
