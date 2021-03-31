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
public class HighlsUserController implements Initializable {

     PubService ps = new PubService();
    String PV;
    @FXML
    private AnchorPane main;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    @FXML
    private VBox videoContainer;
    @FXML
    private Label lbl;
    @FXML
    private Label lblS;
    @FXML
    private Label lblA;
    @FXML
    private Button btn_stotr1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Publication> listEquipe = ps.afficherVideo();

        afficherVideo(listEquipe);
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
                System.out.println(PV);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(HighlsUserController.this.getClass().getResource("VideoHighls.fxml"));
                try {

                    loader.load();
                    VideoHighlsController displaycontroller = loader.getController();
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

            
         

            single.getChildren().add(PM);
            single.getChildren().add(play);
            single.getChildren().add(name);
            single.getChildren().add(location);
            single.getChildren().add(dte);
  

            videoContainer.getChildren().add(single);

        }
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HighlsUserController.this.getClass().getResource("EquipeUserInt.fxml"));
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
    }

    @FXML
    private void interfaceHighls(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HighlsUserController.this.getClass().getResource("HighlsUser.fxml"));
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
    
}
