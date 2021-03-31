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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import entites.Adherent;
import entites.Groupe;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AfficherlesAdherentdeGroupeController implements Initializable {

    FXMLLoginController log= new FXMLLoginController();
    Adherent u = log.user;
    private Button btnback;
    @FXML
    private ScrollPane Co;
    @FXML
    private VBox ContainerForm;
    @FXML
    private Label prop;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    public void filltable(List<Adherent> l,Groupe g){
//        AdherentCRUD acd= new AdherentCRUD();
//        Adherent u1= new Adherent();
//        u1=acd.connecter(g.getOwner());
//        prop.setText("Le propriétaire du groupe est : "+u1.getNom_user());
        ContainerForm.getChildren().clear();
        for (int i = 0; i < l.size(); i++) {
        Adherent u1=l.get(i);
        Pane single = new Pane();
             single.setPrefHeight(120);
             single.setPrefWidth(200);
             single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
               FontAwesomeIconView grp = new FontAwesomeIconView(FontAwesomeIcon.USER);
            grp.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:50px;"
                    + "-fx-fill:black;"
            );
            grp.setLayoutX(30);
            grp.setLayoutY(80);
                  FontAwesomeIconView sep = new FontAwesomeIconView(FontAwesomeIcon.ANGLE_DOUBLE_RIGHT);
            sep.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:35px;"
                    + "-fx-fill:#e13131;"
            );
            sep.setLayoutX(300);
            sep.setLayoutY(70);
            
     
         Label name1 = new Label();
            name1.setPrefHeight(27);
            name1.setPrefWidth(USE_COMPUTED_SIZE);
            name1.setLayoutX(100);
            name1.setLayoutY(40);
            name1.setStyle("-fx-font : 18pt \"Times New Roman \";");
            name1.setText(u1.getNom_user());    
            
                  FontAwesomeIconView sc = new FontAwesomeIconView(FontAwesomeIcon.TROPHY);
            sc.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );
            sc.setLayoutX(350);
            sc.setLayoutY(40);
            
        Label score = new Label();
            score.setPrefHeight(27);
            score.setPrefWidth(USE_COMPUTED_SIZE);
            score.setLayoutX(380);
            score.setLayoutY(15);
            score.setStyle("-fx-font : 18pt \"Times New Roman \";");
            score.setText("Score");
        Label score1 = new Label();
            score1.setPrefHeight(27);
            score1.setPrefWidth(USE_COMPUTED_SIZE);
            score1.setLayoutX(390);
            score1.setLayoutY(70);
            score1.setStyle("-fx-font :bold 15pt \"Times New Roman \";");
            score1.setText(Integer.toString(u1.getScore_user()));
            
           
        single.getChildren().add(grp);    
        single.getChildren().add(sc); 
        single.getChildren().add(sep);  
        single.getChildren().add(name1);
        single.getChildren().add(score);
        single.getChildren().add(score1);
        ContainerForm.getChildren().add(single);
        }
        
        
        
        
        
        
//        btnback.setOnAction((e)->{ 
//        FXMLLoader loader = new FXMLLoader();
//          loader.setLocation(getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
//          try {
//            loader.load();
//            } catch (IOException ex) {
//            System.out.println(ex);
//        }
//          
//          btnback.getScene().getWindow().hide();
//          Parent parent = loader.getRoot();
//          Stage stage = new Stage();
//          stage.setScene(new Scene(parent));
//          stage.show();
//        });
    
    };
    
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
        loader.setLocation(AfficherlesAdherentdeGroupeController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
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
        loader.setLocation(AfficherlesAdherentdeGroupeController.this.getClass().getResource("AfficherFormation.fxml"));
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
        loader.setLocation(AfficherlesAdherentdeGroupeController.this.getClass().getResource("EventforUser.fxml"));
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