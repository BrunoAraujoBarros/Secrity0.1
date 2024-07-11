package com.cadastro.security.controllers;

import com.cadastro.security.dtos.UsuariosDto;
import com.cadastro.security.models.Usuario;
import com.cadastro.security.repositories.UsuarioRepository;
import com.cadastro.security.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    UsuarioServices usuarioServices;
    @Autowired
    private UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }
    @PostMapping
    private UsuariosDto salvarUsuario(@RequestBody UsuariosDto usuariosDto) {
        return usuarioServices.salvarUsuario(usuariosDto);
    }
//    @GetMapping("/admin")
//    private List<Usuario> buscarUsuarios() {
//        return usuarioServices.buscarUsuario();
//    }
    @GetMapping("/admin")
    private String getAdmin() {
        return "permissão de administrador";
    }

    @GetMapping("/user")
    private String getUser() {
        return "permissão de usuário";
    }
}
