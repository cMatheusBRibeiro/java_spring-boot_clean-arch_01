package br.com.fiap.clean_arch.core.dto;

public record EstudanteDTO(
        String identificacao,
        String nome,
        String email,
        Integer idade
) {
}
