package com.example.poo2.dao;

import com.example.poo2.modelo.Estado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    
    public void salvar(Estado estado) throws SQLException {
        String sql = "INSERT INTO estado (nome, sigla) VALUES (?, ?) ON DUPLICATE KEY UPDATE nome = VALUES(nome)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getSigla());
            stmt.executeUpdate();
        }
    }
    
    public List<Estado> buscarTodos() throws SQLException {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT * FROM estado";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Estado estado = new Estado(
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("id")),
                    rs.getString("sigla")
                );
                estados.add(estado);
            }
        }
        return estados;
    }
    
    public Estado buscarPorSigla(String sigla) throws SQLException {
        String sql = "SELECT * FROM estado WHERE sigla = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sigla);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Estado(
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("id")),
                    rs.getString("sigla")
                );
            }
        }
        return null;
    }
}