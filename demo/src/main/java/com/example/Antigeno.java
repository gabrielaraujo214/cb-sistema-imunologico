package com.example;

import java.util.ArrayList;

class Antigeno {
    private ArrayList<Integer> umAntigeno;

    public Antigeno() {
        umAntigeno = new ArrayList<>(121);
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

    public ArrayList<Integer> getUmAntigeno() {
        return umAntigeno;
    }

    public void setUmAntigeno(ArrayList<Integer> umAntigeno) {
        this.umAntigeno = umAntigeno;
    }
}
