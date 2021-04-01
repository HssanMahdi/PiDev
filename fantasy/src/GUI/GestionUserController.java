/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Adherent;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.AdherentCRUD;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class GestionUserController implements Initializable {

    @FXML
    private Label labelNom;
    Adherent ad = new Adherent();
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;
    int iduser;
    @FXML
    private CheckBox checkbox;
    private Label lb4;
    private Label lb;
    private Label lb1;
    private Label lb2;

    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    private Label lb3;
    private Label lb5;
    @FXML
    private FontAwesomeIconView btnlog_out;
    private Label lb6;
    @FXML
    private Button dash_id;
    @FXML
    private Button btn_formACT;
    @FXML
    private FontAwesomeIconView grp_btn;
    @FXML
    private FontAwesomeIconView form_btn;
    @FXML
    private FontAwesomeIconView event_btn;
    @FXML
    private Label lbGroupe1;
    @FXML
    private Label lbForm1;
    @FXML
    private Label lbMatch1;
    @FXML
    private Label lbdecnx1;
    @FXML
    private Label lbGroupe11111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ad = FXMLLoginController.user;
        labelNom.setText(ad.getNom_user());
        tfNom.setText(ad.getNom_user());
        tfEmail.setText(ad.getEmail());
        tfMotdepasse.setText(ad.getPassword());
    }

    @FXML
    private void btnmodifier(ActionEvent event) {
        AdherentCRUD add = new AdherentCRUD();

        ad.setIduser(ad.getId_user());
        ad.setNom_user(tfNom.getText());
        ad.setEmail(tfEmail.getText());
        ad.setPassword(encrypt(tfMotdepasse.getText()));

        add.updateAdherent(ad);

    }

    @FXML
    private void showpw(ActionEvent event) {
        if (checkbox.isSelected()) {
            tfMotdepasse.setPromptText(decrypt(tfMotdepasse.getText()));
            tfMotdepasse.setText("");
            tfMotdepasse.setDisable(true);

        } else {
            tfMotdepasse.setText(decrypt(tfMotdepasse.getPromptText()));
            tfMotdepasse.setPromptText("");
            tfMotdepasse.setDisable(false);
        }
    }

    public String decrypt(String pw) {

        return new String(Base64.getMimeDecoder().decode(pw));
    }

    public String encrypt(String pw) {

        return Base64.getEncoder().encodeToString(pw.getBytes());
    }

       @FXML
    private void interfaceEquipe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("EquipeUserInt.fxml"));
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
        loader.setLocation(GestionUserController.this.getClass().getResource("HighlsUser.fxml"));
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
    private void actualiteInterface(ActionEvent event) {
    }

    @FXML
    private void InterfaceFormation(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("AfficherFormation.fxml"));
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
    private void storeInterface(ActionEvent event) {
    }

        @FXML
    private void grpExist(MouseEvent event) {
        lbGroupe1.setVisible(false);
    }

    @FXML
    private void grpRntred(MouseEvent event) {
        lbGroupe1.setVisible(true);
    }

    @FXML
    private void GroupeInt(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
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
        lbForm1.setVisible(false);
    }

    @FXML
    private void formEnt(MouseEvent event) {
        lbForm1.setVisible(true);
    }

    @FXML
    private void formationInt(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("AfficherFormation.fxml"));
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
        lbMatch1.setVisible(false);
    }

    @FXML
    private void evnEntr(MouseEvent event) {
        lbMatch1.setVisible(true);
    }

    @FXML
    private void EventAct(MouseEvent event) {
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("EventforUser.fxml"));
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
        lbGroupe11111.setVisible(false);
    }

    @FXML
    private void actionentred(MouseEvent event) {
        lbGroupe11111.setVisible(true);
    }

    @FXML
    private void logExit(MouseEvent event) {
        lbdecnx1.setVisible(false);
    }

    @FXML
    private void logEntr(MouseEvent event) {
        lbdecnx1.setVisible(true);
    }

    @FXML
    private void lbgroupe1111action(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionUserController.this.getClass().getResource("GestionUser.fxml"));
        lbGroupe11111.getScene().getWindow().hide();
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
    private void deconxAction(MouseEvent event) {
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

}
