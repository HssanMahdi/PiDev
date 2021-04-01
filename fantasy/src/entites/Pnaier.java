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
public class Pnaier {
    
    private int idPanier;
    private int idProduit ;
    private int idUser ;
    private int quantite;    

    public Pnaier(int idPanier, int idProduit, int idUser, int quantite) {
        this.idPanier = idPanier;
        this.idProduit = idProduit;
        this.idUser = idUser;
        this.quantite = quantite;
    }

    public Pnaier() {
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idPanier;
        hash = 97 * hash + this.idProduit;
        hash = 97 * hash + this.idUser;
        hash = 97 * hash + this.quantite;
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
        final Pnaier other = (Pnaier) obj;
        if (this.idPanier != other.idPanier) {
            return false;
        }
        if (this.idProduit != other.idProduit) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pnaier{" + "idPanier=" + idPanier + ", idProduit=" + idProduit + ", idUser=" + idUser + ", quantite=" + quantite + '}';
    }
 
    
    
}
