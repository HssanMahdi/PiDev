/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.ManagerFootball;
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
import services.ManagerFootballCRUD;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class GestionManagerFootballController implements Initializable {

    @FXML
    private Label labelNom;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Button dash_id;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_formACT;
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
    private FontAwesomeIconView btnlog_out;
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

    ManagerFootball mn= new ManagerFootball();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          mn = FXMLLoginController.manager;
        labelNom.setText(mn.getNom_user());
        tfNom.setText(mn.getNom_user());
        tfEmail.setText(mn.getEmail());
        tfMotdepasse.setText(mn.getPassword());
    }

    @FXML
    private void btnmodifier(ActionEvent event) {
        ManagerFootballCRUD mnn = new ManagerFootballCRUD();

        mn.setIduser(mn.getId_user());
        mn.setNom_user(tfNom.getText());
        mn.setEmail(tfEmail.getText());
        mn.setPassword(encrypt(tfMotdepasse.getText()));

        mnn.updateManagerFootball(mn);
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("DisplayEquipe.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("DisplayHighls.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("AfficherFormation.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("AfficherFormation.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("EventforUser.fxml"));
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
        loader.setLocation(GestionManagerFootballController.this.getClass().getResource("GestionUser.fxml"));
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
       
     

  