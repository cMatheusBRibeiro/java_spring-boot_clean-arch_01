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

    private String identificadorInternoEstudante;
    private String nomeEstudante;
    private String emailEstudante;
    private Integer idadeEstudante;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        cadastrarEstudanteUseCase = CadastrarEstudanteUseCase.create(estudanteGateway);
        identificadorInternoEstudante = "abcd";
        nomeEstudante = "john";
        emailEstudante = "john@email.com";
        idadeEstudante = 30;
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
        assertEquals(identificadorInternoEstudante, estudanteCriado.getIdentificadorInterno());
        assertEquals(nomeEstudante, estudanteCriado.getNome());
        assertEquals(emailEstudante, estudanteCriado.getEmail());
        assertEquals(idadeEstudante, estudanteCriado.getIdade());
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
        return new NovoEstudanteDTO(nomeEstudante, emailEstudante, idadeEstudante);
    }

    private Estudante criarEstudante() {
        return Estudante.create(identificadorInternoEstudante, nomeEstudante, emailEstudante, idadeEstudante);
    }

}
