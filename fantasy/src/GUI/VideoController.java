/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class VideoController implements Initializable {

    Media MD;
    MediaPlayer MDP;
    public String PV;
    @FXML
    private MediaView MP;
    @FXML
    private Button stopbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Button play_btn;
    @FXML
    private Label nomPub;
    @FXML
    private AnchorPane main;
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

        // TODO
    }

    public void VideoInfo(String px, String nomVideo) {
        PV = px;
        nomPub.setText(nomVideo);
        String path = new File(PV).getAbsolutePath();
        System.out.println(path);
        MD = new Media(new File(path).toURI().toString());
        MDP = new MediaPlayer(MD);
        MP.setFitWidth(900);
        MP.setFitHeight(380);
        MP.setMediaPlayer(MDP);

    }

    @FXML
    private void Stop(ActionEvent event) {
        MDP.pause();

    }

    @FXML
    private void back(ActionEvent event) {
        MDP.stop();
        AnchorPane redirect;
                try {
                    redirect = FXMLLoader.load(VideoController.this.getClass().getResource("DisplayHighls.fxml"));
                    main.getChildren().setAll(redirect);
                } catch (IOException ex) {
                    System.out.println(ex);
                   
                }
    }

    @FXML
    private void PlayV(ActionEvent event) {
        MDP.play();
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
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoController.this.getClass().getResource("DisplayEquipe.fxml"));
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
    private void interfaceJoueur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoController.this.getClass().getResource("DisplayPlayer.fxml"));
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
        loader.setLocation(VideoController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(VideoController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(VideoController.this.getClass().getResource("AddEvent.fxml"));
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
        loader.setLocation(VideoController.this.getClass().getResource("Repot.fxml"));
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
        loader.setLocation(VideoController.this.getClass().getResource("GestionManagerFootball.fxml"));
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
