package com.example.poo2.leitor;

import com.example.poo2.modelo.Municipio;
import com.example.poo2.excecoes.ArquivoInvalidoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorDados {
    private List<Municipio> municipios;
    
    public LeitorDados() {
        this.municipios = new ArrayList<>();
    }
    
    public void lerArquivo(String caminhoArquivo) throws ArquivoInvalidoException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                processarLinha(linha);
            }
        }
    }
    
    private void processarLinha(String linha) throws ArquivoInvalidoException {
        String[] dados = linha.split(",");
        if (dados.length < 5) {
            throw new ArquivoInvalidoException("Linha com formato inválido: " + linha);
        }
        
        try {
            String nome = dados[0];
            String codigo = dados[1];
            int populacao = Integer.parseInt(dados[2]);
            double area = Double.parseDouble(dados[3]);
            int ano = Integer.parseInt(dados[4]);
            
            municipios.add(new Municipio(nome, codigo, populacao, area, ano));
        } catch (NumberFormatException e) {
            throw new ArquivoInvalidoException("Dados numéricos inválidos na linha: " + linha);
        }
    }
    
    public List<Municipio> getMunicipios() {
        return municipios;
    }
    

}