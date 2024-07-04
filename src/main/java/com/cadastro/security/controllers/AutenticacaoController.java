package com.cadastro.security.controllers;

import com.cadastro.security.dtos.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    AuthenticationManager authenticationManager;
    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String autenticar(@RequestBody AuthDto authDto) {

        var usuarioAutenticationTolkien = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
       authenticationManager.authenticate(usuarioAutenticationTolkien);
        return "Tolkien autenticado com sucesso!";
    }
}