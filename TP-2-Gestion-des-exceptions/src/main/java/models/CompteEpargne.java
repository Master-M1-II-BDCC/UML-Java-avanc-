package models;

public class CompteEpargne extends CompteBancaire {
    private double tauxInteret;

    public CompteEpargne(String numero, String titulaire, double solde, double tauxInteret) {
        super(numero, titulaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public void appliquerInterets() {
        solde += solde * tauxInteret;
        System.out.println("Intérêts appliqués ! Nouveau solde : " + solde);
    }
}

