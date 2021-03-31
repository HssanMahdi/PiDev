/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ManagerFootballCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class StatistiqueNbrUserController implements Initializable {
     private Connection cnx = MyConnection.getInstance().getCnx();
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;
    ManagerFootballCRUD mng= new ManagerFootballCRUD();
    @FXML
    private PieChart pieChart;
    private Button btnback;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_joueur;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    @FXML
    private FontAwesomeIconView btnM;
    @FXML
    private Label lb;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private FontAwesomeIconView btnlog_out;
    @FXML
    private Label lb3;
    @FXML
    private Label lb6;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private FontAwesomeIconView btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        String req = "select type_user,COUNT(Id_user) from user GROUP by type_user";
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            rs = cnx.createStatement().executeQuery(req);
            while (rs.next()) {
                if (rs.getString(1).equals("MF")) {
                    pieChartData.add(new PieChart.Data("Manager Football", rs.getInt(2)));
                }
                if (rs.getString(1).equals("ad")) {
                    pieChartData.add(new PieChart.Data("Adhrent", rs.getInt(2)));
                }
                if (rs.getString(1).equals("adS")) {
                    pieChartData.add(new PieChart.Data("Admin Systeme", rs.getInt(2)));
                }
               }
            pieChart.setData(pieChartData);
            pieChart.setVisible(true);
            pieChart.setTitle("Nombre des utilisateurs");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }



    @FXML
    private void interfaceEquipe(ActionEvent event) {
    }

    @FXML
    private void interfaceJoueur(ActionEvent event) {
    }

    @FXML
    private void interfaceStat(ActionEvent event) {
    }

    @FXML
    private void interfaceHighls(ActionEvent event) {
    }

    @FXML
    private void AdminExisted(MouseEvent event) {
        lb.setVisible(false);
    }

    @FXML
    private void AdminEntred(MouseEvent event) {
        lb.setVisible(true);
    }

    @FXML
    private void ManagerExited(MouseEvent event) {
        lb1.setVisible(false);
    }

    @FXML
    private void ManagerEntred(MouseEvent event) {
        lb1.setVisible(true);
    }

    @FXML
    private void GestionManager(MouseEvent event) {
        
    }

    @FXML
    private void StatExited(MouseEvent event) {
       
    }

    @FXML
    private void StatEntred(MouseEvent event) {
        lb2.setVisible(true);
    }

    @FXML
    private void AfficherStat(MouseEvent event) {
        lb2.setVisible(false);
    }


   

    @FXML
    private void logout(MouseEvent event) throws IOException {
        btnlog_out.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void produitExited(MouseEvent event) {
         lb3.setVisible(false);
    }

    @FXML
    private void produitEntred(MouseEvent event) {
         lb3.setVisible(true);
    }

    @FXML
    private void commandeExited(MouseEvent event) {
         lb4.setVisible(false);
    }

    @FXML
    private void commandeEntred(MouseEvent event) {
         lb4.setVisible(true);
    }

    @FXML
    private void log_outExited(MouseEvent event) {
         lb6.setVisible(false);
    }

    @FXML
    private void log_outEntred(MouseEvent event) {
         lb6.setVisible(true);
    }

    @FXML
    private void categorieExited(MouseEvent event) {
         lb5.setVisible(false);
    }

    @FXML
    private void categorieEntred(MouseEvent event) {
         lb5.setVisible(true);
    }

    @FXML
    private void AdminSystem(MouseEvent event) throws IOException {
         btn1.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminSysteme.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
        
    
    

