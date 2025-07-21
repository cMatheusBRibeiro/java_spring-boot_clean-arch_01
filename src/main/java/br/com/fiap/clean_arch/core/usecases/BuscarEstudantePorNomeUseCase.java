package br.com.fiap.clean_arch.core.usecases;

import br.com.fiap.clean_arch.core.entities.Estudante;
import br.com.fiap.clean_arch.core.exceptions.EstudanteNaoEncontradoException;
import br.com.fiap.clean_arch.core.gateway.IEstudanteGateway;

public class BuscarEstudantePorNomeUseCase {

    private final IEstudanteGateway estudanteGateway;

    private BuscarEstudantePorNomeUseCase (final IEstudanteGateway estudanteGateway) {
        this.estudanteGateway = estudanteGateway;
    }

    public BuscarEstudantePorNomeUseCase create (final IEstudanteGateway estudanteGateway) {
        return new BuscarEstudantePorNomeUseCase(estudanteGateway);
    }

    public Estudante run (final String nome) {
        Estudante estudante = estudanteGateway.buscarPorNome(nome);

        if (estudante == null) {
            throw new EstudanteNaoEncontradoException("Estudante não encontrado");
        }

        return estudante;
    }

}
