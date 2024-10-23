package br.com.balanca.exceptions;

public class TipoProdutoInvalidoException extends RuntimeException {
    public TipoProdutoInvalidoException(String message) {
        super(message);
    }
}
