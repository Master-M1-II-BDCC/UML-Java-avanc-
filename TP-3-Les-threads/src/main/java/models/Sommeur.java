package models;

import java.util.concurrent.Callable;

public class Sommeur implements Callable<Integer> {
    private int[] tableau;
    private int debut, fin;

    public Sommeur(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public Integer call() {
        int somme = 0;
        for (int i = debut; i < fin; i++) {
            somme += tableau[i];
        }
        return somme;
    }
}


