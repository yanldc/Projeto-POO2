package com.example.poo2.modelo;

public class Populacao {
    private int total;
    private int masculina;
    private int feminina;
    private int ano;
    
    public Populacao(int total, int masculina, int feminina, int ano) {
        this.total = total;
        this.masculina = masculina;
        this.feminina = feminina;
        this.ano = ano;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getMasculina() {
        return masculina;
    }
    
    public void setMasculina(int masculina) {
        this.masculina = masculina;
    }
    
    public int getFeminina() {
        return feminina;
    }
    
    public void setFeminina(int feminina) {
        this.feminina = feminina;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
}