package models;

import exceptions.FondsInsuffisantsException;

public class CompteBancaire {
    protected String numero;
    protected double solde;
    protected String titulaire;

    public CompteBancaire(String numero, String titulaire, double solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public void depot(double montant) {
        solde += montant;
        System.out.println("Dépôt réussi ! Nouveau solde : " + solde);
    }

    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > solde) {
            throw new FondsInsuffisantsException("Fonds insuffisants !");
        }
        solde -= montant;
        System.out.println("Retrait réussi ! Nouveau solde : " + solde);
    }

    public void afficherSolde() {
        System.out.println("Compte : " + numero + ", Titulaire : " + titulaire + ", Solde : " + solde);
    }
}

