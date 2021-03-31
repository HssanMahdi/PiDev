/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Equipe;
import services.ServiceEquipe;
import services.ServiceJoueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EquipeUserIntController implements Initializable {

    @FXML
    private AnchorPane main;
    ServiceEquipe SA = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();

    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox ContainerEq;
    @FXML
    private TextField search;
    static int idJoueurEquipe;
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

        List<Equipe> listEquipe = SA.getEquipes();

        afficher(listEquipe);

        // TODO
    }

    private void afficher(List<Equipe> lsEquipe) {

        ContainerEq.getChildren().clear();

        for (int i = 0; i < lsEquipe.size(); i++) {
            Equipe actuel = lsEquipe.get(i);
            Pane single = new Pane();
            single.setPrefHeight(140);
            single.setPrefWidth(547);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(23);
            logo.setLayoutY(18);
            logo.setFitWidth(100);
            logo.setFitHeight(100);
            logo.setPreserveRatio(false);
            Image image;
            try {
                image = new Image(new FileInputStream(actuel.getLogo_equipe()));
                logo.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayEquipeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(166);
            name.setLayoutY(20);
            name.setStyle("-fx-font-size :25");
            name.setFont(Font.font("Times New Roman"));
            name.setText(actuel.getNom());

            Label location = new Label();
            location.setPrefHeight(27);
            location.setPrefWidth(USE_COMPUTED_SIZE);
            location.setLayoutX(180);
            location.setLayoutY(68);
            location.setStyle("-fx-font-size :20");
            location.setFont(Font.font("Times New Roman"));
            location.setText(actuel.getStade());

            ImageView marker = new ImageView();
            marker.setLayoutX(163);
            marker.setLayoutY(76);
            marker.setFitWidth(15);
            marker.setFitHeight(15);
            marker.setPreserveRatio(false);
            Image mark = new Image("uploads/pin.png");
            marker.setImage(mark);

            Button JoueurEq = new Button("Les Joueurs");
            JoueurEq.getStylesheets().add(getClass().getResource("/CSS/ButtonStyles.css").toExternalForm());

            JoueurEq.setPrefHeight(33);
            JoueurEq.setPrefWidth(130);
            JoueurEq.setLayoutX(455);
            JoueurEq.setLayoutY(37);
            JoueurEq.setVisible(true);

            JoueurEq.setOnMouseClicked((MouseEvent event) -> {
                Equipe nv = new Equipe(actuel.getId(), actuel.getNom(), actuel.getLogo_equipe(), actuel.getStade());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EquipeUserIntController.this.getClass().getResource("JoueurUserInt.fxml"));
                int id = nv.getId();
                idJoueurEquipe = id;
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                JoueurUserIntController displaycontroller = loader.getController();
                displaycontroller.afficher(SJ.getJoueurEquipe(id));
                JoueurEq.getScene().getWindow().hide();
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
            });

            single.getChildren().add(logo);
            single.getChildren().add(name);
            single.getChildren().add(location);
            single.getChildren().add(marker);
            single.getChildren().add(JoueurEq);

            ContainerEq.getChildren()
                    .add(single);

        }
    }

    @FXML
    private void Search(MouseEvent event) {
        String charac = search.getText();

        List<Equipe> a = SA.SearchTeam(charac);
        ObservableList<Equipe> obs = FXCollections.observableArrayList(a);
        afficher(obs);
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EquipeUserIntController.this.getClass().getResource("EquipeUserInt.fxml"));
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
        loader.setLocation(EquipeUserIntController.this.getClass().getResource("HighlsUser.fxml"));
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
