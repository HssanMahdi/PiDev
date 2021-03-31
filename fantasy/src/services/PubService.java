/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Publication;
import interfaces.IPubService;
import tools.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pc
 */
public class PubService implements IPubService{

    Connection cnx = MyConnection.getInstance().getCnx();
    Statement st;
    public static int totaleJ = 0;
    public static int totaleF = 0;
    public static int totaleM = 0;
    public static int totaleA = 0;
    public static int totaleMai = 0;
    public static int totaleJuin = 0;
    public static int totaleJuillet = 0;
    public static int totaleAout = 0;
    public static int totaleSeptembre = 0;
    public static int totaleOctobre = 0;
    public static int totaleNovembre = 0;
    public static int totaleDécembre = 0;

    public PubService()  {

    }

    @Override
    public void ajouter(Publication p) {

        String req = "INSERT INTO publication (videoName,date_Pub,url) VALUES (?,?, ?)";

        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, p.getnom_pub());
            pre.setDate(2, p.getdate_pub());
            pre.setString(3, p.getcontenu_pub());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("stop ici");
        }
    }

    @Override
    public void deleteVideo(Publication p) {

        try {

            String req = "DELETE FROM publication WHERE idVideo = ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
             pst.setInt(1, p.getid_pub());
            pst.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Publication> afficherVideo() {
        List<Publication> pub = new ArrayList<>();
        try {
            String requete = "select * from publication";
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Publication pp = new Publication();
                pp.setid_pub(rs.getInt("idVideo"));
                pp.setnom_pub(rs.getString("videoName"));
                pp.setdate_pub(rs.getDate("date_pub"));
                pp.setcontenu_pub(rs.getString("url"));

                pub.add(pp);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
   

        }
        return pub;
    }

    @Override
    public void calculatereportspermonth() {

        try {
            String req = "SELECT * FROM publication ";
            Statement statement = cnx.createStatement();

            ResultSet rs = statement.executeQuery(req);

            while (rs.next()) {
                switch (rs.getDate("date_Pub").getMonth() + 1) {
                    //between 0 and 11. 0 corresponds to January, 
                    case 1:
                        totaleJ += 1;
                        System.out.println("January " + totaleJ + " date " + rs.getDate("date_Pub"));
                        break;
                    case 2:
                        totaleF += 1;
                        System.out.println("February " + totaleF + " date " + rs.getDate("date_Pub"));
                        break;
                    case 3:
                        totaleM += 1;
                        System.out.println("March " + totaleM + " date " + rs.getDate("date_Pub"));
                        break;
                    case 4:
                        totaleA += 1;
                        System.out.println("April " + totaleA + " date " + rs.getDate("date_Pub"));
                        break;
                    case 5:
                        totaleMai += 1;
                        System.out.println("May " + totaleMai + " date " + rs.getDate("date_Pub"));
                        break;
                    case 6:
                        totaleJuin += 1;
                        System.out.println("Juine " + totaleJuin + " date " + rs.getDate("date_Pub"));
                        break;
                    case 7:
                        totaleJuillet += 1;
                        System.out.println("July " + totaleJuillet + " date" + rs.getDate("date_Pub"));
                        break;
                    case 8:
                        totaleAout += 1;
                        System.out.println("August " + totaleAout + " date " + rs.getDate("date_Pub"));
                        break;
                    case 9:
                        totaleSeptembre += 1;
                        System.out.println("september " + totaleSeptembre + " date " + rs.getDate("date_Pub"));
                        break;
                    case 10:
                        totaleOctobre += 1;
                        System.out.println("october " + totaleOctobre + " date " + rs.getDate("date_Pub"));
                        break;
                    case 11:
                        totaleNovembre += 1;
                        System.out.println("november " + totaleNovembre + " date " + rs.getDate("date_Pub"));
                        break;
                    case 12:
                        totaleDécembre += 1;
                        System.out.println("décember " + totaleDécembre + " date " + rs.getDate("date_Pub"));
                        break;
                    default:
                        break;
                }

            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public void updatePub(Publication p) {
        try {

            Statement stm = cnx.createStatement();
            String query = "UPDATE Publication SET videoName= '" + p.getnom_pub() + "' WHERE idVideo='" + p.getid() + "'";
            stm.executeUpdate(query);
            System.out.println("Publication modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



}
