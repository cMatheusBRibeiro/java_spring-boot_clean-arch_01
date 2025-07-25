package br.com.fiap.clean_arch.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstudanteTest {

    private String identificadorInternoEstudante;
    private String nomeEstudante;
    private String emailEstudante;
    private Integer idadeEstudante;

    @BeforeEach
    void setup () {
        identificadorInternoEstudante = "abcd";
        nomeEstudante = "john";
        emailEstudante = "john@email.com";
        idadeEstudante = 30;
    }

    @DisplayName("Cria estudante com sucesso")
    @Test
    void devePermitirCriarEstudante() {
        identificadorInternoEstudante = null;
        var estudante = criarEstudante();

        assertEquals(nomeEstudante, estudante.getNome());
        assertEquals(emailEstudante, estudante.getEmail());
        assertEquals(idadeEstudante, estudante.getIdade());
        assertNull(estudante.getIdentificadorInterno());
    }

    @DisplayName("Cria estudante com identificador interno com sucesso")
    @Test
    void devePermitirCriarEstudanteComIdentificadorInterno() {
        var estudante = criarEstudante();

        assertEquals(nomeEstudante, estudante.getNome());
        assertEquals(emailEstudante, estudante.getEmail());
        assertEquals(idadeEstudante, estudante.getIdade());
        assertEquals(identificadorInternoEstudante, estudante.getIdentificadorInterno());
    }

    @DisplayName("Proíbe a criação de estudante sem nome")
    @Test
    void deveProibirCriarEstudanteSemNome() {
        nomeEstudante = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com nome vazio")
    @Test
    void deveProibirCriarEstudanteComNomeVazio() {
        nomeEstudante = "";
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante sem e-mail")
    @Test
    void deveProibirCriarEstudanteSemEmail() {
        emailEstudante = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com e-mail inválido")
    @Test
    void deveProibirCriarEstudanteComEmailInvalido() {
        emailEstudante = "john@";
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante sem idade")
    @Test
    void deveProibirCriarEstudanteSemIdade() {
        idadeEstudante = null;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    @DisplayName("Proíbe a criação de estudante com idade menor que 18")
    @Test
    void deveProibirCriarEstudanteComIdadeMenorQue18() {
        idadeEstudante = 10;
        assertThrows(IllegalArgumentException.class, this::criarEstudante);
    }

    private Estudante criarEstudante() {
        return Estudante.create(identificadorInternoEstudante, nomeEstudante, emailEstudante, idadeEstudante);
    }

}
