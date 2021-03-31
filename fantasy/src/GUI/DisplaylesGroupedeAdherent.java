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
import javafx.event.EventHandler;
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
import services.GroupeCRUD;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class DisplaylesGroupedeAdherent implements Initializable {
    FXMLLoginController log= new FXMLLoginController();
    Adherent u = log.user;
    GroupeCRUD gcd = new GroupeCRUD();
    Groupe g=new Groupe();
    @FXML
    private VBox ContainerForm;
    @FXML
    private VBox ContainerForm1;
    @FXML
    private ScrollPane Co;
    @FXML
    private Label lbusr;
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
 
        afficher();
    
    }   
        
    public void afficher() {
    ContainerForm.getChildren().clear();
    List<Groupe> ls =gcd.displayGrouppourAdherent(u) ;
    List<Groupe> ls1 = gcd.displayOwnedGroupspourAdherent(u);
    ls1.stream().map((Groupe g1) -> {
        Pane single = new Pane();
        single.setPrefHeight(140);
        single.setPrefWidth(180);
        single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        FontAwesomeIconView grp = new FontAwesomeIconView(FontAwesomeIcon.GROUP);
            grp.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:50px;"
                    + "-fx-fill:black;"
            );
            grp.setLayoutX(30);
            grp.setLayoutY(80);
        Label name1 = new Label();
            name1.setPrefHeight(27);
            name1.setPrefWidth(USE_COMPUTED_SIZE);
            name1.setLayoutX(110);
            name1.setLayoutY(30);
           
            name1.setStyle("-fx-font : 18pt \"Times New Roman \";" );
            name1.setText("Nom de Groupe");
        Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(110);
            name.setLayoutY(80);
            name.setStyle("-fx-font : 14pt \"Times New Roman \";");
            name.setText(g1.getNom());
        FontAwesomeIconView btnmem = new FontAwesomeIconView(FontAwesomeIcon.USER);
            btnmem.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:black;"
            );
            btnmem.setLayoutX(450);
            btnmem.setLayoutY(80);
            Label pop = new Label();
            pop.setPrefHeight(27);
            pop.setPrefWidth(USE_COMPUTED_SIZE);
            pop.setLayoutX(350);
            pop.setLayoutY(98);
            pop.setStyle("  -fx-font-size: 13px ;"
                    + "-fx-text-fill: #e1012d;"
                    + "-fx-font-weight: bold;");
    
  

          

            btnmem.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setText("Membres de groupe");
            }
        });
            btnmem.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setText("");
            }
        });
            
            
            btnmem.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherlesAdherentdeGroupe.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex);}

            AfficherlesAdherentdeGroupeController controller = loader.getController();
            controller.filltable(gcd.displayAdherentdeGroupe(g),g);
            btnmem.getScene().getWindow().hide();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            
            
        }); 
        FontAwesomeIconView btnsupp = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            btnsupp.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );
            btnsupp.setLayoutX(450);
            btnsupp.setLayoutY(40);
            
        btnsupp.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            gcd.supprimerGroupe(g);
            afficher();
        });
        FontAwesomeIconView btnupdate = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            btnupdate.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:black;"
            );
            btnupdate.setLayoutX(410);
            btnupdate.setLayoutY(40);
           
            btnupdate.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifGroupe.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            ModifGroupeController controller = loader.getController();
            controller.fill(g);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            btnupdate.getScene().getWindow().hide();
            
        }); 
            
        FontAwesomeIconView btnadd = new FontAwesomeIconView(FontAwesomeIcon.USER_PLUS);
            btnadd.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );
            btnadd.setLayoutX(410);
            btnadd.setLayoutY(80);

            btnadd.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setText("Ajouter un membre");
            }
        });
            btnadd.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pop.setText("");
            }
        });
            btnadd.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AjoutAdherentauGroupe.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            AjoutAdherentauGroupeController controller = loader.getController();
            controller.fill(g);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }); 
        single.getChildren().add(name1);
        single.getChildren().add(name);
        single.getChildren().add(grp);
        single.getChildren().add(btnmem);
        single.getChildren().add(pop);
        single.getChildren().add(btnsupp);
        single.getChildren().add(btnupdate);
        single.getChildren().add(btnadd);
            return single;
        }).forEachOrdered((single) -> {
            ContainerForm.getChildren().add(single);
        });
    
    
    ls.stream().map((g1) -> {
        Pane single1 = new Pane();
        single1.setPrefHeight(120);
        single1.setPrefWidth(150);
        single1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
         FontAwesomeIconView grp1 = new FontAwesomeIconView(FontAwesomeIcon.GROUP);
            grp1.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:50px;"
                    + "-fx-fill:black;"
            );
            grp1.setLayoutX(30);
            grp1.setLayoutY(80);
        Label name1 = new Label();
            name1.setPrefHeight(27);
            name1.setPrefWidth(USE_COMPUTED_SIZE);
            name1.setLayoutX(110);
            name1.setLayoutY(30);
            
            name1.setStyle("-fx-font :  18pt \"Times New Roman \";"  );
            name1.setText("Nom de Groupe");
        Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(110);
            name.setLayoutY(70);
            name.setStyle("-fx-font : 14pt \"Times New Roman \";");
            name.setText(g1.getNom());
        FontAwesomeIconView btnmem = new FontAwesomeIconView(FontAwesomeIcon.USER);
            btnmem.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );    
            btnmem.setLayoutX(450);
            btnmem.setLayoutY(40);
           
            btnmem.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherlesAdherentdeGroupe.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
            AfficherlesAdherentdeGroupeController controller = loader.getController();
            controller.filltable(gcd.displayAdherentdeGroupe(g),g);
            btnmem.getScene().getWindow().hide();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            
 
        }); 
            
        FontAwesomeIconView btnquitter = new FontAwesomeIconView(FontAwesomeIcon.SHARE_SQUARE_ALT);
            btnquitter.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:BLACK;"
            );
            btnquitter.setLayoutX(410);
            btnquitter.setLayoutY(40);
            
            btnquitter.setOnMouseClicked((e)->{
            g= gcd.getGroupebyId(g1.getId());
            gcd.supprimerAdherentduGroupe(g, u);
            ContainerForm1.getChildren().clear();
            afficher();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Quitdef.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            QuitdefController controller = loader.getController();
            controller.fill(g);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            
            
        });
            single1.getChildren().add(grp1);
        single1.getChildren().add(name1);
        single1.getChildren().add(name);
        single1.getChildren().add(btnmem);
        single1.getChildren().add(btnquitter);
            return single1;
            
        }).forEachOrdered((single1) -> {
            ContainerForm1.getChildren().add(single1);
        });
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
        loader.setLocation(DisplaylesGroupedeAdherent.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
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
        loader.setLocation(DisplaylesGroupedeAdherent.this.getClass().getResource("AfficherFormation.fxml"));
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
        loader.setLocation(DisplaylesGroupedeAdherent.this.getClass().getResource("EventforUser.fxml"));
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