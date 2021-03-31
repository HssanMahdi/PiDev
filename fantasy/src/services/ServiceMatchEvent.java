/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.Equipe;
import entites.MatchEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.MyConnection;

/**
 *
 * @author PC
 */
public class ServiceMatchEvent {

    ServiceEquipe SE = new ServiceEquipe();
    Connection cnx = MyConnection.getInstance().getCnx();
    MatchEvent mev = new MatchEvent();

    public void addMatch(MatchEvent me) {
        try {
            System.out.println(me.getDateMatch());
            String requete = "INSERT INTO matchevent (titre,dateMatch,id_equipeA,id_equipeB)"
                    + "VALUES ('" + me.getTitre() + "','" + me.getDateMatch() + "','" + me.getId_equipeA() + "','" + me.getId_equipeB() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Match ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<MatchEvent> afficherMatch() {
        List<MatchEvent> ListMatch = new ArrayList<>();

        try {
            String requete = "SELECT * FROM matchevent";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                MatchEvent mv = new MatchEvent();

                ImageView img = new ImageView();
                Image image;
                String url = SE.getById(mv.getId_equipeA()).getLogo_equipe();
                image = new Image("file:" + url);
                img.setImage(image);
                img.setPreserveRatio(true);
                mv.setLogoimageA(img);
                ImageView imge = new ImageView();
                Image imageA;
                String urlA = SE.getById(mv.getId_equipeB()).getLogo_equipe();
                image = new Image("file:" + urlA);
                imge.setImage(image);
                imge.setPreserveRatio(true);
                mv.setLogoimageA(imge);
                mv.setIdMatch(rs.getInt("idMatch"));

                mv.setTitre(rs.getString("titre"));
                mv.setDateMatch(rs.getDate("dateMatch"));
                mv.setId_equipeA(rs.getInt("id_equipeA"));
                mv.setId_equipeB(rs.getInt("id_equipeB"));

                String a = SE.getById(mv.getId_equipeA()).getNom();
                String b = SE.getById(mv.getId_equipeB()).getNom();
                mv.setNomEquipeA(a);
                mv.setNomEquipeB(b);
                ListMatch.add(mv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMatch;
    }

    public Equipe getEquipe(String nom) {
        Equipe eq = new Equipe();
        try {
            String requete = "SELECT * FROM equipe where nom_equipe= '" + nom + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eq.setIdG(rs.getInt("id_equipe"));
                eq.setNom(rs.getString("nom_equipe"));
                eq.setLogo_equipe(rs.getString("logo_equipe"));

                eq.setstade(rs.getString("stade"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eq;
    }

    public void deleteMatch(MatchEvent m) {
        try {
            String requete = "DELETE FROM matchevent where idMatch=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, m.getIdMatch());
            pst.executeUpdate();
            System.out.println("Match supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean verifyEvent(Date date) {

        java.sql.Date dateAujourd = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        return dateAujourd.compareTo(date) > 0;
    }

    public void suppEventDate(MatchEvent m) {
        if (verifyEvent(m.getDateMatch())) {
            deleteMatch(m);
        }
    }

    public void updateMatch(MatchEvent m) {
        try {

            Statement stm = cnx.createStatement();
            String query = "UPDATE matchevent SET titre= '" + m.getTitre() + "', dateMatch= '" + m.getDateMatch() + "', id_equipeA= '" + m.getId_equipeA() + "',id_equipeB= '" + m.getId_equipeB() + "' WHERE idMatch='" + m.getIdMatch() + "'";
            stm.executeUpdate(query);
            System.out.println("Match modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
