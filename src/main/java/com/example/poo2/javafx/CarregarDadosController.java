package com.example.poo2.javafx;

import com.example.poo2.dao.EstadoDAO;
import com.example.poo2.dao.MunicipioDAO;
import com.example.poo2.excecoes.ArquivoInvalidoException;
import com.example.poo2.leitor.LeitorDados;
import com.example.poo2.modelo.Estado;
import com.example.poo2.modelo.Municipio;
import javafx.scene.control.Alert;
import java.sql.SQLException;

public class CarregarDadosController {
    
    public static void processarArquivo(String caminhoArquivo) {
        try {
            LeitorDados leitor = new LeitorDados();
            leitor.lerArquivo(caminhoArquivo);
            
            EstadoDAO estadoDAO = new EstadoDAO();
            MunicipioDAO municipioDAO = new MunicipioDAO();
            
            // Salvar dados no banco
            for (Municipio municipio : leitor.getMunicipios()) {
                // Criar estado fictício baseado no código do município
                String siglaEstado = municipio.getCodigo().substring(0, 2);
                Estado estado = new Estado("Estado " + siglaEstado, "1", siglaEstado);
                
                estadoDAO.salvar(estado);
                municipioDAO.salvar(municipio, 1); // ID fixo para exemplo
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
}