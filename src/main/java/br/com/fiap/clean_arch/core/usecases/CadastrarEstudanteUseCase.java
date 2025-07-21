package br.com.fiap.clean_arch.core.usecases;

import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;
import br.com.fiap.clean_arch.core.entities.Estudante;
import br.com.fiap.clean_arch.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.clean_arch.core.gateway.IEstudanteGateway;

public class CadastrarEstudanteUseCase {

    private final IEstudanteGateway estudanteGateway;

    private CadastrarEstudanteUseCase(IEstudanteGateway estudanteGateway) {
        this.estudanteGateway = estudanteGateway;
    }

    public static CadastrarEstudanteUseCase create(IEstudanteGateway estudanteGateway) {
        return new CadastrarEstudanteUseCase(estudanteGateway);
    }

    public Estudante run (NovoEstudanteDTO novoEstudanteDTO) {
        final Estudante estudanteExistente = estudanteGateway.buscarPorNome(novoEstudanteDTO.nome());

        if (estudanteExistente != null) {
            throw new EstudanteJaExisteException("Estudante " + novoEstudanteDTO.nome() + " já existe");
        }

        final Estudante novoEstudante = Estudante.create(
                novoEstudanteDTO.nome(),
                novoEstudanteDTO.email(),
                novoEstudanteDTO.idade()
        );

        Estudante estudante = estudanteGateway.incluir(novoEstudante);

        return estudante;
    }

}
