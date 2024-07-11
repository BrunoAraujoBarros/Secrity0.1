package com.cadastro.security.dtos;

import com.cadastro.security.enums.RoleEnum;

public record UsuariosDto(
        String nome,
        String login,
        String senha,
        RoleEnum role

) {
}
