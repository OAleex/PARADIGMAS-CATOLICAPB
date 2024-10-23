package br.com.balanca.exceptions;

public class ValorProdutoInvalidoException extends RuntimeException {
    public ValorProdutoInvalidoException(String message) {
        super(message);
    }
}