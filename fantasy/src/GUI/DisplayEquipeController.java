/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ServiceEquipe;
import services.ServiceJoueur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Equipe;
import tools.UploadImage;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DisplayEquipeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfStade;
    @FXML
    private TextField logoEq;
    @FXML
    private Button browse;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField search;
    @FXML
    private AnchorPane main;
    ServiceEquipe SA = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    @FXML
    private VBox equipeContainer;
    File f;
    @FXML
    private Button load;

    static int idJoueurEquipe;
    @FXML
    private Button button;
    @FXML
    private Label lb;
    private Button btnDashboard;
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
    private FontAwesomeIconView btnlog_out;

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
        ServiceEquipe se = new ServiceEquipe();
        equipeContainer.getChildren().clear();

        for (int i = 0; i < lsEquipe.size(); i++) {
            Equipe actuel = lsEquipe.get(i);
            Pane single = new Pane();
            single.setStyle("-fx-border-color:#dacece; " + "  -fx-border-width: 0 0 1.2 0; ");
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
                System.out.println(ex.getMessage());
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
            location.setLayoutY(60);
            location.setStyle("-fx-font-size :18");
            location.setFont(Font.font("Times New Roman"));
            location.setText(actuel.getStade());

            ImageView marker = new ImageView();
            marker.setLayoutX(163);
            marker.setLayoutY(66);
            marker.setFitWidth(15);
            marker.setFitHeight(15);
            marker.setPreserveRatio(false);
            Image mark = new Image("uploads/pin.png");
            marker.setImage(mark);

            Button JoueurEq = new Button("Les Joueurs");
            JoueurEq.getStylesheets().add(getClass().getResource("/CSS/ButtonStyles.css").toExternalForm());

            JoueurEq.setPrefHeight(33);
            JoueurEq.setPrefWidth(130);
            JoueurEq.setLayoutX(540);
            JoueurEq.setLayoutY(40);
            JoueurEq.setVisible(true);

            JoueurEq.setOnMouseClicked(
                    (MouseEvent event) -> {

                        Equipe nv = new Equipe(actuel.getId(), actuel.getNom(), actuel.getLogo_equipe(), actuel.getStade());
                        int id = nv.getId();
                        idJoueurEquipe = id;

                        String a = se.getById(id).getNom();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(DisplayEquipeController.this.getClass().getResource("JoueurEquipe.fxml"));

                        try {

                            loader.load();

                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                        JoueurEquipeController displaycontroller = loader.getController();

                        displaycontroller.afficher(SJ.getJoueurEquipe(id));
                        displaycontroller.loadComboBox(a);
                        JoueurEq.getScene().getWindow().hide();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                    }
            );

            FontAwesomeIconView supprimer = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            supprimer.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );

            supprimer.setLayoutX(164);
            supprimer.setLayoutY(126);

            supprimer.setOnMouseClicked((MouseEvent e) -> {

                SA.deleteEquipe(actuel);
                AnchorPane redirect;
                try {
                    redirect = FXMLLoader.load(getClass().getResource("DisplayEquipe.fxml"));
                    main.getChildren().setAll(redirect);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            });

            FontAwesomeIconView modifier = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            modifier.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:Black;"
            );
            modifier.setLayoutX(190);
            modifier.setLayoutY(127);
            modifier.setOnMouseClicked((MouseEvent e) -> {
                Equipe eq = new Equipe(actuel.getId(), actuel.getNom(), actuel.getLogo_equipe(), actuel.getStade());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DisplayEquipeController.this.getClass().getResource("ModifEquipe.fxml"));
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ModifEquipeController modifEquipe = loader.getController();
                modifEquipe.setTextField(eq.getId(), eq.getNom(), eq.getLogo_equipe(), eq.getStade());
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
            single.getChildren().add(supprimer);
            single.getChildren().add(modifier);

            equipeContainer.getChildren().add(single);

        }
    }

    @FXML
    private void AjouterEquipe(ActionEvent event) {
        Equipe e = new Equipe();

        if (validateFields()) {
            UploadImage ui = new UploadImage();
            String path = ui.saveFile(f);
            logoEq.setText(path);
            e.setNom(tfNom.getText());
            e.setLogo_equipe(logoEq.getText());
            e.setstade(tfStade.getText());

            SA.addEquipe(e);
            AnchorPane redirect;
            try {
                redirect = FXMLLoader.load(getClass().getResource("DisplayEquipe.fxml"));
                main.getChildren().setAll(redirect);
            } catch (IOException ex) {
                System.out.println(ex);
            }

        }
    }

    private boolean validateFields() {
        if (tfNom.getText().isEmpty() | logoEq.getText().isEmpty() | tfStade.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valider les champs");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir les champs");
            alert.showAndWait();

            return false;

        }
        return true;
    }

    @FXML
    private void Browse(ActionEvent event) {
        try {
            final FileChooser fileChooser = new FileChooser();
            final Stage stage = null;

            f = fileChooser.showOpenDialog(stage);
            if (f != null) {
                String file = f.toURI().getPath();
                Image image;
                image = new Image(new FileInputStream(file));
                imgView.setImage(image);
                imgView.setPreserveRatio(true);
                String s = f.getName();
                String ur = "C:\\wamp64\\www\\PIProjet\\" + s;
                logoEq.setText(ur);

            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
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
    private void Load(ActionEvent event) {
        AnchorPane redirect;
        try {
            redirect = FXMLLoader.load(getClass().getResource("DisplayEquipe.fxml"));
            main.getChildren().setAll(redirect);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mouseExist(MouseEvent event) {
        lb.setVisible(false);
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        lb.setVisible(true);
    }

    private void DashboardAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("DisplayHighls.fxml"));
        btnDashboard.getScene().getWindow().hide();
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
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("DisplayEquipe.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("DisplayPlayer.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("AddEvent.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("Repot.fxml"));
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
        loader.setLocation(DisplayEquipeController.this.getClass().getResource("GestionManagerFootball.fxml"));
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
