package br.com.fiap.clean_arch.core.exceptions;

public class EstudanteNaoEncontradoException extends RuntimeException {
    public EstudanteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
