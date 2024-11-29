package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Antigeno {
    private ArrayList umAntigeno;

    public Antigeno() {
        umAntigeno = new ArrayList(121);
        this.addLine(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.addLine(0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0);
        this.addLine(0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0);
        this.addLine(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void addLine(int x1, int x2, int x3, int x4, int x5,
            int x6, int x7, int x8, int x9, int x10, int x11) {
        getUmAntigeno().add(x1);
        getUmAntigeno().add(x2);
        getUmAntigeno().add(x3);
        getUmAntigeno().add(x4);
        getUmAntigeno().add(x5);
        getUmAntigeno().add(x6);
        getUmAntigeno().add(x7);
        getUmAntigeno().add(x8);
        getUmAntigeno().add(x9);
        getUmAntigeno().add(x10);
        getUmAntigeno().add(x11);
    }

    public String toString() {
        int novaLinha = 0;
        String ret = "";
        for (int i = 0; i < getUmAntigeno().size(); i++) {
            if (novaLinha >= 10) {
                novaLinha = 0;
                ret += getUmAntigeno().get(i) + "\n";
            } else {
                novaLinha++;
                ret += getUmAntigeno().get(i);
            }
        }
        return ret;
    }
    // getters e setters

    public ArrayList getUmAntigeno() {
        return umAntigeno;
    }

    public void setUmAntigeno(ArrayList umAntigeno) {
        this.umAntigeno = umAntigeno;
    }
}

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

public class Teste {
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
            System.out.println("Cont = " + cont);
            int novos = ag.getPopulacao() - hipermutacao.size();
            hipermutacao.addAll(ag.criaPopulacao(novos)); // Agora criaPopulacao pode receber o parâmetro
            ag.setAnticorpos(hipermutacao);
            cont++;
        }
    }
}