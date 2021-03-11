/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;

import interfaces.IProduit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class ServiceProduit implements IProduit {

    @Override
    public void ajouterProduit(Produit p) {
        
        try {
            
        String requete= "INSERT INTO produit (id_categorie, nom_produit, prix_unitaire, quantite, image, description ) "
               + "VALUES (?,?,?,?,?,?)  ";
        
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            pst.setInt(1, p.getIdCategorie());
            pst.setString(2, p.getNomProduit());
            pst.setFloat(3,p.getPrixUnitaire());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImage());
            pst.setString(6, p.getDescription());
            
             pst.executeUpdate();
           
            System.out.println("produit ajouter !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimmerProduit(int idSupp) {
        
             try {
            String requete = "DELETE FROM produit where id_produit='"+idSupp+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierProduit(Produit p, int idMod) {
        
             try {
            String requete = "UPDATE produit SET id_categorie=?, nom_produit=?, prix_unitaire=?, quantite=?, image=?, description=? WHERE id_produit='"+idMod+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.setInt(1, p.getIdCategorie());
            pst.setString(2, p.getNomProduit());
            pst.setFloat(3,p.getPrixUnitaire());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImage());
            pst.setString(6, p.getDescription());
            
            pst.executeUpdate();
            System.out.println("produit modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     *
     * @return
     */
    @Override
    public List<Produit> displayProduit() {
        
               List<Produit> prodsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM produit";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               Produit p = new Produit();
               ImageView img = new ImageView();
                
               Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("image"))));
                      img.setImage(image);
                  img.setPreserveRatio(true);
                 
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             
           
              
               p.setIdProduit(rs.getInt("id_produit"));
                p.setIdCategorie(rs.getInt("id_categorie"));
                p.setNomProduit(rs.getString("nom_produit"));
                p.setPrixUnitaire(rs.getFloat("prix_unitaire"));
                p.setQuantite(rs.getInt("quantite"));
                p.setImagedisplay(img);
                p.setDescription(rs.getString("description"));
                
              
              prodsList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prodsList;

    }
    
    
    @Override
        public List<Produit> displayProduit2() {
        
               List<Produit> prodsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM produit";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               Produit p = new Produit();
               ImageView img = new ImageView();
                
               Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("image"))));
                      img.setImage(image);
                  img.setPreserveRatio(true);
                 
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             
           
              
       
                p.setNomProduit(rs.getString("nom_produit"));
                p.setPrixUnitaire(rs.getFloat("prix_unitaire"));
             
                p.setImagedisplay(img);
                p.setDescription(rs.getString("description"));
                
              
              prodsList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prodsList;

    }
}
