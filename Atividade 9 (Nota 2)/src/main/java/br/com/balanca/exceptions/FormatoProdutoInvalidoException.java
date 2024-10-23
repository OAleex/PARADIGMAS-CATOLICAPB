package br.com.balanca.exceptions;

public class FormatoProdutoInvalidoException extends RuntimeException {
    public FormatoProdutoInvalidoException(String message) {
        super(message);
    }
}