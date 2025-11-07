package com.example.poo2.javafx;

import com.example.poo2.dao.*;
import com.example.poo2.excecoes.ArquivoInvalidoException;
import com.example.poo2.leitor.LeitorDados;
import com.example.poo2.modelo.*;
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.*;

public class CarregarDadosController {
    
    public static void processarArquivo(String caminhoArquivo) {
        try {
            LeitorDados leitor = new LeitorDados();
            leitor.lerArquivo(caminhoArquivo);
            
            EstadoDAO estadoDAO = new EstadoDAO();
            MunicipioDAO municipioDAO = new MunicipioDAO();
            
            limparDados();
            Map<String, String> nomesEstados = obterNomesEstados();
            Set<String> estadosSalvos = new HashSet<>();
            for (Municipio municipio : leitor.getMunicipios()) {
                String siglaEstado = municipio.getCodigo().substring(0, 2);
                
                if (!estadosSalvos.contains(siglaEstado)) {
                    String nomeEstado = nomesEstados.getOrDefault(siglaEstado, gerarNomeEstado(siglaEstado));
                    Estado estado = new Estado(nomeEstado, siglaEstado, siglaEstado);
                    estadoDAO.salvar(estado);
                    estadosSalvos.add(siglaEstado);
                }
            }
            
            for (Municipio municipio : leitor.getMunicipios()) {
                ValidadorDados.validarPopulacao(municipio.getPopulacao());
                ValidadorDados.validarAno(municipio.getAno());
                
                String siglaEstado = municipio.getCodigo().substring(0, 2);
                Estado estado = estadoDAO.buscarPorSigla(siglaEstado);
                if (estado != null) {
                    int idEstado = Integer.parseInt(estado.getCodigo());
                    municipioDAO.salvar(municipio, idEstado);
                }
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Dados carregados com sucesso");
            alert.setContentText("Arquivo processado e dados salvos no banco.");
            alert.showAndWait();
            
        } catch (ArquivoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Arquivo Inválido");
            alert.setHeaderText("Erro no formato do arquivo");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Banco");
            alert.setHeaderText("Falha na conexão com o banco");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro inesperado");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    private static Map<String, String> obterNomesEstados() {
        Map<String, String> nomes = new HashMap<>();
        nomes.put("SP", "São Paulo");
        nomes.put("RJ", "Rio de Janeiro");
        nomes.put("MG", "Minas Gerais");
        nomes.put("RS", "Rio Grande do Sul");
        nomes.put("PR", "Paraná");
        nomes.put("SC", "Santa Catarina");
        nomes.put("BA", "Bahia");
        nomes.put("GO", "Goiás");
        nomes.put("PE", "Pernambuco");
        nomes.put("CE", "Ceará");
        return nomes;
    }
    
    private static String gerarNomeEstado(String sigla) {
        return "Estado de " + sigla;
    }
    
    private static void limparDados() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.prepareStatement("DELETE FROM municipio").executeUpdate();
            conn.prepareStatement("DELETE FROM estado").executeUpdate();
        } catch (SQLException ignored) {
        }
    }
}