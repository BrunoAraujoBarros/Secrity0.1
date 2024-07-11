package com.cadastro.security.controllers;

import com.cadastro.security.dtos.AuthDto;
import com.cadastro.security.services.UtenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    UtenticacaoService utenticacaoService;
    AuthenticationManager authenticationManager;
    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, UtenticacaoService utenticacaoService) {
        this.authenticationManager = authenticationManager;
        this.utenticacaoService = utenticacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String autenticar(@RequestBody AuthDto authDto) {

        var usuarioAutenticationTolkien = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
       authenticationManager.authenticate(usuarioAutenticationTolkien);
        return utenticacaoService.obterToken(authDto);
    }
}
