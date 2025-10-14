package com.example.poo2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Estado extends Regiao {
    private String sigla;
    private List<Municipio> municipios;
    
    public Estado(String nome, String codigo, String sigla) {
        super(nome, codigo);
        this.sigla = sigla;
        this.municipios = new ArrayList<>();
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public List<Municipio> getMunicipios() {
        return municipios;
    }
    
    public void adicionarMunicipio(Municipio municipio) {
        this.municipios.add(municipio);
    }
}