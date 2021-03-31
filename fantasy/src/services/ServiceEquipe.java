/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Equipe;
import interfaces.IServiceEquipe;
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
import tools.MyConnection;

/**
 *
 * @author PC
 */
public class ServiceEquipe implements IServiceEquipe {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addEquipe(Equipe e) {
        try {
            String requete = "INSERT INTO equipe (nom_equipe,logo_equipe,stade)"
                    + "VALUES ('" + e.getNom() + "','" + e.getLogo_equipe() + "','" + e.getStade() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Equipe ajoutée");
            System.out.println(e.getLogo_equipe());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Equipe> getEquipes() {
        List<Equipe> ListEquipe = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Equipe";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Equipe e = new Equipe();
                ImageView img = new ImageView();
                img.setFitWidth(120);
                img.setFitHeight(130);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_equipe"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
                }

                e.setIdG(rs.getInt("id_equipe"));
                e.setNom(rs.getString("nom_equipe"));
                e.setLogo_equipe(rs.getString("logo_equipe"));
                e.setLogoimage(img);
                e.setstade(rs.getString("stade"));
                
                ListEquipe.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListEquipe;
    }

    @Override
    public void deleteEquipe(Equipe e) {
        try {
            String requete = "DELETE FROM Equipe where id_equipe=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println("Equipe supprimée");
            String requete1 = "DELETE FROM Joueur where id_equipe=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1, e.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateEquipe(Equipe e) {
        try {

            Statement stm = cnx.createStatement();
            String query = "UPDATE Equipe SET nom_equipe= '" + e.getNom() + "', logo_equipe= '" + e.getLogo_equipe() + "', stade= '" + e.getStade() + "' WHERE Id_equipe='" + e.getId() + "'";
            stm.executeUpdate(query);
            System.out.println("Equipe modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Equipe getById(int id) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `equipe` where id_equipe= '" + id + "'";
            ResultSet rst = st.executeQuery(query);

            Equipe Eq = new Equipe();
            while (rst.next()) {

                Eq.setIdG(rst.getInt("id_equipe"));
                Eq.setNom(rst.getString("nom_equipe"));
                Eq.setLogo_equipe(rst.getString("logo_equipe"));

            }
            return Eq;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
@Override
    public Equipe getByName(String name) {
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `equipe` where nom_equipe= '" + name + "'";
            ResultSet rst = stm.executeQuery(query);

            Equipe j1 = new Equipe();

            while (rst.next()) {

                j1.setIdG(rst.getInt("id_equipe"));
                j1.setNom(rst.getString("nom_equipe"));

            }
            return j1;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    @Override
   public List<Equipe> SearchTeam(String character) {
        List<Equipe> ListEquipe = new ArrayList<>();
        try {

            String req = "SELECT * FROM equipe where nom_equipe  LIKE '%" + character + "%'";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Equipe j = new Equipe();
                ImageView img = new ImageView();
                img.setFitWidth(120);
                img.setFitHeight(130);

                Image image;
                try {
                    image = new Image(new FileInputStream((rs.getString("logo_equipe"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                j.setIdG(rs.getInt("id_equipe"));
                j.setNom(rs.getString("nom_equipe"));
                j.setLogoimage(img);
                j.setLogo_equipe(rs.getString("logo_equipe"));
                j.setstade(rs.getString("stade"));

                ListEquipe.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListEquipe;

    }

}
