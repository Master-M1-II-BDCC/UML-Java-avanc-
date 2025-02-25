package models;

import exceptions.NombreNegatifException;

public class EntierNaturel {
    private int val;

    public EntierNaturel(int val) throws NombreNegatifException {
        if (val < 0) {
            throw new NombreNegatifException("Valeur négative interdite", val);
        }
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) throws NombreNegatifException {
        if (val < 0) {
            throw new NombreNegatifException("Valeur négative interdite", val);
        }
        this.val = val;
    }

    public void decrementer() throws NombreNegatifException {
        if (val == 0) {
            throw new NombreNegatifException("Impossible de décrémenter en dessous de zéro", val);
        }
        val--;
    }
}
