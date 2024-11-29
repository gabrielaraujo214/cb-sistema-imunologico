package com.example;

import java.util.ArrayList;

class Anticorpo implements Comparable<Anticorpo> {
    private ArrayList<Integer> umAnticorpo;
    private int afinidade;

    // Construtor padrão
    public Anticorpo() {
        umAnticorpo = new ArrayList<>(121);
        int valor;
        for (int i = 0; i < 121; i++) {
            valor = Math.toIntExact(Math.round(Math.random()));
            umAnticorpo.add(i, valor);
        }
        afinidade = 0;
    }

    // Construtor de cópia
    public Anticorpo(Anticorpo outro) {
        // Cria uma nova lista de anticorpos e copia os elementos da lista original
        this.umAnticorpo = new ArrayList<>(outro.getUmAnticorpo());
        this.afinidade = outro.getAfinidade();
    }

    // Implementação de Comparable para ordenar anticorpos
    public int compareTo(Anticorpo outroAnticorpo) {
        if (this.getAfinidade() > outroAnticorpo.getAfinidade())
            return -1;
        if (this.getAfinidade() < outroAnticorpo.getAfinidade())
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        int novaLinha = 0;
        StringBuilder ret = new StringBuilder();
        ret.append("\nRanking: ").append(getAfinidade()).append("\n");
        for (int i = 0; i < getUmAnticorpo().size(); i++) {
            if (novaLinha >= 10) {
                novaLinha = 0;
                ret.append(getUmAnticorpo().get(i)).append("\n");
            } else {
                novaLinha++;
                ret.append(getUmAnticorpo().get(i));
            }
        }
        return ret.toString();
    }

    // Getters e Setters
    public ArrayList<Integer> getUmAnticorpo() {
        return umAnticorpo;
    }

    public void setUmAnticorpo(ArrayList<Integer> umAnticorpo) {
        this.umAnticorpo = umAnticorpo;
    }

    public int getAfinidade() {
        return afinidade;
    }

    public void setAfinidade(int afinidade) {
        this.afinidade = afinidade;
    }
}
