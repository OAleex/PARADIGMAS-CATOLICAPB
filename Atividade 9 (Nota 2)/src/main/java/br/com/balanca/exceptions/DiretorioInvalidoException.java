package br.com.balanca.exceptions;

public class DiretorioInvalidoException extends RuntimeException {
    public DiretorioInvalidoException(String message) {
        super(message);
    }
}