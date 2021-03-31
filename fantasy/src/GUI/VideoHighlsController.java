/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML
    private Label lb1;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr1;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    @FXML
    private Button backbtn;

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
    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoHighlsController.this.getClass().getResource("HighlsUser.fxml"));
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
    
}
