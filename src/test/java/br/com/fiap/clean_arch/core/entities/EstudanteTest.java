package br.com.fiap.clean_arch.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstudanteTest {

    private String identificadorInterno;
    private String nome;
    private String email;
    private Integer idade;

    @BeforeEach
    void setup () {
        identificadorInterno = "abcd";
        nome = "john";
        email = "john@email.com";
        idade = 30;
    }

    @DisplayName("Cria estudante com sucesso")
    @Test
    void devePermitirCriarEstudante() {
        identificadorInterno = null;
        var estudante = criarEstudante();

        assertEquals(nome, estudante.getNome());
        assertEquals(email, estudante.getEmail());
        assertEquals(idade, estudante.getIdade());
        assertNull(estudante.getIdentificadorInterno());
    }

    @DisplayName("Cria estudante com identificador interno com sucesso")
    @Test
    void devePermitirCriarEstudanteComIdentificadorInterno() {
        var estudante = criarEstudante();

        assertEquals(nome, estudante.getNome());
        assertEquals(email, estudante.getEmail());
        assertEquals(idade, estudante.getIdade());
        assertEquals(identificadorInterno, estudante.getIdentificadorInterno());
    }

    @DisplayName("Proíbe a criação de estudante sem nome")
    @Test
    void deveProibirCriarEstudanteSemNome() {
        nome = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com nome vazio")
    @Test
    void deveProibirCriarEstudanteComNomeVazio() {
        nome = "";
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante sem e-mail")
    @Test
    void deveProibirCriarEstudanteSemEmail() {
        email = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com e-mail inválido")
    @Test
    void deveProibirCriarEstudanteComEmailInvalido() {
        email = "john@";
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante sem idade")
    @Test
    void deveProibirCriarEstudanteSemIdade() {
        idade = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com idade menor que 18")
    @Test
    void deveProibirCriarEstudanteComIdadeMenorQue18() {
        idade = 10;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    private Estudante criarEstudante() {
        return Estudante.create(identificadorInterno, nome, email, idade);
    }

}
