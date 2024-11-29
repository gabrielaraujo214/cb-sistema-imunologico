package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class AlgoritmoGenetico {
    private Antigeno umAntigeno;
    private ArrayList<Anticorpo> anticorpos;
    private int populacao;

    public AlgoritmoGenetico(int tamPopulacao) {
        populacao = tamPopulacao;
        umAntigeno = new Antigeno();
        anticorpos = criaPopulacao(tamPopulacao); // Passando o tamanho da população
    }

    // Getter e Setter para 'populacao'
    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    // Getter e Setter para 'umAntigeno'
    public Antigeno getUmAntigeno() {
        return umAntigeno;
    }

    public void setUmAntigeno(Antigeno umAntigeno) {
        this.umAntigeno = umAntigeno;
    }

    // Getter e Setter para 'anticorpos'
    public ArrayList<Anticorpo> getAnticorpos() {
        return anticorpos;
    }

    public void setAnticorpos(ArrayList<Anticorpo> anticorpos) {
        this.anticorpos = anticorpos;
    }

    // Alterado para aceitar o parâmetro int
    public ArrayList<Anticorpo> criaPopulacao(int tamPopulacao) {
        ArrayList<Anticorpo> tempAnticorpos = new ArrayList<>(tamPopulacao);
        for (int i = 0; i < tamPopulacao; i++) {
            tempAnticorpos.add(new Anticorpo());
        }
        return tempAnticorpos;
    }

    public ArrayList<Anticorpo> afinidades() {
        for (int i = 0; i < anticorpos.size(); i++) {
            anticorpos.get(i).setAfinidade(fitness(umAntigeno.getUmAntigeno(),
                    anticorpos.get(i).getUmAnticorpo()));
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
        ArrayList<Anticorpo> listaMelhores = new ArrayList<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            listaMelhores.add(melhores.get(i));
        }
        return listaMelhores;
    }

    public ArrayList<Anticorpo> clonagem(ArrayList<Anticorpo> melhores, int cl) {
        ArrayList<Anticorpo> clones = new ArrayList<>();
        int totalAfinidades = 0, clonagens;
        int cont;
        float totalClonagens, tempAfinidade;
        for (int i = 0; i < melhores.size(); i++) {
            totalAfinidades += melhores.get(i).getAfinidade();
        }
        for (int i = 0; i < melhores.size(); i++) {
            tempAfinidade = melhores.get(i).getAfinidade();
            totalClonagens = (tempAfinidade / totalAfinidades) * cl;
            clonagens = Math.round(totalClonagens);
            cont = 0;
            while (cont < clonagens) {
                clones.add(melhores.get(i));
                cont++;
            }
        }
        return clones;
    }

    public ArrayList<Anticorpo> hipermutacao(ArrayList<Anticorpo> clones, float taxaMutacao) {
        Random rand = new Random();
        ArrayList<Anticorpo> novosClones = new ArrayList<>();

        // Para cada clone na lista de clones
        for (int i = 0; i < clones.size(); i++) {
            Anticorpo clone = clones.get(i);
            Anticorpo cloneMutado = new Anticorpo(clone); // Usando o construtor de cópia

            // Para cada gene do anticorpo
            for (int j = 0; j < clone.getUmAnticorpo().size(); j++) {
                // Verificar se o gene será mutado com base na taxa de mutação
                if (rand.nextFloat() < taxaMutacao) {
                    // Mudar o gene aleatoriamente
                    cloneMutado.getUmAnticorpo().set(j, rand.nextInt(2)); // Exemplo de mudança para 0 ou 1
                }
            }

            // Adicionar o clone mutado à nova lista
            novosClones.add(cloneMutado);
        }

        return novosClones;
    }
}
