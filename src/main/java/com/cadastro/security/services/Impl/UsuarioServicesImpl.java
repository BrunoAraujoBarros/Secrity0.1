package com.cadastro.security.services.Impl;

import com.cadastro.security.dtos.UsuariosDto;
import com.cadastro.security.models.Usuario;
import com.cadastro.security.repositories.UsuarioRepository;
import com.cadastro.security.services.UsuarioServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicesImpl  implements UsuarioServices {

    PasswordEncoder passwordEncoder;
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioServicesImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuariosDto salvarUsuario(UsuariosDto usuariosDto) {

        Usuario usuarioJaExiste = usuarioRepository.findByLogin(usuariosDto.login());

        if (usuarioJaExiste != null) {
            throw new RuntimeException("Usuario ja existe");
        }

        var passwoedHash = passwordEncoder.encode(usuariosDto.senha());

        var usuario = new Usuario();
        String[] ignoresProperties = {"senha"};
        BeanUtils.copyProperties(usuariosDto, usuario, ignoresProperties);
        usuario.setSenha(passwoedHash);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new UsuariosDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha());
    }

    @Override
    public List<Usuario> buscarUsuario() {
       return usuarioRepository.findAll();
    }
}
