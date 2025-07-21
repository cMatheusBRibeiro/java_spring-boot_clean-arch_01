package br.com.fiap.clean_arch.core.dto;

public record NovoEstudanteDTO(
        String nome,
        String email,
        Integer idade
) {}
