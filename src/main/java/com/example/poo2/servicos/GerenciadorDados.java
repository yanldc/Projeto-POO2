package com.example.poo2.servicos;

import com.example.poo2.modelo.Estado;
import com.example.poo2.modelo.Municipio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDados {
    private List<Estado> estados;
    private Map<String, Estado> estadosPorSigla;
    
    public GerenciadorDados() {
        this.estados = new ArrayList<>();
        this.estadosPorSigla = new HashMap<>();
    }
    
    public void adicionarEstado(Estado estado) {
        estados.add(estado);
        estadosPorSigla.put(estado.getSigla(), estado);
    }
    
    public Estado buscarEstadoPorSigla(String sigla) {
        return estadosPorSigla.get(sigla);
    }
    
    public List<Estado> getEstados() {
        return estados;
    }
    
    public List<Municipio> obterTodosMunicipios() {
        List<Municipio> todosMunicipios = new ArrayList<>();
        for (Estado estado : estados) {
            todosMunicipios.addAll(estado.getMunicipios());
        }
        return todosMunicipios;
    }
}