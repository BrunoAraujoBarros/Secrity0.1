package com.cadastro.security.services;

import com.cadastro.security.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UtenticacaoService  extends UserDetailsService {

    public String obterToken(AuthDto authDto);
    public String validaTokenJwt(String token);
}
