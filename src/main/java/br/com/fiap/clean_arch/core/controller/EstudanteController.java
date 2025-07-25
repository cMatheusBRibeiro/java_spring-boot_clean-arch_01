package br.com.fiap.clean_arch.core.controller;

import br.com.fiap.clean_arch.core.dto.EstudanteDTO;
import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;
import br.com.fiap.clean_arch.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.clean_arch.core.gateway.EstudanteGateway;
import br.com.fiap.clean_arch.core.gateway.IDataSource;
import br.com.fiap.clean_arch.core.presenters.EstudantePresenter;
import br.com.fiap.clean_arch.core.usecases.CadastrarEstudanteUseCase;

public class EstudanteController {

    private final IDataSource dataStorageSource;

    private EstudanteController (IDataSource dataSource) {
        this.dataStorageSource = dataSource;
    }

    public static EstudanteController create(IDataSource dataSource) {
        return new EstudanteController(dataSource);
    }

    public EstudanteDTO cadastrar (NovoEstudanteDTO novoEstudanteDTO) {
        var estudanteGateway = EstudanteGateway.create(this.dataStorageSource);
        var useCase = CadastrarEstudanteUseCase.create(estudanteGateway);

        try {
            var estudante = useCase.run(novoEstudanteDTO);
            return EstudantePresenter.toDTO(estudante);
        } catch (EstudanteJaExisteException e) {
            return null;
        }
    }

    public EstudanteDTO buscarPorNome (String nome) {
        /* ... */
        return null;
    }

}
