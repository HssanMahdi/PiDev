/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.PubService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Publication;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DisplayHighlsController implements Initializable {

    PubService ps = new PubService();
    String PV;
    @FXML
    private AnchorPane main;
    @FXML
    private VBox videoContainer;
    @FXML
    private Button add_btn;
    @FXML
    private Button loadbtn;
    @FXML
    private Button Report_btn;
    @FXML
    private Label lbl;
    @FXML
    private Label lblS;
    @FXML
    private Label lblA;
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
    private Label lbHL;
    @FXML
    private Label lbMatch;
    @FXML
    private FontAwesomeIconView btn_statH;
   
    @FXML
    private Label lblProfile;
    @FXML
    private Label lblLogout;
    @FXML
    private FontAwesomeIconView btn_matchEv;
    @FXML
    private FontAwesomeIconView btn_highInt;
    @FXML
    private Label lblStats;
    @FXML
    private FontAwesomeIconView btnlog_out;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Publication> listEquipe = ps.afficherVideo();

        afficherVideo(listEquipe);
        // TODO
    }

    private void afficherVideo(List<Publication> lsPub) {

        videoContainer.getChildren().clear();

        for (int i = 0; i < lsPub.size(); i++) {
            Publication pbc = lsPub.get(i);
            Pane single = new Pane();
            single.setPrefHeight(150);
            single.setPrefWidth(520);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            Media MD;
            MediaPlayer MDP;
            String sr = "file:" + pbc.contenu_pub;
            MD = new Media(sr);
            MDP = new MediaPlayer(MD);
            MediaView PM = new MediaView();
            PM.setMediaPlayer(MDP);
            PM.setLayoutX(3);
            PM.setLayoutY(3);
            PM.setFitHeight(250);
            PM.setFitWidth(250);

            FontAwesomeIconView play = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
            play.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:45px;"
                    + "-fx-fill: #f2eded;"
            );
            play.setLayoutX(110);
            play.setLayoutY(90);
            play.setVisible(true);
//            Button btn = new Button("", play);
//            btn.setPrefHeight(USE_COMPUTED_SIZE);
//            btn.setPrefHeight(USE_COMPUTED_SIZE);
//            btn.setStyle("-fx-background-color: Black;");
//            btn.setLayoutX(3);
//            btn.setLayoutY(3);

            play.setOnMouseClicked((MouseEvent event) -> {
                Publication pub = new Publication(pbc.getid_pub(), pbc.getnom_pub(), pbc.getdate_pub(), pbc.getcontenu_pub());
                PV = pub.getcontenu_pub();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DisplayHighlsController.this.getClass().getResource("Video.fxml"));
                try {

                    loader.load();
                    VideoController displaycontroller = loader.getController();
                    displaycontroller.VideoInfo(PV, pub.getnom_pub());

                } catch (IOException ex) {
                    System.out.println(ex);
                }

                play.getScene().getWindow().hide();
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
            });

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(280);
            name.setLayoutY(15);
            name.setStyle("-fx-font-size :24");
            name.setText(pbc.getnom_pub());

            Label location = new Label();
            location.setPrefHeight(27);
            location.setPrefWidth(USE_COMPUTED_SIZE);
            location.setLayoutX(300);
            location.setLayoutY(58);
            location.setStyle("-fx-font-size :15");
            location.setFont(Font.font("Times New Roman"));
            location.setText("" + pbc.getdate_pub());

            FontAwesomeIconView dte = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
            dte.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
            );
            dte.setLayoutX(280);
            dte.setLayoutY(77);

            FontAwesomeIconView supprimer = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            supprimer.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:25px;"
                    + "-fx-fill:#e13131;"
            );

            supprimer.setLayoutX(577);
            supprimer.setLayoutY(38);

            supprimer.setOnMouseClicked((MouseEvent e) -> {
                ps.deleteVideo(pbc);
                Refresh();
            });

            FontAwesomeIconView modifier = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            modifier.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:23px;"
                    + "-fx-fill:Black;"
            );
            modifier.setLayoutX(543);
            modifier.setLayoutY(39);
            modifier.setOnMouseClicked((MouseEvent e) -> {
                Publication pub = new Publication(pbc.getid_pub(), pbc.getnom_pub(), pbc.getdate_pub(), pbc.getcontenu_pub());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DisplayHighlsController.this.getClass().getResource("modifHighls.fxml"));
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ModifHighlsController modifPub = loader.getController();
                modifPub.setTextField(pub.getid_pub(), pub.getnom_pub());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            });

            single.getChildren().add(PM);
            single.getChildren().add(play);
            single.getChildren().add(name);
            single.getChildren().add(location);
            single.getChildren().add(dte);
            single.getChildren().add(supprimer);
            single.getChildren().add(modifier);

            videoContainer.getChildren().add(single);

        }
    }

    @FXML
    private void AddHighliths(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddHighlights.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void loadPub(ActionEvent event) {
        Refresh();

    }

    public void Refresh() {
        AnchorPane redirect;
        try {
            redirect = FXMLLoader.load(DisplayHighlsController.this.getClass().getResource("DisplayHighls.fxml"));
            main.getChildren().setAll(redirect);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void Report(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("Repot.fxml"));
        Report_btn.getScene().getWindow().hide();
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
    private void addExit(MouseEvent event) {
        lbl.setVisible(false);
    }

    @FXML
    private void addEntered(MouseEvent event) {
        lbl.setVisible(true);
    }

    @FXML
    private void loadExit(MouseEvent event) {
        lblA.setVisible(false);
    }

    @FXML
    private void loadEntred(MouseEvent event) {
        lblA.setVisible(true);
    }

        @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("DisplayEquipe.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("DisplayPlayer.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("AddEvent.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("Repot.fxml"));
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
        loader.setLocation(DisplayHighlsController.this.getClass().getResource("GestionManagerFootball.fxml"));
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
