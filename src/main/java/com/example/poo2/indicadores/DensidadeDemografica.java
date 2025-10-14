package com.example.poo2.indicadores;

import com.example.poo2.modelo.Municipio;

public class DensidadeDemografica implements Indicador {
    private Municipio municipio;
    
    public DensidadeDemografica(Municipio municipio) {
        this.municipio = municipio;
    }
    
    @Override
    public double calcular() {
        if (municipio.getArea() == 0) {
            return 0;
        }
        return municipio.getPopulacao() / municipio.getArea();
    }
}