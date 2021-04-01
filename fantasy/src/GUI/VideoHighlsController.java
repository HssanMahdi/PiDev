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
public class VideoHighlsController implements Initializable {

    Media MD;
    MediaPlayer MDP;
    public String PV;
    @FXML
    private AnchorPane main;
    @FXML
    private MediaView MP;
    @FXML
    private Label nomPub;
    @FXML
    private Button stopbtn;
    @FXML
    private Button play_btn;
    private Label lb1;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    private Button backbtn;
    @FXML
    private Button dash_id;
    @FXML
    private Button btn_formACT;
    @FXML
    private FontAwesomeIconView grp_btn;
    @FXML
    private FontAwesomeIconView form_btn;
    @FXML
    private FontAwesomeIconView event_btn;
    @FXML
    private FontAwesomeIconView btnlog_out;
    @FXML
    private Label lbGroupe1;
    @FXML
    private Label lbForm1;
    @FXML
    private Label lbMatch1;
    @FXML
    private Label lbdecnx1;
    @FXML
    private Label lbGroupe11111;

    /**
     * Initializes the controller class.
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
    private void PlayV(ActionEvent event) {
        MDP.play();
    }

       @FXML
    private void interfaceEquipe(ActionEvent event) {
        MDP.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("EquipeUserInt.fxml"));
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
    private void interfaceStat(ActionEvent event) {
         MDP.stop();
    }

    @FXML
    private void interfaceHighls(ActionEvent event) {
         MDP.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("HighlsUser.fxml"));
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
    private void actualiteInterface(ActionEvent event) {
         MDP.stop();
    }

    @FXML
    private void InterfaceFormation(ActionEvent event) {
         MDP.stop();
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("AfficherFormation.fxml"));
        form_btn.getScene().getWindow().hide();
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
    private void storeInterface(ActionEvent event) {
         MDP.stop();
    }

        @FXML
    private void grpExist(MouseEvent event) {
        lbGroupe1.setVisible(false);
    }

    @FXML
    private void grpRntred(MouseEvent event) {
        lbGroupe1.setVisible(true);
    }

    @FXML
    private void GroupeInt(MouseEvent event) {
         MDP.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
        grp_btn.getScene().getWindow().hide();
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
    private void formExi(MouseEvent event) {
        lbForm1.setVisible(false);
    }

    @FXML
    private void formEnt(MouseEvent event) {
        lbForm1.setVisible(true);
    }

    @FXML
    private void formationInt(MouseEvent event) {
         MDP.stop();
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("AfficherFormation.fxml"));
        form_btn.getScene().getWindow().hide();
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
    private void evnExit(MouseEvent event) {
        lbMatch1.setVisible(false);
    }

    @FXML
    private void evnEntr(MouseEvent event) {
        lbMatch1.setVisible(true);
    }

    @FXML
    private void EventAct(MouseEvent event) {
         MDP.stop();
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("EventforUser.fxml"));
        event_btn.getScene().getWindow().hide();
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
    private void actionExit(MouseEvent event) {
        lbGroupe11111.setVisible(false);
    }

    @FXML
    private void actionentred(MouseEvent event) {
        lbGroupe11111.setVisible(true);
    }

    @FXML
    private void logExit(MouseEvent event) {
        lbdecnx1.setVisible(false);
    }

    @FXML
    private void logEntr(MouseEvent event) {
        lbdecnx1.setVisible(true);
    }

    @FXML
    private void lbgroupe1111action(MouseEvent event) {
         MDP.stop();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("GestionUser.fxml"));
        lbGroupe11111.getScene().getWindow().hide();
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
    private void deconxAction(MouseEvent event) {
         MDP.stop();
        try {
            btnlog_out.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(JoueurUserIntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
