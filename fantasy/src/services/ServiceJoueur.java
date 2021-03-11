/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interface.IServiceJoueur;
import Tools.MyConnection;
import entities.Equipe;
import entities.Joueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
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

/**
 *
 * @author PC
 */
public class ServiceJoueur implements IServiceJoueur {

    ServiceEquipe SE = new ServiceEquipe();
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addJoueur(Joueur j) {
        try {
            String requete = "INSERT INTO joueur (nom_joueur,prenom_joueur,position,score_joueur,logo_joueur,prix_joueur,id_equipe)"
                    + "VALUES ('" + j.getNomJoueur() + "','" + j.getPrenomJoueur() + "','" + j.getPosition() + "','" + j.getScoreJoueur() + "','" + j.getLogoJoueur() + "','" + j.getPrixJoueur() + "','" + j.getIdG() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Joueur ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Joueur> getJoueurs() {
        List<Joueur> ListJoueur = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Joueur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Joueur j = new Joueur();
                ImageView img = new ImageView();
                img.setFitWidth(130);
                img.setFitHeight(140);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_joueur"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
                }
                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoJoueur(rs.getString("logo_joueur"));
                j.setLogoimage(img);
                j.setPrixJoueur(rs.getInt("prix_joueur"));
                j.setIdG(rs.getInt("id_equipe"));
                String a = SE.getById(j.getIdG()).getNom();
                j.setNomEquipe(a);

                ListJoueur.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListJoueur;
    }

    @Override
    public void deleteJoueur(Joueur j) {
        try {
            String requete = "DELETE FROM joueur where id_joueur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, j.getIdJoueur());
            pst.executeUpdate();
            System.out.println("Joueur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateJoueur(Joueur j) {
        try {

            Statement stm = cnx.createStatement();
            String query = "UPDATE joueur SET nom_joueur= '" + j.getNomJoueur() + "', prenom_joueur= '" + j.getPrenomJoueur() + "', position= '" + j.getPosition() + "',score_joueur= '" + j.getScoreJoueur() + "', logo_joueur= '" + j.getLogoJoueur() + "', prix_joueur= '" + j.getPrixJoueur() + "', id_equipe= '" + j.getIdG() + "' WHERE Id_joueur='" + j.getIdJoueur() + "'";
            stm.executeUpdate(query);
            System.out.println("Joueur modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Joueur> getJoueurEquipe(int id) {
        List<Joueur> ListJoueur = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Joueur where Id_equipe= '" + id + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Joueur j = new Joueur();
                ImageView img = new ImageView();
                img.setFitWidth(130);
                img.setFitHeight(140);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_joueur"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
                }
                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoJoueur(rs.getString("logo_joueur"));
                j.setLogoimage(img);
                j.setPrixJoueur(rs.getInt("prix_joueur"));
                j.setIdG(rs.getInt("id_equipe"));
                String a = SE.getById(j.getIdG()).getNom();
                j.setNomEquipe(a);
                ListJoueur.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListJoueur;
    }

    public List<Joueur> Trier(int i) {
        List<Joueur> playerList = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "";
            if (i == 1) {
                query = "select * from `joueur` ORDER BY prix_joueur ASC";
            } else if (i == 2) {
                query = "select * from `joueur` ORDER BY position ASC";
            }

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

                Joueur j = new Joueur();
                ImageView img = new ImageView();
                img.setFitWidth(130);
                img.setFitHeight(140);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_joueur"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
                }

                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoJoueur(rs.getString("logo_joueur"));
                j.setLogoimage(img);
                j.setPrixJoueur(rs.getInt("prix_joueur"));
                j.setIdG(rs.getInt("id_equipe"));
                String a = SE.getById(j.getIdG()).getNom();
                j.setNomEquipe(a);

                playerList.add(j);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;
    }

    @Override
    public List<Joueur> SearchPlayer(String character) {
        List<Joueur> ListJoueur = new ArrayList<>();
        try {

            String req = "SELECT * FROM joueur where nom_joueur  LIKE '%" + character + "%'";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Joueur j = new Joueur();
                ImageView img = new ImageView();
                img.setFitWidth(120);
                img.setFitHeight(130);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_joueur"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
                }
                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoimage(img);
                j.setLogoJoueur(rs.getString("logo_joueur"));
                j.setPrixJoueur(rs.getInt("prix_joueur"));
                String a = SE.getById(j.getIdG()).getNom();
                j.setNomEquipe(a);
                j.setIdG(rs.getInt("id_equipe"));
                ListJoueur.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListJoueur;

    }
}
