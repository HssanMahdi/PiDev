/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AdminSystemeController implements Initializable {

    @FXML
    private AnchorPane main;
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
    private ComboBox<?> comboTri;
    @FXML
    private Button btn_tri;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox containerAd;
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
    private FontAwesomeIconView btn1;
    @FXML
    private FontAwesomeIconView btnM;
    @FXML
    private FontAwesomeIconView btnstat;
    @FXML
    private Label lb;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
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
    }    

    @FXML
    private void AjouterAdminSysteme(ActionEvent event) {
    }

    @FXML
    private void AfficherAdmin(ActionEvent event) {
    }

    @FXML
    private void recherche(MouseEvent event) {
    }

    @FXML
    private void TrierAd(ActionEvent event) {
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
    private void AdminExisted(MouseEvent event) {
    }

    @FXML
    private void AdminEntred(MouseEvent event) {
    }

    @FXML
    private void ManagerExited(MouseEvent event) {
    }

    @FXML
    private void ManagerEntred(MouseEvent event) {
    }

    @FXML
    private void GestionManager(MouseEvent event) {
    }

    @FXML
    private void StatExited(MouseEvent event) {
    }

    @FXML
    private void StatEntred(MouseEvent event) {
    }

    @FXML
    private void AfficherStat(MouseEvent event) {
    }

    @FXML
    private void produitExited(MouseEvent event) {
    }

    @FXML
    private void produitEntred(MouseEvent event) {
    }

    @FXML
    private void commandeExited(MouseEvent event) {
    }

    @FXML
    private void commandeEntred(MouseEvent event) {
    }

    @FXML
    private void categorieExited(MouseEvent event) {
    }

    @FXML
    private void categorieEntred(MouseEvent event) {
    }

    @FXML
    private void log_outExited(MouseEvent event) {
    }

    @FXML
    private void log_outEntred(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void pdfActionBtn(MouseEvent event) {
    }
    
}
