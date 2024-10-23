package br.com.balanca.exceptions;

public class CodigoProdutoInvalidoException extends RuntimeException {
    public CodigoProdutoInvalidoException(String message) {
        super(message);
    }
}
