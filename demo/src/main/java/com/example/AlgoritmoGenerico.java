package com.example;

import java.util.ArrayList;
import java.util.Collections;

class AlgoritmoGenetico {
    private Antigeno umAntigeno;
    private ArrayList<Anticorpo> anticorpos;
    private int populacao;

    public AlgoritmoGenetico(int tamPopulacao) {
        populacao = tamPopulacao;
        umAntigeno = new Antigeno();
        anticorpos = criaPopulacao();
    }

    public ArrayList<Anticorpo> criaPopulacao() {
        ArrayList<Anticorpo> tempAnticorpos = new ArrayList<>(getPopulacao());
        for (int i = 0; i < getPopulacao(); i++) {
            tempAnticorpos.add(new Anticorpo());
        }
        return tempAnticorpos;
    }

    public ArrayList<Anticorpo> afinidades() {
        for (Anticorpo anticorpo : anticorpos) {
            anticorpo.setAfinidade(
                    fitness(umAntigeno.getUmAntigeno(), anticorpo.getUmAnticorpo()));
        }
        Collections.sort(anticorpos);
        return anticorpos;
    }

    public int fitness(ArrayList<Integer> umAntigeno, ArrayList<Integer> umAnticorpo) {
        int afinidade = 0;
        for (int i = 0; i < umAnticorpo.size(); i++) {
            if (umAnticorpo.get(i).equals(umAntigeno.get(i))) {
                afinidade++;
            }
        }
        return afinidade;
    }

    public ArrayList<Anticorpo> melhores(ArrayList<Anticorpo> melhores, int quantidade) {
        return new ArrayList<>(melhores.subList(0, Math.min(quantidade, melhores.size())));
    }

    public ArrayList<Anticorpo> clonagem(ArrayList<Anticorpo> melhores, int cl) {
        ArrayList<Anticorpo> clones = new ArrayList<>();
        int totalAfinidades = melhores.stream().mapToInt(Anticorpo::getAfinidade).sum();

        for (Anticorpo anticorpo : melhores) {
            float proportion = (float) anticorpo.getAfinidade() / totalAfinidades;
            int clonagens = Math.round(proportion * cl);

            for (int j = 0; j < clonagens; j++) {
                clones.add(new Anticorpo());
            }
        }
        return clones;
    }

    public ArrayList<Anticorpo> hipermutacao(ArrayList<Anticorpo> clones, float taxaMutacao) {
        for (Anticorpo clone : clones) {
            ArrayList<Integer> genes = clone.getUmAnticorpo();
            for (int i = 0; i < genes.size(); i++) {
                if (Math.random() < taxaMutacao) {
                    genes.set(i, genes.get(i) == 0 ? 1 : 0);
                }
            }
        }
        return clones;
    }

    public int getPopulacao() {
        return populacao;
    }

    public ArrayList<Anticorpo> getAnticorpos() {
        return anticorpos;
    }

    public void setAnticorpos(ArrayList<Anticorpo> anticorpos) {
        this.anticorpos = anticorpos;
    }

    public Antigeno getUmAntigeno() {
        return umAntigeno;
    }

    public void setUmAntigeno(Antigeno umAntigeno) {
        this.umAntigeno = umAntigeno;
    }

    public static class Teste {
        public static void main(String[] args) {
            AlgoritmoGenetico ag = new AlgoritmoGenetico(100);
            ArrayList<Anticorpo> populacao, melhores, clonagem, hipermutacao;
            float taxaMutacao = 0.5f;
            int cont = 0;

            while (cont < 100) {
                populacao = ag.afinidades();
                melhores = ag.melhores(populacao, 15);
                clonagem = ag.clonagem(melhores, 75);
                hipermutacao = ag.hipermutacao(clonagem, taxaMutacao);

                System.out.println("Iteration: " + cont);
                System.out.println("Best affinity: " + melhores.get(0).getAfinidade());

                int novos = ag.getPopulacao() - hipermutacao.size();
                hipermutacao.addAll(ag.criaPopulacao().subList(0, novos));
                ag.setAnticorpos(hipermutacao);

                cont++;
            }
        }
    }
}
