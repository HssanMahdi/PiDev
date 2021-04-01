/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class ServiceCategorie {
    
      
    public void ajouterCategorie(Categorie c) {
  try {
            
        String requete= "INSERT INTO categorie (nom_categorie) "
               + "VALUES (?)  ";
        
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNomCategorie());
           
             pst.executeUpdate();
           
            System.out.println("categorie ajouter !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    
    public void supprimmerCategorie(int idSupp) {
        
         try {
            String requete = "DELETE categorie, produit FROM categorie INNER JOIN produit ON produit.id_categorie=categorie.id_categorie  WHERE categorie.id_categorie='"+idSupp+"'";
 
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.executeUpdate();
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    public void modifierCategorie(Categorie c, int idMod) {
        
           try {
            String requete ="UPDATE categorie INNER JOIN produit ON categorie.id_categorie=produit.id_categorie  SET categorie.nom_categorie=?, produit.nom_categorie=? WHERE categorie.id_categorie= '"+idMod+"' ";//'"+idMod+"'
            //
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getNomCategorie());
            pst.setString(2, c.getNomCategorie());
            
            pst.executeUpdate();
            System.out.println("categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   
    public List<Categorie> displayCategorie() {
        
            List<Categorie> catsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               Categorie c = new Categorie();
                c.setIdCategorie(rs.getInt("id_categorie"));
                c.setNomCategorie(rs.getString(2));
              
               catsList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return catsList;
    }
    
}
