package com.example.poo2.javafx;

import com.example.poo2.dao.MunicipioDAO;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    
    @FXML
    private BarChart<String, Number> barChart;
    
    @FXML
    private ProgressIndicator progressIndicator;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarDadosGrafico();
    }
    
    private void carregarDadosGrafico() {
        // Criar Task para executar em thread separada
        Task<Map<String, Long>> task = new Task<Map<String, Long>>() {
            @Override
            protected Map<String, Long> call() throws Exception {
                MunicipioDAO municipioDAO = new MunicipioDAO();
                return municipioDAO.buscarPopulacaoPorEstado();
            }
        };
        
        // Mostrar indicador de progresso
        progressIndicator.setVisible(true);
        barChart.setVisible(false);
        
        // Configurar callback de sucesso
        task.setOnSucceeded(e -> {
            Map<String, Long> dados = task.getValue();
            atualizarGrafico(dados);
            progressIndicator.setVisible(false);
            barChart.setVisible(true);
        });
        
        // Configurar callback de falha
        task.setOnFailed(e -> {
            progressIndicator.setVisible(false);
            barChart.setVisible(true);
            
            Throwable exception = task.getException();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao carregar dados");
            
            if (exception instanceof SQLException) {
                alert.setContentText("Erro de conexão com o banco de dados: " + exception.getMessage());
            } else {
                alert.setContentText("Erro inesperado: " + exception.getMessage());
            }
            alert.showAndWait();
        });
        
        // Executar task em thread separada
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    private void atualizarGrafico(Map<String, Long> dados) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("População");
        
        for (Map.Entry<String, Long> entry : dados.entrySet()) {
            // Validação de dados
            if (entry.getValue() < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dados Inválidos");
                alert.setHeaderText("Valor de população inválido");
                alert.setContentText("Estado " + entry.getKey() + " possui população negativa.");
                alert.showAndWait();
                continue;
            }
            
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        
        barChart.getData().clear();
        barChart.getData().add(series);
    }
}