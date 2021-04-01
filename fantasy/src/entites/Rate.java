/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author mhamdi iheb
 */
public class Rate {
    private int idRate;
    private int idProduit;
    private double rate;

    public Rate() {
    }

    public Rate(int idRate, int idProduit, double rate) {
        this.idRate = idRate;
        this.idProduit = idProduit;
        this.rate = rate;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "idRate=" + idRate + ", idProduit=" + idProduit + ", rate=" + rate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.idRate;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rate other = (Rate) obj;
        if (this.idRate != other.idRate) {
            return false;
        }
        return true;
    }
    
    
}
