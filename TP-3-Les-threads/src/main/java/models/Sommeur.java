package models;

public class Sommeur implements Runnable {
    private int[] tableau;
    private int debut, fin;
    private int sommePartielle;

    public Sommeur(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
        this.sommePartielle = 0;
    }

    @Override
    public void run() {
        for (int i = debut; i < fin; i++) {
            sommePartielle += tableau[i];
        }
    }

    public int getSomme() {
        return sommePartielle;
    }
}

