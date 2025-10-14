package com.example.poo2.excecoes;

public class ArquivoInvalidoException extends Exception {
    public ArquivoInvalidoException(String mensagem) {
        super(mensagem);
    }
    
    public ArquivoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}