/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author mhamdi iheb
 */
public class Commande {
    
        private int idCmd;
    private int idUser;
    private String adrLivraison;
    private String country;
    private int postCode;
    private String dateCommande;
    private double totalPrice;
    private String status;
    private String listeProduit;

    public Commande() {
    }

    public Commande(int idCmd, int idUser, String adrLivraison, String country, int postCode, String dateCommande, double totalPrice, String status, String listeProduit) {
        this.idCmd = idCmd;
        this.idUser = idUser;
        this.adrLivraison = adrLivraison;
        this.country = country;
        this.postCode = postCode;
        this.dateCommande = dateCommande;
        this.totalPrice = totalPrice;
        this.status = status;
        this.listeProduit = listeProduit;
    }

    public int getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(int idCmd) {
        this.idCmd = idCmd;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAdrLivraison() {
        return adrLivraison;
    }

    public void setAdrLivraison(String adrLivraison) {
        this.adrLivraison = adrLivraison;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(String listeProduit) {
        this.listeProduit = listeProduit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idCmd);
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
        final Commande other = (Commande) obj;
        if (!Objects.equals(this.idCmd, other.idCmd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCmd=" + idCmd + ", idUser=" + idUser + ", adrLivraison=" + adrLivraison + ", country=" + country + ", postCode=" + postCode + ", dateCommande=" + dateCommande + ", totalPrice=" + totalPrice + ", status=" + status + ", listeProduit=" + listeProduit + '}';
    }
    
    
    
}
