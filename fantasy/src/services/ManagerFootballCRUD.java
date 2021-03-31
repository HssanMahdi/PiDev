/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.ManagerFootball;
import interfaces.IManagerFootball;
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
public class ManagerFootballCRUD implements IManagerFootball{

    @Override
    public void ajouterManagerFootball(ManagerFootball m) {
         try {
            String requete = "INSERT INTO user (nom_user,email,password,type_user,score_user,solde)"
                    + "VALUES ('"+ m.getNom_user() + "','" + m.getEmail() + "','" + m.getPassword() + "','MF','" + 0 + "','" + 0 + "')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    @Override
    public List displayManagerFootball() {
    List<ManagerFootball> ManagerList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user WHERE type_user='MF' ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ManagerFootball ss = new ManagerFootball();
                ss.setIduser(rs.getInt("id_user"));
                ss.setNom_user(rs.getString(2));
                ss.setEmail(rs.getString("email"));
                ss.setPassword(rs.getString("password"));
                ss.setType_user(rs.getString("type_user"));
                
                ManagerList.add(ss);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ManagerList;
    }
    public ManagerFootball connecter(int id) {
        ManagerFootball ss = new ManagerFootball();
        try {
            String req = "Select * from user WHERE id_user=" + id ;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                ss.setIduser(rs.getInt("id_user"));
                ss.setNom_user(rs.getString(2));
                ss.setEmail(rs.getString("email"));
                ss.setPassword(rs.getString("password"));
                ss.setType_user(rs.getString("type_user"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ss;
    }

   @Override
    public void supprimerManagerFootball(ManagerFootball m) {
         try {
            String requete = "DELETE FROM user where id_user=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, m.getId_user());
            pst.executeUpdate();
            System.out.println("Personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateManagerFootball(ManagerFootball m) {
        
     try {
     Statement stm = MyConnection.getInstance().getCnx().createStatement();
            String query = "UPDATE user SET nom_user= '" + m.getNom_user()
                    + "', email= '" + m.getEmail() + "', password= '" + m.getPassword() 
                    + "', type_user= 'MF' '"+ "', score_user= '" + 0 +  "', solde= '" + 0 +"' WHERE Id_user='" + m.getId_user() + "'";
            stm.executeUpdate(query);
            System.out.println("ManagerFootball modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public List<ManagerFootball> Rechercher(String s) {
        List<ManagerFootball> MN = new ArrayList<>();
        try {
            String req = "SELECT * FROM user where nom_user  LIKE '%" + s + "%' AND type_user = 'MF'"  ;
            Statement stm = MyConnection.getInstance().getCnx().createStatement();
           ResultSet rs = stm.executeQuery(req);
              while (rs.next()) {
                ManagerFootball mn = new ManagerFootball();
                mn.setIduser(rs.getInt("id_user"));
                mn.setNom_user(rs.getString("nom_user"));
                mn.setEmail(rs.getString("email"));
                mn.setPassword(rs.getString("password"));
                mn.setType_user(rs.getString("type_user"));
                MN.add(mn);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return MN;

    }


  
}

  