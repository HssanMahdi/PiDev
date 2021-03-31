/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ServiceJoueur;
import services.ServiceRating;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Joueur;
import entites.ManagerFootball;
import entites.RatingJoueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class JoueurUserIntController implements Initializable {

    @FXML
    private Button backbtn;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox ContainerJR;
    ServiceJoueur SJ = new ServiceJoueur();
    @FXML
    private TextField search;
    @FXML
    private ComboBox<String> sortcombo;
    @FXML
    private Button sort;
    @FXML
    private FontAwesomeIconView Search;
    @FXML
    private AnchorPane main;
    List<Joueur> listJoueur = SJ.getJoueurEquipe(EquipeUserIntController.idJoueurEquipe);
    @FXML
    private Button refrbtn;
    int ide = EquipeUserIntController.idJoueurEquipe;
    @FXML
    private Label lb;
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
        ObservableList<String> options = FXCollections.observableArrayList(
                "Position",
                "Prix"
        );
        sortcombo.getItems().addAll(options);
//        List<Joueur> listJoueur = SJ.getJoueurs();
//
//        afficher(listJoueur);
        // TODO
    }

    public void afficher(List<Joueur> lsJoueur) {

        ContainerJR.getChildren().clear();

        for (int i = 0; i < lsJoueur.size(); i++) {
            Joueur jr = lsJoueur.get(i);
            Pane single = new Pane();
            single.setPrefHeight(170);
            single.setPrefWidth(549);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(12);
            logo.setLayoutY(12);
            logo.setFitWidth(130);
            logo.setFitHeight(148);
            logo.setPreserveRatio(false);
            Image image;
            try {
                image = new Image(new FileInputStream(jr.getLogoJoueur()));
                logo.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(166);
            name.setLayoutY(20);
            name.setStyle("-fx-font : 18pt \"Times New Roman \";");
            name.setText(jr.getNomJoueur() + " " + jr.getPrenomJoueur());

            Label position = new Label();
            position.setPrefHeight(27);
            position.setPrefWidth(USE_COMPUTED_SIZE);
            position.setLayoutX(183);
            position.setLayoutY(55);
            position.setStyle("-fx-font-size :15");
            position.setText("Position " + jr.getPosition());

            FontAwesomeIconView marker = new FontAwesomeIconView(FontAwesomeIcon.ARROWS);
            marker.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:13px;"
                    + "-fx-fill:black;"
            );
            marker.setLayoutX(163);
            marker.setLayoutY(72);

            Label Score = new Label();
            Score.setPrefHeight(27);
            Score.setPrefWidth(USE_COMPUTED_SIZE);
            Score.setLayoutX(185);
            Score.setLayoutY(80);
            Score.setStyle("-fx-font-size :15");
            Score.setText("Score " + jr.getScoreJoueur());

            ImageView markprice = new ImageView();
            markprice.setLayoutX(163);
            markprice.setLayoutY(85);
            markprice.setFitWidth(15);
            markprice.setFitHeight(15);
            markprice.setPreserveRatio(false);
            Image markPr = new Image("uploads/scoore.png");
            markprice.setImage(markPr);

//            ImageView markScore = new ImageView();
            FontAwesomeIconView markScore = new FontAwesomeIconView(FontAwesomeIcon.DOLLAR);
            markScore.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:16px;"
                    + "-fx-fill:black;"
            );
            markScore.setLayoutX(166);
            markScore.setLayoutY(126);

//            markScore.setPreserveRatio(false);
//            Image markScr = new Image("uploads/price.png");
//            markScore.setImage(markScr);
            Label prix = new Label();
            prix.setPrefHeight(27);
            prix.setPrefWidth(USE_COMPUTED_SIZE);
            prix.setLayoutX(183);
            prix.setLayoutY(108);
            prix.setStyle("-fx-font-size :15");
            prix.setText(jr.getPrixJoueur() + " TND");

            Label eq = new Label();
            eq.setPrefHeight(27);
            eq.setPrefWidth(USE_COMPUTED_SIZE);
            eq.setLayoutX(183);
            eq.setLayoutY(132);
            eq.setStyle("-fx-font: bold  13pt \"Times New Roman\";");
            eq.setText(jr.getNomEquipe());

            FontAwesomeIconView iconc = new FontAwesomeIconView(FontAwesomeIcon.SOCCER_BALL_ALT);
            iconc.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
                    + "-fx-fill:#ec2525;"
            );
            iconc.setLayoutX(163);
            iconc.setLayoutY(152);

            Rating rat = new Rating(5);
            rat.setMouseTransparent(true);

            RatingJoueur rj = new RatingJoueur();
            ServiceRating sr = new ServiceRating();

            double rtj = sr.calculAvis(jr.getIdJoueur());
            rat.setRating(rtj);
            rat.setLayoutX(430);
            rat.setLayoutY(15);

            FontAwesomeIconView avis = new FontAwesomeIconView(FontAwesomeIcon.TWITCH);
            avis.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:16px;"
                    + "-fx-fill:black;"
            );
            avis.setOnMouseClicked((MouseEvent event) -> {
                ManagerFootball us = new ManagerFootball(2, "Mahdi", "ghadahajjaji0@gmail.com", "123", "MF");
                Joueur nv = new Joueur(jr.getIdJoueur(), jr.getNomJoueur(), jr.getPrenomJoueur(), jr.getPosition(), jr.getScoreJoueur(), jr.getLogoJoueur(), jr.getPrixJoueur(), jr.getIdG());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(JoueurUserIntController.this.getClass().getResource("RatingJR.fxml"));

                int id = sr.getById(jr.getIdJoueur()).getId_rating();
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }

                RatingJRController displaycontroller = loader.getController();
                displaycontroller.getDetails(id, rtj, nv.getIdJoueur(), us.getId_user());
                displaycontroller.afficher(rtj);

                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
            });
            avis.setLayoutX(610);
            avis.setLayoutY(21);

            single.getChildren().add(logo);
            single.getChildren().add(name);
            single.getChildren().add(position);
            single.getChildren().add(marker);
            single.getChildren().add(Score);
            single.getChildren().add(markScore);
            single.getChildren().add(prix);
            single.getChildren().add(markprice);
            single.getChildren().add(eq);
            single.getChildren().add(iconc);
            single.getChildren().add(rat);
            single.getChildren().add(avis);
            ContainerJR.getChildren().add(single);

        }
    }

    @FXML
    private void back(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(JoueurUserIntController.this.getClass().getResource("EquipeUserInt.fxml"));
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
    private void sort(MouseEvent event) {
        if (sortcombo.getValue().equals("Prix")) {

            ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.TrierPlayer(1, ide);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            afficher(obs);

        } else if (sortcombo.getValue().equals("Position")) {

            ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.TrierPlayer(2, ide);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            afficher(obs);
        }
    }

    private void loadData() {
        afficher(SJ.getJoueurEquipe(EquipeUserIntController.idJoueurEquipe));
    }

    @FXML
    private void Search(MouseEvent event) {
        String charac = search.getText();

        ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.SearchPlayer(charac, ide);
        ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
        afficher(obs);
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        loadData();
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
    private void loadExit(MouseEvent event) {
        lb.setVisible(false);
    }

    @FXML
    private void loadEntred(MouseEvent event) {
        lb.setVisible(true);
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(JoueurUserIntController.this.getClass().getResource("EquipeUserInt.fxml"));
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
        loader.setLocation(JoueurUserIntController.this.getClass().getResource("HighlsUser.fxml"));
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
    private void Search1(KeyEvent event) {
        String charac = search.getText();

        ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.SearchPlayer(charac, ide);
        ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
        afficher(obs);
    }

}
