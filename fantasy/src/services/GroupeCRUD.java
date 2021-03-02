/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Adherent;
import entites.Groupe;
import interfaces.IGroupe;
import tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public class GroupeCRUD implements IGroupe<Groupe,Adherent>{

    @Override
    public void creeGroupe(Groupe g, Adherent u) {
         try {
 
           String requete= "INSERT INTO groupe(nom_groupe)"
                    + "VALUES (?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, g.getNom());
            pst.executeUpdate();
          //  System.out.println("Groupe Crée");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
 
           String requete= "INSERT INTO groupeuser(id_groupe,id_user)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, g.getId());
            pst.setInt(2, u.getId_user());
            pst.executeUpdate();
           // System.out.println("worked ");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
         
    }
    
    
    @Override
    public void ajouterAdherentauGroupe(Groupe g, Adherent u) {
        try {
 
           String requete= "INSERT INTO groupeuser(id_groupe,id_user)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, g.getId());
            pst.setInt(2, u.getId_user());
            pst.executeUpdate();
          //  System.out.println("worked ");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    @Override
    public void supprimerAdherentduGroupe(Groupe g, Adherent u) {
         try {
            String requete = "DELETE FROM groupeuser where id_user=? AND id_groupe=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, u.getId_user());
            pst.setInt(2, g.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerGroupe(Groupe g) {
         try {
            String requete = "DELETE FROM groupe where id_groupe=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, g.getId());
            pst.executeUpdate();
            System.out.println("Groupe supprimée de tab groupe");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
          try {
            String requete = "DELETE FROM groupeuser where groupeuser.id_groupe=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, g.getId());
            pst.executeUpdate();
            System.out.println("Groupe supprimée de tab groupeuser");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateGroupe(Groupe g) {
        try {
            String requete = "UPDATE groupe SET nom_groupe=? WHERE id_groupe=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, g.getNom());
            pst.setInt(2, g.getId());
            pst.executeUpdate();
            System.out.println("Groupe modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Groupe> displayGrouppourAdherent(Adherent u) {
        List<Groupe> GroupeList = new ArrayList<>();
        ResultSet rs1;
        try {
            String requete = "SELECT id_groupe FROM groupeuser WHERE id_user="+u.getId_user()+";";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
//            System.out.println("1");
            while(rs.next()){
//                System.out.println(rs.getInt("id_groupe"));
                String requete1 = "SELECT * FROM groupe WHERE id_groupe="+rs.getInt("id_groupe")+";";
                Statement st1 = MyConnection.getInstance().getCnx()
                    .createStatement();
                rs1 = st1.executeQuery(requete1);
                while(rs1.next()){
                System.out.println(rs1.getInt("id_groupe"));
                System.out.println(rs1.getString("nom_groupe"));
                Groupe g = new Groupe();
                g.setId(rs1.getInt("id_groupe"));
                g.setNom(rs1.getString("nom_groupe"));
//                System.out.println("2");
                 GroupeList.add(g);
                
        }
                
              //  System.out.println("3");
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return GroupeList;
       //pour l'affichage (front/back) System.out.println(gcd.displayGrouppourAdherent(u));
    }

    @Override
    public List<Adherent> displayAdherentdeGroupe(Groupe g) {
        List<Adherent> AdherentList = new ArrayList<>();
        ResultSet rs1;
        try {
            String requete = "SELECT id_user FROM groupeuser WHERE id_groupe="+g.getId()+";";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
//            System.out.println("1");
            while(rs.next()){
//               System.out.println(rs.getInt("id_user"));
                String requete1 = "SELECT * FROM user WHERE id_user="+rs.getInt("id_user")+";";
                Statement st1 = MyConnection.getInstance().getCnx()
                    .createStatement();
                rs1 = st1.executeQuery(requete1);
                while(rs1.next()){
                System.out.println(rs1.getInt("id_user"));
               System.out.println(rs1.getString("nom_user"));
                Adherent u = new Adherent();
                u.setId_user(rs1.getInt("id_user"));
                u.setNom_user(rs1.getString("nom_user"));
//                System.out.println("2");
                 AdherentList.add(u);
                
        }
                
              //  System.out.println("3");
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return AdherentList;
    }

    

    
}
