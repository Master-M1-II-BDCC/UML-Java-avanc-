package main;

import exceptions.FondsInsuffisantsException;
import models.CompteBancaire;
import models.CompteCourant;
import models.CompteEpargne;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        ArrayList<CompteBancaire> comptes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        comptes.add(new CompteCourant("123", "Alice", 1000, 500));
        comptes.add(new CompteEpargne("456", "Bob", 2000, 0.05));

        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Afficher les comptes");
            System.out.println("2. Déposer de l'argent");
            System.out.println("3. Retirer de l'argent");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    for (CompteBancaire c : comptes) {
                        c.afficherSolde();
                    }
                    break;

                case 2:
                    System.out.print("Entrez le numéro du compte : ");
                    String numCompte = scanner.next();
                    System.out.print("Entrez le montant : ");
                    double montant = scanner.nextDouble();

                    for (CompteBancaire c : comptes) {
                        if (c.getNumero().equals(numCompte))
                        {
                            c.depot(montant);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Entrez le numéro du compte : ");
                    numCompte = scanner.next();
                    System.out.print("Entrez le montant : ");
                    montant = scanner.nextDouble();

                    for (CompteBancaire c : comptes) {
                        if (c.getNumero().equals(numCompte))
                        {
                            try {
                                c.retrait(montant);
                            } catch (FondsInsuffisantsException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }
                    break;

                case 4:
                    scanner.close();
                    return;
            }
        }
    }
}

