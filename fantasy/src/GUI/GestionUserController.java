/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Adherent;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
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
    @FXML
    private Label lb4;
    @FXML
    private FontAwesomeIconView btnM;
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
    private Label lb3;
    @FXML
    private Label lb5;
    @FXML
    private FontAwesomeIconView btnlog_out;
    @FXML
    private Label lb6;

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
    private void produitExited(MouseEvent event) {
         lb3.setVisible(false);
    }

    @FXML
    private void produitEntred(MouseEvent event) {
         lb3.setVisible(true);
    }

    @FXML
    private void commandeExited(MouseEvent event) {
         lb4.setVisible(false);
    }

    @FXML
    private void commandeEntred(MouseEvent event) {
         lb4.setVisible(true);
    }

    @FXML
    private void categorieExited(MouseEvent event) {
         lb5.setVisible(false);
    }

    @FXML
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
    private void logout(MouseEvent event) throws IOException {
        btnlog_out.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
