/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Adherent;
import interfaces.IAdherent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyConnection;

/**
 *
 * @author MediaStudio
 */
public class AdherentCRUD implements IAdherent {

    @Override
    public void ajouterAdherent(Adherent a) {
        try {
            String requete = "INSERT INTO user (nom_user,email,password,type_user,score_user,solde)"
                    + "VALUES ('" + a.getNom_user() + "','" + a.getEmail() + "','" + a.getPassword() + "','ad','" + 0 + "','" + 10000 + "')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerAdherent(Adherent a) {
        try {
            String requete = "DELETE FROM user where id_user=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId_user());
            pst.executeUpdate();
            System.out.println("Personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateAdherent(Adherent a) {
        try {

            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            String query = "UPDATE user SET nom_user= '" + a.getNom_user()
                    + "', email= '" + a.getEmail() + "', password= '" + a.getPassword()
                    + "', type_user= 'ad' '" + "', score_user= '" + a.getScore_user() + "', solde= '" + a.getSolde() + "' WHERE Id_user='" + a.getId_user() + "'";
            stm.executeUpdate(query);
            System.out.println("Adherent modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Adherent connecter(int id) {
        Adherent as = new Adherent();
        try {
            String req = "Select * from user WHERE id_user=" + id ;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                as.setIduser(rs.getInt("id_user"));
                as.setNom_user(rs.getString("nom_user"));
                as.setEmail(rs.getString("email"));
                as.setPassword(rs.getString("password"));
                as.setType_user(rs.getString("type_user"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return as;
    }
    

    @Override
    public List displayAdherent() {
        List<Adherent> adherentList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user WHERE type_user='ad' ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Adherent as = new Adherent();
                as.setIduser(rs.getInt("id_user"));
                as.setNom_user(rs.getString("nom_user"));
                as.setEmail(rs.getString("email"));
                as.setPassword(rs.getString("password"));
                as.setType_user(rs.getString("type_user"));
                adherentList.add(as);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adherentList;
    }
    public List<String> sugg(){
    List <String> t=new ArrayList();
    try {
            String requete = "SELECT email FROM user ;";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                t.add(rs.getString("email"));
                System.out.println(rs.getString("email"));
            }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return t;
    }
    
    public static Adherent byemail(String text) {
        Adherent u = new Adherent();
        try {
            String requete = "SELECT * FROM user WHERE email='"+text+"';";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()){
                u.setIduser(rs.getInt("id_user"));
                u.setEmail(text);
                u.setNom_user(rs.getString("nom_user"));
                System.out.println("111");
            }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

   
}
