/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import entites.MatchEvent;
import services.ServiceMatchEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class EventforUserController implements Initializable {
    
    ServiceMatchEvent SM = new ServiceMatchEvent();
    @FXML
    private AnchorPane main;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox Container;
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
    private FontAwesomeIconView grp_btn;
    @FXML
    private FontAwesomeIconView form_btn;
    @FXML
    private FontAwesomeIconView event_btn;
    @FXML
    private Label lbGroupe;
    @FXML
    private Label lbForm;
    @FXML
    private Label lbMatch;
    @FXML
    private Label lbdecnx;
    @FXML
    private Label lbGroupe1111;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceMatchEvent sm=new ServiceMatchEvent();
        afficher(sm.afficherMatch());
        
    }    
public void afficher(List<MatchEvent> lsMatch) {

        Container.getChildren().clear();
        for (int i = 0; i < lsMatch.size(); i++) {
            MatchEvent jr = lsMatch.get(i);
            SM.suppEventDate(jr);
            Pane single = new Pane();
            single.setPrefHeight(170);
            single.setPrefWidth(580);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(9);
            logo.setLayoutY(12);
            logo.setFitWidth(110);
            logo.setFitHeight(110);
            logo.setPreserveRatio(false);
            Image image;
            String im = SM.getEquipe(jr.getNomEquipeA()).getLogo_equipe();
            image = new Image("file:" + im);
            logo.setImage(image);

            ImageView logoA = new ImageView();
            logoA.setLayoutX(400);
            logoA.setLayoutY(12);
            logoA.setFitWidth(110);
            logoA.setFitHeight(110);
            logoA.setPreserveRatio(false);
            Image imageA;
            String imA = SM.getEquipe(jr.getNomEquipeB()).getLogo_equipe();
      
            imageA = new Image("file:" + imA);
            logoA.setImage(imageA);

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(170);
            name.setLayoutY(20);
            name.setStyle("-fx-font : 18pt \"Times New Roman \";");
            name.setText(jr.getTitre());

            Label dateMatch = new Label();
            dateMatch.setPrefHeight(27);
            dateMatch.setPrefWidth(USE_COMPUTED_SIZE);
            dateMatch.setLayoutX(230);
            dateMatch.setLayoutY(60);
            dateMatch.setStyle("-fx-font-size :15");
            dateMatch.setText("" + jr.getDateMatch());

            FontAwesomeIconView iconc = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
            iconc.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
                    + "-fx-fill:#ec2525;"
            );
            iconc.setLayoutX(200);
            iconc.setLayoutY(80);

            FontAwesomeIconView iconA = new FontAwesomeIconView(FontAwesomeIcon.SOCCER_BALL_ALT);
            iconA.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
                    + "-fx-fill:#ec2525;"
            );
            iconA.setLayoutX(27);
            iconA.setLayoutY(150);

            Label prix = new Label();
            prix.setPrefHeight(27);
            prix.setPrefWidth(USE_COMPUTED_SIZE);
            prix.setLayoutX(50);
            prix.setLayoutY(130);
            prix.setStyle("-fx-font: bold  13pt \"Times New Roman\";");
            prix.setText(jr.getNomEquipeA());

            Label eq = new Label();
            eq.setPrefHeight(27);
            eq.setPrefWidth(USE_COMPUTED_SIZE);
            eq.setLayoutX(410);
            eq.setLayoutY(130);
            eq.setStyle("-fx-font: bold  13pt \"Times New Roman\";");
            eq.setText(jr.getNomEquipeB());

            FontAwesomeIconView iconB = new FontAwesomeIconView(FontAwesomeIcon.SOCCER_BALL_ALT);
            iconB.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
                    + "-fx-fill:#ec2525;"
            );
            iconB.setLayoutX(390);
            iconB.setLayoutY(150);

            

            
           
            
            single.getChildren().add(logo);
            single.getChildren().add(logoA);
            single.getChildren().add(name);
            single.getChildren().add(dateMatch);
            single.getChildren().add(iconA);
            single.getChildren().add(prix);
            single.getChildren().add(iconc);
            single.getChildren().add(eq);
            single.getChildren().add(iconB);
            
            Container.getChildren().add(single);

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
    private void grpExist(MouseEvent event) {
        lbGroupe.setVisible(false);
    }

    @FXML
    private void grpRntred(MouseEvent event) {
        lbGroupe.setVisible(true);
    }

    @FXML
    private void GroupeInt(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EventforUserController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
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
        lbForm.setVisible(false);
    }

    @FXML
    private void formEnt(MouseEvent event) {
        lbForm.setVisible(true);
    }

    @FXML
    private void formationInt(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EventforUserController.this.getClass().getResource("AfficherFormation.fxml"));
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
        lbMatch.setVisible(false);
    }

    @FXML
    private void evnEntr(MouseEvent event) {
        lbMatch.setVisible(true);
    }

    @FXML
    private void EventAct(MouseEvent event) {
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EventforUserController.this.getClass().getResource("EventforUser.fxml"));
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
        lbGroupe1111.setVisible(false);
    }

    @FXML
    private void actionentred(MouseEvent event) {
        lbGroupe1111.setVisible(true);
    }

    @FXML
    private void logExit(MouseEvent event) {
        lbdecnx.setVisible(false);
    }

    @FXML
    private void logEntr(MouseEvent event) {
        lbdecnx.setVisible(true);
    }
    
}
