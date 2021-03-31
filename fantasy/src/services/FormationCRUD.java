/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Adherent;
import entites.Formation;
import entites.Joueur;
import interfaces.IFormation;
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
public class FormationCRUD implements IFormation<Formation,Joueur,Adherent>{

// creation de formation doit etre avec la creation de user a
    @Override
     public void creeFormation( Adherent a) {
       try {
 
           String requete= "INSERT INTO formation(id_user)"
                    + "VALUES (?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId_user());
            pst.executeUpdate();
          //  System.out.println("Groupe Crée");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

// ki yechri joueur mel magasin
    @Override
    public void ajouterJoueurauFormation(Adherent a,Formation f, Joueur j) {
        try {
            //insert joueur au formation
            String requete= "INSERT INTO formjoueur(id_formation,id_joueur)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId());
            pst.setInt(2, j.getIdJoueur());
            pst.executeUpdate();
            //mis a jour de solde
            String requete2 = "UPDATE user SET solde=? WHERE id_user=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst2.setInt(1,a.getSolde()-j.getPrixJoueur());
            pst2.setInt(2, a.getId_user());
            pst2.executeUpdate();
//            System.out.println("solde modifiee");
//            System.out.println(a.getSolde()-j.getPrixJoueur());
          //  System.out.println("worked ");  
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    
// ki yetfasakh joueur mel base
    @Override
    public void supprimerJoueurduFormation(Adherent u, Joueur j) {
        try {
            String requete1 = "SELECT id_formation FROM formation WHERE id_user="+u.getId_user()+";";
                Statement st1 = MyConnection.getInstance().getCnx()
                    .createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                rs1.next();
           
            String requete = "DELETE FROM formjoueur where id_formation=? AND id_joueur=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, rs1.getInt("id_formation"));
            pst.setInt(2, j.getIdJoueur());
            pst.executeUpdate();
            
            String requete2 = "UPDATE user SET solde=? WHERE id_user=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst2.setInt(1,u.getSolde()+j.getPrixJoueur());
            pst2.setInt(2, u.getId_user());
            pst2.executeUpdate();
//            System.out.println("solde modifiee");
//            System.out.println(u.getSolde());
//            System.out.println(u.getSolde()+j.getPrixJoueur());
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // supp d'un joueur lezem tkoun ki yetfasakh un joueur mel BD yetfasakh mel les formation lkol j
    @Override
    public void supprimerJoueurduFormation2( Joueur j) {
        try {
            String requete = "DELETE FROM formjoueur where id_joueur=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, j.getIdJoueur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Joueur> displayJoueurdeFormation(Adherent a) {
       List<Joueur> JoueurList = new ArrayList<>();
        ResultSet rs1,rs2;
        try {
        String requete = "SELECT id_formation FROM formation WHERE id_user="+a.getId_user()+";";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
             while(rs.next()){
//               System.out.println(rs.getInt("id_user"));
                String requete1 = "SELECT id_joueur FROM formjoueur WHERE id_formation="+rs.getInt("id_formation")+";";
                Statement st1 = MyConnection.getInstance().getCnx()
                    .createStatement();
                rs1 = st1.executeQuery(requete1);
                while(rs1.next()){
                    String requete2 = "SELECT * FROM joueur WHERE id_joueur="+rs1.getInt("id_joueur")+";";
                    Statement st2 = MyConnection.getInstance().getCnx()
                        .createStatement();
                    rs2 = st2.executeQuery(requete2);
                    while(rs2.next()){
                
                        Joueur j = new Joueur();
                        j.setIdJoueur(rs2.getInt("id_joueur"));
                        j.setNomJoueur(rs2.getString("nom_joueur"));
                        j.setPrenomJoueur(rs2.getString("prenom_joueur"));
                        j.setPosition(rs2.getString("position"));
                        j.setPrixJoueur(rs2.getInt("prix_joueur"));
                        j.setLogoJoueur(rs2.getString("logo_joueur"));
//                System.out.println("2");
                        JoueurList.add(j);
                    }
                }
             }    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return JoueurList;
    }

     
    @Override
    public Formation getFormation(Adherent a) {
        Formation f=new Formation();
        try {
            String requete = "SELECT id_formation FROM formation WHERE id_user='"+a.getId_user()+"';";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()){
                f.setId(rs.getInt("id_formation"));
                f.setIdAdherent(a.getId_user());
                System.out.println("111");
            }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }




   


}
