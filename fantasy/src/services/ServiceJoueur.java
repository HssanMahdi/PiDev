/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interface.IServiceJoueur;
import Tools.MyConnection;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                    + "VALUES ('" + j.getNomJoueur() + "','" + j.getPrenomJoueur() + "','" + j.getPosition()+"','"+0+"','" + j.getLogoJoueur() +"','" + j.getPrixJoueur() + "','" + j.getIdG() + "')";
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
                    System.out.println(ex.getMessage());
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
            String query = "UPDATE joueur SET nom_joueur= '" + j.getNomJoueur() + "', prenom_joueur= '" + j.getPrenomJoueur() + "', position= '" + j.getPosition() + "',score_joueur= '" +0+ "', logo_joueur= '" + j.getLogoJoueur() + "', prix_joueur= '" + j.getPrixJoueur() + "', id_equipe= '" + j.getIdG() + "' WHERE Id_joueur='" + j.getIdJoueur() + "'";
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
                    System.out.println(ex.getMessage());
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
                    System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
        return playerList;
    }
    
    @Override
     public List<Joueur> TrierPlayer(int i, int id) {
        List<Joueur> playerList = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "";
            if (i == 1) {
                query = "select * from `joueur` WHERE id_equipe= " + id +" ORDER BY prix_joueur ASC";
            } else if (i == 2) {
                query = "select * from `joueur` WHERE id_equipe= " + id +" ORDER BY position ASC";
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
                    System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
        return playerList;
    }

    
    @Override
    public List<Joueur> SearchPlayer(String character,int id) {
        List<Joueur> ListJoueur = new ArrayList();
        try {

            String req = "SELECT * FROM joueur where nom_joueur  LIKE '%" + character + "%' AND id_equipe = "+id+";";
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
                    System.out.println(ex.getMessage());
                }
                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoimage(img);
                j.setLogoJoueur(rs.getString("logo_joueur"));
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
    public List<Joueur> Search(String character) {
        List<Joueur> ListJoueur = new ArrayList();
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
                    System.out.println(ex.getMessage());
                }
                j.setIdJoueur(rs.getInt("id_joueur"));
                j.setNomJoueur(rs.getString("nom_joueur"));
                j.setPrenomJoueur(rs.getString("prenom_joueur"));
                j.setPosition(rs.getString("position"));
                j.setScoreJoueur(rs.getInt("score_joueur"));
                j.setLogoimage(img);
                j.setLogoJoueur(rs.getString("logo_joueur"));
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
       public ObservableList FillCombo() {
        ObservableList option = FXCollections.observableArrayList();
        String query = "SELECT nom_equipe FROM Equipe";
        try {

           PreparedStatement ps = cnx.prepareStatement(query);
           ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                option.add(rs.getString("nom_equipe"));

            }

            ps.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return option;
    }
}
