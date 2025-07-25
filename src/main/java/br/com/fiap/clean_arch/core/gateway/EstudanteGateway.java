package br.com.fiap.clean_arch.core.gateway;

import br.com.fiap.clean_arch.core.dto.EstudanteDTO;
import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;
import br.com.fiap.clean_arch.core.entities.Estudante;

public class EstudanteGateway implements IEstudanteGateway {

    private final IDataSource dataStorageSource;

    private EstudanteGateway(IDataSource dataSource) {
        this.dataStorageSource = dataSource;
    }

    public static EstudanteGateway create (IDataSource dataSource) {
        return new EstudanteGateway(dataSource);
    }

    @Override
    public Estudante incluir(Estudante estudante) {
        final NovoEstudanteDTO novoEstudanteDTO = new NovoEstudanteDTO(
                estudante.getNome(),
                estudante.getEmail(),
                estudante.getIdade()
        );

        final EstudanteDTO estudanteCriado = this.dataStorageSource.incluir(novoEstudanteDTO);

        return Estudante.create(
                estudanteCriado.identificacao(),
                estudanteCriado.nome(),
                estudanteCriado.email(),
                estudanteCriado.idade()
        );
    }

    @Override
    public Estudante buscarPorNome(String nome) {
        return null;
    }

}
