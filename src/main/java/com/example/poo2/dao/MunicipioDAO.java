package com.example.poo2.dao;

import com.example.poo2.modelo.Estado;
import com.example.poo2.modelo.Municipio;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MunicipioDAO {
    
    public void salvar(Municipio municipio, int idEstado) throws SQLException {
        String sql = "INSERT INTO municipio (nome, populacao, ano, id_estado) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, municipio.getNome());
            stmt.setInt(2, municipio.getPopulacao());
            stmt.setInt(3, municipio.getAno());
            stmt.setInt(4, idEstado);
            stmt.executeUpdate();
        }
    }
    
    public List<Municipio> buscarPorEstado(Estado estado) throws SQLException {
        List<Municipio> municipios = new ArrayList<>();
        String sql = "SELECT * FROM municipio WHERE id_estado = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, Integer.parseInt(estado.getCodigo()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Municipio municipio = new Municipio(
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("id")),
                    rs.getInt("populacao"),
                    0.0, // área não está no banco
                    rs.getInt("ano")
                );
                municipios.add(municipio);
            }
        }
        return municipios;
    }
    
    public Map<String, Long> buscarPopulacaoPorEstado() throws SQLException {
        Map<String, Long> dados = new HashMap<>();
        String sql = """
            SELECT e.nome, SUM(m.populacao) as total_populacao 
            FROM estado e 
            JOIN municipio m ON e.id = m.id_estado 
            GROUP BY e.id, e.nome
            """;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                dados.put(rs.getString("nome"), rs.getLong("total_populacao"));
            }
        }
        return dados;
    }
}