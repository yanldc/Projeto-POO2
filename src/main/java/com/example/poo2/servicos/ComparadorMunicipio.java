package com.example.poo2.servicos;

import com.example.poo2.modelo.Municipio;
import java.util.Comparator;

public class ComparadorMunicipio {
    
    public static class PorPopulacao implements Comparator<Municipio> {
        @Override
        public int compare(Municipio m1, Municipio m2) {
            return Integer.compare(m2.getPopulacao(), m1.getPopulacao()); // Decrescente
        }
    }
    
    public static class PorNome implements Comparator<Municipio> {
        @Override
        public int compare(Municipio m1, Municipio m2) {
            return m1.getNome().compareTo(m2.getNome());
        }
    }
    
    public static class PorArea implements Comparator<Municipio> {
        @Override
        public int compare(Municipio m1, Municipio m2) {
            return Double.compare(m2.getArea(), m1.getArea()); // Decrescente
        }
    }
}