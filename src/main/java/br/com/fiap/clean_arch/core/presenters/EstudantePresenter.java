package br.com.fiap.clean_arch.core.presenters;

import br.com.fiap.clean_arch.core.dto.EstudanteDTO;
import br.com.fiap.clean_arch.core.entities.Estudante;

public class EstudantePresenter {

    public static EstudanteDTO toDTO(Estudante estudante) {
        var identificacao = estudante.getIdentificadorInterno();
        var identificacaoOfuscada = identificacao.charAt(1) + "..." + identificacao.charAt(identificacao.length() - 1);

        return new EstudanteDTO(
                identificacaoOfuscada,
                estudante.getNome(),
                estudante.getEmail(),
                estudante.getIdade()
        );
    }

}
