package main;

import models.Produit;
import services.MetierProduitImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        MetierProduitImpl metier = new MetierProduitImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Afficher la liste des produits");
            System.out.println("2. Rechercher un produit par id");
            System.out.println("3. Ajouter un nouveau produit");
            System.out.println("4. Supprimer un produit par id");
            System.out.println("5. Quitter");

            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne vide après le nombre

            switch (choix) {
                case 1:
                    System.out.println("Liste des produits :");
                    for (Produit p : metier.getAll()) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Entrez l'id du produit : ");
                    long idRecherche = scanner.nextLong();
                    Produit produit = metier.findById(idRecherche);
                    if (produit != null) {
                        System.out.println("Produit trouvé : " + produit);
                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;

                case 3:
                    System.out.print("ID : ");
                    long id = scanner.nextLong();
                    scanner.nextLine(); // Consommer la ligne vide
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Marque : ");
                    String marque = scanner.nextLine();
                    System.out.print("Prix : ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Description : ");
                    String description = scanner.nextLine();
                    System.out.print("Stock : ");
                    int stock = scanner.nextInt();

                    metier.add(new Produit(id, nom, marque, prix, description, stock));
                    System.out.println("Produit ajouté avec succès !");
                    break;

                case 4:
                    System.out.print("Entrez l'id du produit à supprimer : ");
                    long idSupp = scanner.nextLong();
                    metier.delete(idSupp);
                    System.out.println("Produit supprimé !");
                    break;

                case 5:
                    System.out.println("Programme terminé.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }
}

