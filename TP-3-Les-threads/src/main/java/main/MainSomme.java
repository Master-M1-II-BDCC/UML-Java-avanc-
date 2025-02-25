package main;

import models.Sommeur;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class MainSomme {
    public static void main(String[] args) {
        int[] tableau = new int[20000];
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = i + 1;
        }

        int nombreThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(nombreThreads);
        List<Future<Integer>> resultats = new ArrayList<>();

        int taillePlage = tableau.length / nombreThreads;

        for (int i = 0; i < nombreThreads; i++) {
            int debut = i * taillePlage;
            int fin = (i == nombreThreads - 1) ? tableau.length : debut + taillePlage;
            Future<Integer> future = executorService.submit(new Sommeur(tableau, debut, fin));
            resultats.add(future);
        }

        int sommeTotale = 0;
        for (Future<Integer> future : resultats) {
            try {
                sommeTotale += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        System.out.println("Somme totale du tableau : " + sommeTotale);
    }
}

