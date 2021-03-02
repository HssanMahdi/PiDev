/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.Adherent;
import entites.Formation;
import entites.Joueur;
import interfaces.IFormation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MyConnection;

/**
 *
 * @author Mahdi
 */
public class FormationCRUD implements IFormation<Formation,Joueur,Adherent>{

// creation de formation doit etre avec la creation de user a
    @Override
     public void creeFormation( Adherent a) {
       try {
 
           String requete= "INSERT INTO formation(id_user)"
                    + "VALUES (?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId_user());
            pst.executeUpdate();
          //  System.out.println("Groupe Crée");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void ajouterJoueurauFormation(Formation f, Joueur j) {
        try {
 
           String requete= "INSERT INTO formjoueur(id_formation,id_joueur)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId());
            pst.setInt(2, j.getIdJoueur());
            pst.executeUpdate();
          //  System.out.println("worked ");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimerJoueurduFormation(Formation f, Joueur j) {
        try {
            String requete = "DELETE FROM formjoueur where id_formation=? AND id_joueur=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId());
            pst.setInt(2, j.getIdJoueur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // supp d'un joueur lezem tkoun ki yetfasakh un joueur mel BD yetfasakh mel les formation lkol j
    @Override
    public void supprimerJoueurduFormation2( Joueur j) {
        try {
            String requete = "DELETE FROM formjoueur where id_joueur=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, j.getIdJoueur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
