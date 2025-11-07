package com.example.poo2.javafx;

import com.example.poo2.dao.MunicipioDAO;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressIndicator;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardCompletoController implements Initializable {
    
    @FXML private BarChart<String, Number> barChart;
    @FXML private PieChart pieChart;
    @FXML private ProgressIndicator progressBar;
    @FXML private ProgressIndicator progressPie;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarBarChart();
        carregarPieChart();
    }
    
    private void carregarBarChart() {
        Task<Map<String, Long>> taskBar = new Task<Map<String, Long>>() {
            @Override
            protected Map<String, Long> call() throws Exception {
                Thread.sleep(1000); // Simula processamento
                return new MunicipioDAO().buscarPopulacaoPorEstado();
            }
        };
        
        progressBar.setVisible(true);
        taskBar.setOnSucceeded(e -> {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("População");
            taskBar.getValue().forEach((estado, pop) -> 
                series.getData().add(new XYChart.Data<>(estado, pop)));
            barChart.getData().add(series);
            progressBar.setVisible(false);
        });
        
        new Thread(taskBar).start();
    }
    
    private void carregarPieChart() {
        Task<Map<String, Long>> taskPie = new Task<Map<String, Long>>() {
            @Override
            protected Map<String, Long> call() throws Exception {
                Thread.sleep(1500); // Simula processamento diferente
                return new MunicipioDAO().buscarPopulacaoPorEstado();
            }
        };
        
        progressPie.setVisible(true);
        taskPie.setOnSucceeded(e -> {
            taskPie.getValue().forEach((estado, pop) -> 
                pieChart.getData().add(new PieChart.Data(estado, pop)));
            progressPie.setVisible(false);
        });
        
        new Thread(taskPie).start();
    }
}