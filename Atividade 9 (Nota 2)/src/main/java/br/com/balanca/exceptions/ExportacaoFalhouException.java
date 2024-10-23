package br.com.balanca.exceptions;

public class ExportacaoFalhouException extends RuntimeException {
    public ExportacaoFalhouException(String message, Throwable cause) {
        super(message, cause);
    }
}
