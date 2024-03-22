package com.projetouposunipe.dto;


import com.projetouposunipe.enums.RoleEnum;

public record UsuarioDto(
        String nome,
        String login,
        String senha,
        RoleEnum role
) {
}
