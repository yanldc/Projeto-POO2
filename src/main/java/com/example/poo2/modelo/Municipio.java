package com.example.poo2.modelo;

public class Municipio extends Regiao {
    private int populacao;
    private double area;
    private int ano;
    
    public Municipio(String nome, String codigo, int populacao, double area, int ano) {
        super(nome, codigo);
        this.populacao = populacao;
        this.area = area;
        this.ano = ano;
    }
    
    public int getPopulacao() {
        return populacao;
    }
    
    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }
    
    public double getArea() {
        return area;
    }
    
    public void setArea(double area) {
        this.area = area;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
}