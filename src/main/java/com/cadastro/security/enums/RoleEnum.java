package com.cadastro.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

}
