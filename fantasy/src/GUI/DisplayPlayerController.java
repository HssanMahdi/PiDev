/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ServiceEquipe;
import services.ServiceJoueur;
import tools.MyConnection;
import tools.ExportExcel;
import tools.ExportPdf;
import tools.Mail;
import tools.UploadImage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Joueur;
import entites.ManagerFootball;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class DisplayPlayerController implements Initializable {

    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;

    @FXML
    private Button button;
    @FXML
    private Button browse;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ComboBox<String> positionComoBox;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfPrix;
    @FXML
    private VBox joueurContainer;
    ServiceJoueur SJ = new ServiceJoueur();
    @FXML
    private AnchorPane main;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField search;
    @FXML
    private FontAwesomeIconView Search;
    @FXML
    private ComboBox<String> sortcombo;
    @FXML
    private Button sort;
    ServiceEquipe SE = new ServiceEquipe();
    File f;
    ManagerFootball mn = new ManagerFootball(1, "Ghada", "ghada.hajjaji@esprit.tn", "azz", "MF");
    @FXML
    private TextField tfLogo;
    List<Joueur> listJoueur = SJ.getJoueurs();
    @FXML
    private Button refrbtn;
    @FXML
    private Label lb;
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
    private Label lbExcel;
    @FXML
    private Label lbPDF;
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher(listJoueur);

        ObservableList<String> list = FXCollections.observableArrayList("mil", "Att", "Des", "G");
        positionComoBox.setItems(list);
        ObservableList<String> options = FXCollections.observableArrayList(
                "Position",
                "Prix"
        );
        sortcombo.getItems().addAll(options);
        //Liste des equipes
        combo.setItems(SJ.FillCombo());
        // TODO
    }

    private void afficher(List<Joueur> lsJoueur) {

        joueurContainer.getChildren().clear();

        for (int i = 0; i < lsJoueur.size(); i++) {
            Joueur jr = lsJoueur.get(i);
            Pane single = new Pane();
            single.setPrefHeight(170);
            single.setPrefWidth(560);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(9);
            logo.setLayoutY(12);
            logo.setFitWidth(140);
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
            name.setStyle("-fx-font-size :22");
            name.setFont(Font.font("Times New Roman"));
            name.setText(jr.getNomJoueur() + "  " + jr.getPrenomJoueur());

            Label position = new Label();
            position.setPrefHeight(27);
            position.setPrefWidth(USE_COMPUTED_SIZE);
            position.setLayoutX(180);
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

            FontAwesomeIconView markScore = new FontAwesomeIconView(FontAwesomeIcon.DOLLAR);
            markScore.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:16px;"
                    + "-fx-fill:black;"
            );
            markScore.setLayoutX(166);
            markScore.setLayoutY(103);

//            markScore.setPreserveRatio(false);
//            Image markScr = new Image("uploads/price.png");
//            markScore.setImage(markScr);
            Label prix = new Label();
            prix.setPrefHeight(27);
            prix.setPrefWidth(USE_COMPUTED_SIZE);
            prix.setLayoutX(183);
            prix.setLayoutY(84);
            prix.setStyle("-fx-font-size :15");
            prix.setText(jr.getPrixJoueur() + " TND");

            Label eq = new Label();
            eq.setPrefHeight(27);
            eq.setPrefWidth(USE_COMPUTED_SIZE);
            eq.setLayoutX(183);
            eq.setLayoutY(109);
            eq.setStyle("-fx-font: bold  13pt \"Times New Roman\";");
            eq.setText(jr.getNomEquipe());

            FontAwesomeIconView iconc = new FontAwesomeIconView(FontAwesomeIcon.SOCCER_BALL_ALT);
            iconc.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:14px;"
                    + "-fx-fill:#ec2525;"
            );
            iconc.setLayoutX(163);
            iconc.setLayoutY(129);

            FontAwesomeIconView supprimer = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            supprimer.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );

            supprimer.setLayoutX(602);
            supprimer.setLayoutY(31);

            supprimer.setOnMouseClicked((MouseEvent e) -> {

                SJ.deleteJoueur(jr);
                AnchorPane redirect;
                try {
                    redirect = FXMLLoader.load(getClass().getResource("DisplayPlayer.fxml"));
                    main.getChildren().setAll(redirect);
                } catch (IOException ex) {
                    System.out.println(ex);
                }

            });

            FontAwesomeIconView modifier = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            modifier.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:Black;"
            );
            modifier.setLayoutX(573);
            modifier.setLayoutY(32);
            modifier.setOnMouseClicked((MouseEvent e) -> {
                Joueur nv = new Joueur(jr.getIdJoueur(), jr.getNomJoueur(), jr.getPrenomJoueur(), jr.getPosition(), jr.getScoreJoueur(), jr.getLogoJoueur(), jr.getPrixJoueur(), jr.getIdG());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DisplayPlayerController.this.getClass().getResource("ModifJoueurEquipe.fxml"));
                String a = SE.getById(jr.getIdG()).getNom();
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ModifJoueurEquipeController modifJR = loader.getController();
                modifJR.ComboBoxPosition(jr.getPosition());
                modifJR.ComboBoxEquipe(a);
                modifJR.setTextField(nv.getIdJoueur(), nv.getNomJoueur(), nv.getPrenomJoueur(), nv.getScoreJoueur(), nv.getLogoJoueur(), nv.getPrixJoueur());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
            });

            single.getChildren().add(logo);
            single.getChildren().add(name);
            single.getChildren().add(position);
            single.getChildren().add(marker);

            single.getChildren().add(markScore);
            single.getChildren().add(prix);
            single.getChildren().add(eq);
            single.getChildren().add(iconc);

            single.getChildren().add(supprimer);
            single.getChildren().add(modifier);

            joueurContainer.getChildren().add(single);

        }
    }

    @FXML
    private void AjouterJoueur(ActionEvent event) {

        Joueur e = new Joueur();

        if (validateFields()) {
            UploadImage ui = new UploadImage();
            String path = ui.saveFile(f);
           // System.out.println(path);
            tfLogo.setText(path);
            e.setLogoJoueur(tfLogo.getText());
            e.setNomJoueur(tfNom.getText());
            e.setPrenomJoueur(tfPrenom.getText());

            e.setPosition(positionComoBox.getSelectionModel().getSelectedItem());
            e.setPrixJoueur(Integer.parseInt(tfPrix.getText()));

//
            int i = SE.getByName(combo.getValue()).getId();
            e.setIdG(i);

            SJ.addJoueur(e);
            AnchorPane redirect;
            try {
                redirect = FXMLLoader.load(getClass().getResource("DisplayPlayer.fxml"));
                main.getChildren().setAll(redirect);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            Mail mail = new Mail();
            mail.envoyer2(mn, tfNom.getText(), tfPrenom.getText(), positionComoBox.getSelectionModel().getSelectedItem(), Integer.parseInt(tfPrix.getText()), combo.getValue());

        }
    }

    private boolean validateFields() {
        if (positionComoBox.getSelectionModel().isEmpty() | combo.getSelectionModel().isEmpty() | tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty()
                | tfLogo.getText().isEmpty() | tfPrix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valider les champs");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir les champs");
            alert.showAndWait();

            return false;
        } else {
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
                String ur = "C:\\wamp\\www\\PIProjet\\" + s;
                tfLogo.setText(ur);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void sort(MouseEvent event) {
        if (sortcombo.getValue().equals("Prix")) {

            List<Joueur> a = SJ.Trier(1);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            afficher(obs);

        } else if (sortcombo.getValue().equals("Position")) {

            List<Joueur> a = SJ.Trier(2);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            afficher(obs);
        }
    }

    @FXML
    private void Search(MouseEvent event) {
        String charac = search.getText();
        List<Joueur> a = SJ.Search(charac);
        afficher(a);
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        AnchorPane redirect;
        try {
            redirect = FXMLLoader.load(getClass().getResource("DisplayPlayer.fxml"));
            main.getChildren().setAll(redirect);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @FXML
//    private void closeButtonAction(MouseEvent event) {
//
//    // get a handle to the stage
//    Stage stage = (Stage) closeButton.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//}
    @FXML
    private void ExportExl(ActionEvent event) {
        ExportExcel ex = new ExportExcel();
        ex.exportFile();
    }

    @FXML
    private void ExportPDF(ActionEvent event) {
        ExportPdf ep = new ExportPdf();
        ep.pdfs();

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
        loader.setLocation(DisplayPlayerController.this.getClass().getResource("DisplayEquipe.fxml"));
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
        loader.setLocation(DisplayPlayerController.this.getClass().getResource("DisplayPlayer.fxml"));
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
        loader.setLocation(DisplayPlayerController.this.getClass().getResource("DisplayHighls.fxml"));
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
    private void PdfExit(MouseEvent event) {
        lbPDF.setVisible(false);
    }

    @FXML
    private void PdfEntred(MouseEvent event) {
        lbPDF.setVisible(true);
    }

    @FXML
    private void ExcelExit(MouseEvent event) {
        lbExcel.setVisible(false);
    }

    @FXML
    private void ExcelEntred(MouseEvent event) {
         lbExcel.setVisible(true);
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
        loader.setLocation(DisplayPlayerController.this.getClass().getResource("DisplayHighls.fxml"));
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
    }

    @FXML
    private void statActionInterface(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DisplayPlayerController.this.getClass().getResource("Repot.fxml"));
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



