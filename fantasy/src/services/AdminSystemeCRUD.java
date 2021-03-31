/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.AdminSysteme;
import interfaces.IAdminSysteme;
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
public class AdminSystemeCRUD implements IAdminSysteme {

    @Override
    public void ajouterAdminSysteme(AdminSysteme a) {
        try {
            String requete = "INSERT INTO user (nom_user,email,password,type_user,score_user,solde)"
                    + "VALUES ('" + a.getNom_user() + "','" + a.getEmail() + "','" + a.getPassword() + "','adS','" + 0 + "','" + 0 + "')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerAdminSysteme(AdminSysteme a) {
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
 public AdminSysteme connecter(int id) {
        AdminSysteme as = new AdminSysteme();
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
    public void updateAdminSysteme(AdminSysteme a) {
        try {

            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            String query = "UPDATE user SET nom_user= '" + a.getNom_user()
                    + "', email= '" + a.getEmail() + "', password= '" + a.getPassword()
                    + "', type_user='adS' '"+"', score_user= '" + 0 + "', solde= '" + 0 + "' WHERE id_user='" + a.getId_user() + "'";
            stm.executeUpdate(query);
            System.out.println("AdminSysteme modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<AdminSysteme> displayAdminSysteme() {

        List<AdminSysteme> adminList = new ArrayList();
        try {
            String requete = "SELECT * FROM user WHERE type_user='adS' ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                AdminSysteme as = new AdminSysteme();
                as.setIduser(rs.getInt("id_user"));
                as.setNom_user(rs.getString("nom_user"));
                as.setEmail(rs.getString("email"));
                as.setPassword(rs.getString("password"));
                as.setType_user(rs.getString("type_user"));
                adminList.add(as);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }
    
    public List<AdminSysteme> Rechercher(String s) {
        List<AdminSysteme> AS = new ArrayList<>();
        try {
            String req = "SELECT * FROM user where nom_user  LIKE '%" + s + "%' AND type_user='adS'";
            Statement stm = MyConnection.getInstance().getCnx().createStatement();
           ResultSet rs = stm.executeQuery(req);
              while (rs.next()) {
                AdminSysteme as = new AdminSysteme();
                as.setIduser(rs.getInt("id_user"));
                as.setNom_user(rs.getString("nom_user"));
                as.setEmail(rs.getString("email"));
                as.setPassword(rs.getString("password"));
                as.setType_user(rs.getString("type_user"));
                AS.add(as);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return AS;

    }
public List<AdminSysteme> Trier(int i) {
        List<AdminSysteme> listAd = new ArrayList<>();
        try {
            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            String query = "";
            if (i == 1) {
                query = "select * from user where  type_user='adS' ORDER BY nom_user ASC";
            } else if (i == 2) {
                query = "select * from user where  type_user='adS' ORDER BY email ASC";
            }

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

               
              AdminSysteme as = new AdminSysteme();
                as.setIduser(rs.getInt("id_user"));
                as.setNom_user(rs.getString("nom_user"));
                as.setEmail(rs.getString("email"));
                as.setPassword(rs.getString("password"));
                as.setType_user(rs.getString("type_user"));
           
                listAd.add(as);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listAd;
    }

}
