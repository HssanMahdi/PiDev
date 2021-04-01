/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.AdminSysteme;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Base64;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.AdminSystemeCRUD;
import tools.ExportPdf;

import tools.Mail;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class AdminSystemeController implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private Label label;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfMotdepasse;
    @FXML
    private ComboBox<String> comboTri;
    @FXML
    private Button btn_tri;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox containerAd;
    @FXML
    private Pane main;
    PreparedStatement pst;
    AdminSystemeCRUD aa = new AdminSystemeCRUD();
    @FXML
    private Label lb;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
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
    private FontAwesomeIconView btnM;
    @FXML
    private Label lb3;
    private Label lb4;
    private Label lb5;
    @FXML
    private FontAwesomeIconView btnlog_out;
    @FXML
    private Label lb6;
    @FXML
    private FontAwesomeIconView btnstat;
    @FXML
    private FontAwesomeIconView btn1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Nom",
                "Email"
        );
        comboTri.getItems().addAll(options);
        List<AdminSysteme> listAdmin = aa.displayAdminSysteme();

        afficher(listAdmin);
    }

    @FXML
    private void AjouterAdminSysteme(ActionEvent event) {
        AdminSysteme p = new AdminSysteme();
        if (validerChamps()) {
            p.setNom_user(tfNom.getText());
            p.setEmail(tfEmail.getText());
            p.setPassword(encrypt(tfMotdepasse.getText()));
            aa.ajouterAdminSysteme(p);
            Mail mail = new Mail();
            mail.envoyer1(tfEmail.getText(), tfNom.getText(), tfMotdepasse.getText());

        }
    }

    private boolean validerChamps() {
        if (tfNom.getText().isEmpty() | tfEmail.getText().isEmpty() | tfMotdepasse.getText().isEmpty()) {
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

    private void afficher(List<AdminSysteme> lsAdmin) {
        AdminSystemeCRUD se = new AdminSystemeCRUD();
        containerAd.getChildren().clear();

        for (int i = 0; i < lsAdmin.size(); i++) {
            AdminSysteme actuel = lsAdmin.get(i);
            Pane single = new Pane();
            // single.setStyle("-fx-border-color:#dacece; " + "  -fx-border-width: 0 0 1 0; ");
            single.setPrefHeight(100);
            single.setPrefWidth(700);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.USER);
            icon.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:60px;"
                    + "-fx-fill:black;"
            );
            icon.setLayoutX(30);
            icon.setLayoutY(60);

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(110);
            name.setLayoutY(10);
            name.setStyle("-fx-font-size :17");
            name.setFont(Font.font("Times New Roman"));
            name.setText(actuel.getNom_user());

            Label location = new Label();
            location.setPrefHeight(27);
            location.setPrefWidth(USE_COMPUTED_SIZE);
            location.setLayoutX(130);
            location.setLayoutY(46);
            location.setStyle("-fx-font-size :14");
            location.setFont(Font.font("Times New Roman"));
            location.setText(actuel.getEmail());
            
            FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.ENVELOPE_ALT);
            icon1.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:15px;"
                    + "-fx-fill:black;"
            );
            icon1.setLayoutX(110);
            icon1.setLayoutY(66);
            
          

            FontAwesomeIconView supprimer = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            supprimer.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:20px;"
                    + "-fx-fill:#e13131;"
            );

            supprimer.setLayoutX(400);
            supprimer.setLayoutY(20);
            
            supprimer.setOnMouseClicked(e -> {
                se.supprimerAdminSysteme(actuel);
                AnchorPane redirect;
                try {
                    redirect = FXMLLoader.load(getClass().getResource("AdminSysteme.fxml"));
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
            modifier.setLayoutX(370);
            modifier.setLayoutY(21);
            modifier.setOnMouseClicked((e) -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AdminSystemeController.this.getClass().getResource("ModifierAdminSysteme.fxml"));
                try {

                    loader.load();

                } catch (IOException ex) {
                    System.out.println(ex);
                }

//                AdminSysteme as = new AdminSysteme();
                ModifierAdminSystemeController modifAdmin = loader.getController();
                modifAdmin.fillTextField(actuel.getId_user(), actuel.getNom_user(), actuel.getEmail(), actuel.getPassword());
                //   modifEquipe.setTextField();
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
            });
            single.getChildren().add(icon);
            single.getChildren().add(name);
            single.getChildren().add(location);

            single.getChildren().add(supprimer);
            single.getChildren().add(modifier);
            single.getChildren().add(icon1);

            containerAd.getChildren().add(single);

        }
    }

    
      


    @FXML
    private void recherche(MouseEvent event) {
        AdminSystemeCRUD Sa = new AdminSystemeCRUD();
        String s = recherche.getText();

        List<AdminSysteme> a = (List<AdminSysteme>) Sa.Rechercher(s);
        afficher(a);
    }

    @FXML
    private void TrierAd(ActionEvent event) {
        if (comboTri.getValue().equals("Nom")) {

            List<AdminSysteme> a = (List<AdminSysteme>) aa.Trier(1);
            ObservableList<AdminSysteme> obs = FXCollections.observableArrayList(a);
            afficher(obs);

        } else if (comboTri.getValue().equals("Email")) {

            List<AdminSysteme> a = (List<AdminSysteme>) aa.Trier(2);
            ObservableList<AdminSysteme> obs = FXCollections.observableArrayList(a);
            afficher(obs);
        }
    }

    @FXML
    private void AfficherAdmin(ActionEvent event) {
         List<AdminSysteme> listAdmin = aa.displayAdminSysteme();

        afficher(listAdmin);
    }

    @FXML
    private void AdminExisted(MouseEvent event) {
         lb.setVisible(false);
    }

    @FXML
    private void AdminEntred(MouseEvent event) {
         lb.setVisible(true);
    }

    @FXML
    private void ManagerExited(MouseEvent event) {
     lb1.setVisible(false);
    }

    @FXML
    private void ManagerEntred(MouseEvent event) {
        lb1.setVisible(true);
    }

    @FXML
    private void StatExited(MouseEvent event) {
         lb2.setVisible(false);
    }

    @FXML
    private void StatEntred(MouseEvent event) {
        lb2.setVisible(true);
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminSystemeController.this.getClass().getResource("EquipeUserInt.fxml"));
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
        loader.setLocation(AdminSystemeController.this.getClass().getResource("AfficherFormation.fxml"));
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
        loader.setLocation(AdminSystemeController.this.getClass().getResource("HighlsUser.fxml"));
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
    private void AfficherStat(MouseEvent event) throws IOException {
                btn_stat.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("StatistiqueNbrUser.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
      public String encrypt(String pw){
     
     return Base64.getEncoder().encodeToString(pw.getBytes()) ;
    }

  

    @FXML
    private void GestionManager(MouseEvent event) throws IOException {
                   btnM.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ManagerFootball.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void produitExited(MouseEvent event) {
        lb3.setVisible(false);
    }

    @FXML
    private void produitEntred(MouseEvent event) {
        lb3.setVisible(true);
    }

    private void commandeExited(MouseEvent event) {
        lb4.setVisible(false);
    }

    private void commandeEntred(MouseEvent event) {
        lb4.setVisible(true);
    }

    private void categorieExited(MouseEvent event) {
        lb5.setVisible(false);
    }

    private void categorieEntred(MouseEvent event) {
        lb5.setVisible(true);
    }

    @FXML
    private void log_outExited(MouseEvent event) {
        lb6.setVisible(false);
    }

    @FXML
    private void log_outEntred(MouseEvent event) {
        lb6.setVisible(true);
    }

    @FXML
    private void logout(MouseEvent event) {
        try {
            btnlog_out.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSystemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pdfActionBtn(MouseEvent event) {
        ExportPdf exp = new ExportPdf();
        exp.pdfsG();
    }



}
