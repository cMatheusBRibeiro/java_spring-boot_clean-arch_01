package br.com.fiap.clean_arch.core.gateway;

import br.com.fiap.clean_arch.core.dto.EstudanteDTO;
import br.com.fiap.clean_arch.core.dto.NovoEstudanteDTO;

public interface IDataSource {
    EstudanteDTO incluir(NovoEstudanteDTO novoEstudanteDTO);
}
