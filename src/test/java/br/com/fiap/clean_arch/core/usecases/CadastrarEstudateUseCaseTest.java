package br.com.fiap.clean_arch.core.usecases;

import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;
import br.com.fiap.clean_arch.core.entities.Estudante;
import br.com.fiap.clean_arch.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.clean_arch.core.gateway.IEstudanteGateway;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

public class CadastrarEstudateUseCaseTest {

    @Mock
    private IEstudanteGateway estudanteGateway;
    private CadastrarEstudanteUseCase cadastrarEstudanteUseCase;

    private AutoCloseable mock;

    private String identificadorInterno;
    private String nome;
    private String email;
    private Integer idade;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        cadastrarEstudanteUseCase = CadastrarEstudanteUseCase.create(estudanteGateway);
        identificadorInterno = "abcd";
        nome = "john";
        email = "john@email.com";
        idade = 30;
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Cadastra estudante com sucesso")
    @Test
    void devePermitirCadastrarEstudante () {
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(null);
        when(estudanteGateway.incluir(any(Estudante.class)))
                .thenReturn(criarEstudante());

        var estudanteCriado = cadastrarEstudanteUseCase.run(novoEstudanteDTO);

        verify(estudanteGateway, times(1)).incluir(any(Estudante.class));
        assertEquals(identificadorInterno, estudanteCriado.getIdentificadorInterno());
        assertEquals(nome, estudanteCriado.getNome());
        assertEquals(email, estudanteCriado.getEmail());
        assertEquals(idade, estudanteCriado.getIdade());
    }

    @DisplayName("Proíbe cadastrar estudante sem nome")
    @Test
    void deveProibirCadastrarEstudanteSemNome () {
        nome = null;
        var novoEstudanteDTO = criarNovoEstudanteDTO();

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante com nome vazio")
    @Test
    void deveProibirCadastrarEstudanteComNomeVazio () {
        nome = "";
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante sem email")
    @Test
    void deveProibirCadastrarEstudanteSemEmail () {
        email = null;
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante com email invalido")
    @Test
    void deveProibirCadastrarEstudanteComEmailInvalido () {
        email = "john@";
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante sem idade")
    @Test
    void deveProibirCadastrarEstudanteSemIdade () {
        idade = null;
        var novoEstudanteDTO = criarNovoEstudanteDTO();

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante com idade menor que 18")
    @Test
    void deveProibirCadastrarEstudanteComIdadeMenorQue18 () {
        idade = 10;
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    @DisplayName("Proíbe cadastrar estudante que já existe")
    @Test
    void deveProibirCadastrarEstudanteQueJaExiste () {
        var novoEstudanteDTO = criarNovoEstudanteDTO();
        when(estudanteGateway.buscarPorNome(any(String.class))).thenReturn(criarEstudante());

        assertThrows(EstudanteJaExisteException.class, () -> cadastrarEstudanteUseCase.run(novoEstudanteDTO));
        verify(estudanteGateway, times(0)).incluir(any(Estudante.class));
    }

    private NovoEstudanteDTO criarNovoEstudanteDTO() {
        return new NovoEstudanteDTO(nome, email, idade);
    }

    private Estudante criarEstudante() {
        return Estudante.create(identificadorInterno, nome, email, idade);
    }

}
