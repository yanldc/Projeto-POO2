package com.example.poo2.indicadores;

import com.example.poo2.modelo.Municipio;

public class CrescimentoPopulacional implements Indicador {
    private Municipio municipioAtual;
    private Municipio municipioAnterior;
    
    public CrescimentoPopulacional(Municipio municipioAtual, Municipio municipioAnterior) {
        this.municipioAtual = municipioAtual;
        this.municipioAnterior = municipioAnterior;
    }
    
    @Override
    public double calcular() {
        if (municipioAnterior.getPopulacao() == 0) {
            return 0;
        }
        return ((double)(municipioAtual.getPopulacao() - municipioAnterior.getPopulacao()) 
                / municipioAnterior.getPopulacao()) * 100;
    }
}