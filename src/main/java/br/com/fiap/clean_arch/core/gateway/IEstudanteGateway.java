package br.com.fiap.clean_arch.core.gateway;

import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;
import br.com.fiap.clean_arch.core.entities.Estudante;

public interface IEstudanteGateway {

    Estudante incluir(Estudante estudante);

    Estudante buscarPorNome(String nome);

}
