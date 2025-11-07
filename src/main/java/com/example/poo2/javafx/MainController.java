package com.example.poo2.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class MainController {
    
    @FXML
    private Button btnCarregarDados;
    
    @FXML
    private Button btnAbrirDashboard;
    
    @FXML
    private void carregarDados() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar arquivo de dados");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv")
        );
        
        Stage stage = (Stage) btnCarregarDados.getScene().getWindow();
        File arquivo = fileChooser.showOpenDialog(stage);
        
        if (arquivo != null) {
            CarregarDadosController.processarArquivo(arquivo.getAbsolutePath());
        }
    }
    
    @FXML
    private void abrirDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DashboardHorizontal.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dashboard Horizontal - População por Estado");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir dashboard");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}