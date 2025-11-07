package com.example.poo2.javafx;

import com.example.poo2.dao.MunicipioDAO;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardHorizontalController implements Initializable {

    @FXML
    private BarChart<Number, String> barChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private ProgressIndicator progressBar;

    @FXML
    private ProgressIndicator progressPie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarGraficos();
        carregarBarChart();
        carregarPieChart();
    }

    private void configurarGraficos() {
        xAxis.setLabel("População (Milhões)");
        yAxis.setLabel("Estados");
        yAxis.setTickLabelsVisible(true);

        // Define um tamanho MÍNIMO para o eixo Y
        yAxis.setMinWidth(120.0);
        yAxis.setPrefWidth(120.0);

        // Desabilitar a animação é a correção mais comum para este bug
        barChart.setAnimated(false);
        barChart.setLegendVisible(false);

        pieChart.setLegendVisible(true);
        pieChart.setAnimated(true);
    }

    private void carregarBarChart() {
        Task<Map<String, Long>> task = new Task<Map<String, Long>>() {
            @Override
            protected Map<String, Long> call() throws Exception {
                MunicipioDAO municipioDAO = new MunicipioDAO();
                return municipioDAO.buscarPopulacaoPorEstado();
            }
        };

        progressBar.setVisible(true);
        barChart.setVisible(false);

        task.setOnSucceeded(e -> {
            Map<String, Long> dados = task.getValue();
            atualizarBarChart(dados);
            progressBar.setVisible(false);
            barChart.setVisible(true);
        });

        task.setOnFailed(e -> {
            progressBar.setVisible(false);
            barChart.setVisible(true);

            Throwable exception = task.getException();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao carregar dados");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        });

        new Thread(task).start();
    }

    private void atualizarBarChart(Map<String, Long> dados) {
        XYChart.Series<Number, String> series = new XYChart.Series<>();
        series.setName("População");

        List<Map.Entry<String, Long>> top5 = dados.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        int numCategorias = top5.size();
        double pixelsPorBarra = 50.0;
        double paddingVertical = 80.0;
        double alturaIdeal = (numCategorias * pixelsPorBarra) + paddingVertical;

        // Define a altura mínima e preferencial
        barChart.setMinHeight(alturaIdeal);
        barChart.setPrefHeight(alturaIdeal);

        top5.stream()
                .sorted((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .forEach(entry -> {
                    double populacaoMilhoes = entry.getValue() / 1_000_000.0;
                    series.getData().add(new XYChart.Data<>(populacaoMilhoes, entry.getKey()));
                });

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    private void carregarPieChart() {
        Task<Map<String, Long>> task = new Task<Map<String, Long>>() {
            @Override
            protected Map<String, Long> call() throws Exception {
                Thread.sleep(500);
                MunicipioDAO municipioDAO = new MunicipioDAO();
                return municipioDAO.buscarPopulacaoPorEstado();
            }
        };

        progressPie.setVisible(true);
        pieChart.setVisible(false);

        task.setOnSucceeded(e -> {
            Map<String, Long> dados = task.getValue();
            atualizarPieChart(dados);
            progressPie.setVisible(false);
            pieChart.setVisible(true);
        });

        new Thread(task).start();
    }

    private void atualizarPieChart(Map<String, Long> dados) {
        dados.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> {
                    double populacaoMilhoes = entry.getValue() / 1_000_000.0;
                    pieChart.getData().add(new PieChart.Data(
                            entry.getKey() + " (" + String.format("%.1fM", populacaoMilhoes) + ")",
                            entry.getValue()));
                });
    }
}