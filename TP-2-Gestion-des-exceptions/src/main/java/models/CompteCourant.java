package models;

import exceptions.FondsInsuffisantsException;

public class CompteCourant extends CompteBancaire {
    private double decouvertAutorise;

    public CompteCourant(String numero, String titulaire, double solde, double decouvertAutorise) {
        super(numero, titulaire, solde);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void retrait(double montant) throws FondsInsuffisantsException {
        if (montant > solde + decouvertAutorise) {
            throw new FondsInsuffisantsException("Fonds insuffisants, découvert dépassé !");
        }
        solde -= montant;
        System.out.println("Retrait réussi avec découvert. Nouveau solde : " + solde);
    }
}
