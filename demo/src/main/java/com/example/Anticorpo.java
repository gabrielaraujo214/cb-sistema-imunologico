package com.example;

import java.util.ArrayList;

class Anticorpo implements Comparable<Anticorpo> {
    private ArrayList<Integer> umAnticorpo;
    private int afinidade;

    public Anticorpo() {
        umAnticorpo = new ArrayList<>(121);
        for (int i = 0; i < 121; i++) {
            int valor = (int) Math.round(Math.random());
            umAnticorpo.add(valor);
        }
        afinidade = 0;
    }

    @Override
    public int compareTo(Anticorpo outroAnticorpo) {
        return Integer.compare(outroAnticorpo.getAfinidade(), this.getAfinidade());
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("\nRanking: ").append(getAfinidade()).append("\n");
        int novaLinha = 0;

        for (int i = 0; i < umAnticorpo.size(); i++) {
            ret.append(umAnticorpo.get(i));
            novaLinha++;
            if (novaLinha == 10) {
                ret.append("\n");
                novaLinha = 0;
            }
        }
        return ret.toString();
    }

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
