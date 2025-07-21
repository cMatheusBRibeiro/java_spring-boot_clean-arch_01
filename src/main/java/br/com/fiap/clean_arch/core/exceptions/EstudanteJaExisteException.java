package br.com.fiap.clean_arch.core.exceptions;

public class EstudanteJaExisteException extends RuntimeException {
    public EstudanteJaExisteException(String mensagem) {
        super(mensagem);
    }
}
