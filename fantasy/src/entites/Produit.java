/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;


/**
 *
 * @author mhamdi iheb
 */
public class Produit {
    
    private int idProduit;
    private int idCategorie;
    private String nomProduit;
    private float prixUnitaire;
    private int quantite;
    private ImageView imagedisplay ;
    
       private String image ;
    private String description;

    public Produit(int idProduit, int idCategorie, String nomProduit, float prixUnitaire, int quantite, ImageView imagedisplay, String image, String description) {
        this.idProduit = idProduit;
        this.idCategorie = idCategorie;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.imagedisplay = imagedisplay;
        this.image = image;
        this.description = description;
    }
    
       public Produit( String nomProduit, float prixUnitaire, ImageView imagedisplay, String description) {
      
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;

        this.imagedisplay = imagedisplay;
      
        this.description = description;
    }
    
    
    
 

    public Produit() {
    }

    public Produit(int idProduit, String nomProduit, float prixUnitaire, int quantite, String sex, String image, String description) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
      
        this.image = image;
        this.description = description;
    }
    
    /**
     *
     * @param idProduit
     * @param idCategorie
     * @param nomProduit
     * @param prixUnitaire
     * @param quantite
     * @param imagedisplay
     * @param description
     */
    public Produit(int idProduit, int idCategorie, String nomProduit, float prixUnitaire, int quantite, ImageView imagedisplay,String description) {
        this.idProduit = idProduit;
        this.idCategorie = idCategorie;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.imagedisplay = imagedisplay;
        
        this.description = description;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public ImageView getImagedisplay() {
        return imagedisplay;
    }

    public void setImagedisplay(ImageView imagedisplay) {
        this.imagedisplay = imagedisplay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", idCategorie=" + idCategorie + ", nomProduit=" + nomProduit + ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", image=" + image + ", description=" + description + "}\n";
    }

   

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    
    
}
