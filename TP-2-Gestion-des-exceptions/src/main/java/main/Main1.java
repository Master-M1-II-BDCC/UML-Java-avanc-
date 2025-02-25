package main;

import exceptions.NombreNegatifException;
import models.EntierNaturel;

public class Main1 {
    public static void main(String[] args) {
        try {
            EntierNaturel entier = new EntierNaturel(5);
            System.out.println("Valeur initiale : " + entier.getVal());

            entier.decrementer();
            System.out.println("Après décrémentation : " + entier.getVal());

            entier.setVal(-3);  // Devrait lever une exception

        } catch (NombreNegatifException e) {
            System.err.println("Erreur : " + e.getMessage() + " (valeur erronée : " + e.getValeurErronee() + ")");
        }
    }
}
