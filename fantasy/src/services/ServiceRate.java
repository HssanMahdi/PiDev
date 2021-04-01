/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Rate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class ServiceRate {
    
    public void addRating(Rate r , int idUser) {
        try {
            String requete = "INSERT INTO rateproduit (rate,id_User,id_produit)"
                    + "VALUES ('" + r.getRate() + "','" + idUser + "','" + r.getIdProduit() + "')";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Rating ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


public double afficheRating(int idUser, int idProduit) {
         double rate =0;
        try {
            String requete = "SELECT rate FROM rateproduit WHERE id_User = '"+ idUser + "'  AND id_produit = '"+ idProduit + "' ";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
            

              
            rate = rs.getDouble("rate");
           
               

              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }


    
        public void addRating2(Rate r, int idUser) {
        Statement st;

        try {
            st = MyConnection.getInstance().getCnx().createStatement();
            String rec2 ="SELECT * FROM rateproduit WHERE id_User = '"+ idUser + "' AND id_produit= '" + r.getIdProduit() + "'";

            ResultSet rs = st.executeQuery(rec2);
            if (rs.next()) {
                try{
                       System.out.println("ratitou saye ama bch tmodifi");
                           st = MyConnection.getInstance().getCnx().createStatement();
                  String rec3=  "UPDATE rateproduit SET rate ='" + r.getRate() + "'  WHERE id_User =  '" + idUser + "' AND id_produit = '" + r.getIdProduit() + "' ";
                   st.executeUpdate(rec3);     
                    System.out.println("5edmet");
                    
                }
            catch (SQLException ex) {
                    Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                          String requete = "INSERT INTO rateproduit (rate,id_User,id_produit)"
                    + "VALUES ('" + r.getRate() + "','" + idUser + "','" + r.getIdProduit() + "')";
           st =  MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Rating ajoutée");
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceRate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
