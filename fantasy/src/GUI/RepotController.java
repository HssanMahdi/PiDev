/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.PubService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RepotController implements Initializable {

    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    @FXML
    private LineChart<String, Integer> ChratLine;
    @FXML
    private AnchorPane main;
    @FXML
    private Button backbtn;
    @FXML
    private Label lb1;
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
    private FontAwesomeIconView btn_highInt;
    @FXML
    private Label lbHL;
    @FXML
    private FontAwesomeIconView btn_matchEv;
    @FXML
    private Label lbMatch;
    @FXML
    private FontAwesomeIconView btn_statH;
    @FXML
    private Label lblStats;
    @FXML
    private Label lblProfile;
    @FXML
    private Label lblLogout;
    @FXML
    private FontAwesomeIconView btn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        PubService ps = new PubService();

        ps.calculatereportspermonth();

        XYChart.Series series = new XYChart.Series();

        series.setName("Nombre de publication");
        series.getData()
                .add(new XYChart.Data("Janvier", PubService.totaleJ));
        series.getData()
                .add(new XYChart.Data("Fevrier", PubService.totaleF));
        series.getData()
                .add(new XYChart.Data("Mars", PubService.totaleM));
        series.getData()
                .add(new XYChart.Data("Avril", PubService.totaleA));
        series.getData()
                .add(new XYChart.Data("Mai", PubService.totaleMai));
        series.getData()
                .add(new XYChart.Data("Juin", PubService.totaleJuin));
        series.getData()
                .add(new XYChart.Data("Juillet", PubService.totaleJuillet));
        series.getData()
                .add(new XYChart.Data("Août", PubService.totaleAout));
        series.getData()
                .add(new XYChart.Data("Septembre", PubService.totaleSeptembre));
        series.getData()
                .add(new XYChart.Data("Octobre", PubService.totaleOctobre));
        series.getData()
                .add(new XYChart.Data("Novembre", PubService.totaleNovembre));
        series.getData()
                .add(new XYChart.Data("Decembre", PubService.totaleDécembre));

        ChratLine.getData().add(series);
        // TODO
    }

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("DisplayHighls.fxml"));
        backbtn.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    private void backExit(MouseEvent event) {
        lb1.setVisible(false);
    }

    @FXML
    private void backEntred(MouseEvent event) {
        lb1.setVisible(true);
    }

    @FXML
    private void interfaceJoueur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("DisplayPlayer.fxml"));
        btn_joueur.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void interfaceStat(ActionEvent event) {
    }

    @FXML
    private void interfaceHighls(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("DisplayHighls.fxml"));
        btn_highls.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("DisplayEquipe.fxml"));
        btn_equipe.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

   


    

    @FXML
    private void highlsExit(MouseEvent event) {
        lbHL.setVisible(false);
    }

    @FXML
    private void highlsEntred(MouseEvent event) {
        lbHL.setVisible(true);
    }

    @FXML
    private void actionHighls(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("DisplayHighls.fxml"));
        btn_highInt.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    private void eventActionInterface(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("AddEvent.fxml"));
        btn_statH.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void statActionInterface(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("Repot.fxml"));
        btn_statH.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void userExit(MouseEvent event) {
        lblProfile.setVisible(false);
    }

    @FXML
    private void userEnred(MouseEvent event) {
        lblProfile.setVisible(true);
    }

    @FXML
    private void userProfileInt(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RepotController.this.getClass().getResource("GestionManagerFootball.fxml"));
        btn_statH.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void logExit(MouseEvent event) {
        lblLogout.setVisible(false);
    }

    @FXML
    private void logEntred(MouseEvent event) {
        lblLogout.setVisible(true);
    }

    @FXML
    private void LogoutInt(MouseEvent event) {
               try {
            btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(JoueurUserIntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EventExit(MouseEvent event) {
        lbMatch.setVisible(false);
    }

    @FXML
    private void EventEntred(MouseEvent event) {
        lbMatch.setVisible(true);
    }

    @FXML
    private void statExit(MouseEvent event) {
        lblStats.setVisible(false);
    }

    @FXML
    private void statEntred(MouseEvent event) {
        lblStats.setVisible(true);
    }
}
