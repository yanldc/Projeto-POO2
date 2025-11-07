package com.example.poo2.javafx;

import javafx.scene.control.Alert;

public class ValidadorDados {
    
    public static void validarPopulacao(int populacao) {
        if (populacao < 0) {
            IllegalArgumentException exception = new IllegalArgumentException(
                "População não pode ser negativa: " + populacao
            );
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Valor Incorreto");
            alert.setHeaderText("Dados inválidos");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
            
            throw exception;
        }
    }
    
    public static void validarAno(int ano) {
        int anoAtual = java.time.Year.now().getValue();
        if (ano < 1900 || ano > anoAtual) {
            IllegalArgumentException exception = new IllegalArgumentException(
                "Ano deve estar entre 1900 e " + anoAtual + ": " + ano
            );
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Valor Incorreto");
            alert.setHeaderText("Ano inválido");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
            
            throw exception;
        }
    }
}