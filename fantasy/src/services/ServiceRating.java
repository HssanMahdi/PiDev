/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interface.IServiceRating;
import Tools.MyConnection;
import entities.RatingJoueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ServiceRating implements IServiceRating {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addRating(RatingJoueur r) {
        try {
            String requete = "INSERT INTO rating (ratingValue,id_user,id_joueur)"
                    + "VALUES ('" + r.getRatingValue() + "','" + r.getId_user() + "','" + r.getId_joueur() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Rating ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<RatingJoueur> afficheRating() {
        List<RatingJoueur> ListRating = new ArrayList<>();
        try {
            String requete = "SELECT * FROM rating";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                RatingJoueur r = new RatingJoueur();

                r.setId_rating(rs.getInt("id_rating"));
                r.setRatingValue(rs.getDouble("ratingValue"));
                r.setId_user(rs.getInt("id_user"));
                r.setId_joueur(rs.getInt("id_joueur"));

                ListRating.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListRating;
    }

    @Override
    public void deleteRating(int idUser, int idJoueur) {
        try {
            String requete = "DELETE FROM rating where id_joueur= " + idJoueur + " AND id_user=" + idUser + ";";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.executeUpdate();
            System.out.println("Rating supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//    @Override
    public void updateRating(RatingJoueur j) {
        try {

            Statement stm = cnx.createStatement();
            String query = "UPDATE rating SET ratingValue= '" + j.getRatingValue()+"' WHERE id_joueur='" + j.getId_joueur()+ "' AND  id_user='" +j.getId_user()+"'";
            stm.executeUpdate(query);
            System.out.println("Rating modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public RatingJoueur getById(int id) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `rating` where id_joueur= '" + id + "'";
            ResultSet rst = st.executeQuery(query);
            RatingJoueur r = new RatingJoueur();
            while (rst.next()) {

                r.setId_rating(rst.getInt("id_rating"));
                r.setRatingValue(rst.getDouble("ratingValue"));
                r.setId_user(rst.getInt("id_user"));
                r.setId_joueur(rst.getInt("id_joueur"));

            }
            return r;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public double calculAvis(int id) {
        double moyAvis = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select id_rating,id_joueur,COUNT(id_rating),SUM(ratingValue) from `rating` where id_joueur= '" + id + "'";
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                if (rst.getInt(3) != 0) {
                    moyAvis = rst.getInt(4) / rst.getInt(3);
                } else {
                    moyAvis = 0.0;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return moyAvis;
    }

    @Override
    public boolean UserExist(int idJR, int idUser) {
        boolean test = false;
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `rating` where id_joueur= " + idJR + " AND id_user=" + idUser + ";";
            ResultSet rst = st.executeQuery(query);
            test = rst.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;

    }

}
