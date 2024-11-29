package com.example;

import java.util.ArrayList;

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