package br.com.fiap.clean_arch.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@EqualsAndHashCode
public class Curso {

    private String nome;
    private boolean ativo;

    private ArrayList<Estudante> estudantes = new ArrayList<>();

    public Curso(String nome, boolean ativo) {
        isNomeValido(nome);

        this.nome = nome;
        this.ativo = ativo;
    }

    private static void isNomeValido(String nome) {
        if (nome.trim().isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

}
