/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import entites.Adherent;
import entites.Groupe;
import services.AdherentCRUD;
import services.GroupeCRUD;
import tools.Mail;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AjoutAdherentauGroupeController implements Initializable {

    FXMLLoginController log= new FXMLLoginController();
    Adherent u1 = log.user;
    Adherent u=new Adherent();
    @FXML
    private TextField email;
    @FXML
    private Button btnajt;
    Groupe g1=new Groupe();
    @FXML
    private Label l1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         GroupeCRUD gcd = new GroupeCRUD();
         AdherentCRUD acd = new AdherentCRUD();
         TextFields.bindAutoCompletion(email, acd.sugg());
        btnajt.setOnAction((e)->{
            if(ctrlsaisie()){
              u=acd.byemail(email.getText());
            if(gcd.verifonentry(g1, u)){  
               if (gcd.verifexist(g1, u)){
            gcd.ajouterAdherentauGroupe(g1, u);
            Mail mail = new Mail();
            mail.envoyer(u1, g1, email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Personne ajoutée");
            alert.setHeaderText("Personne ajoutée");
            alert.setContentText("Personne ajoutée et il va recevoir un email sur \n"+email.getText());
            alert.showAndWait();}else{
                   Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Personne déja ajoutée");
            alert.setHeaderText(null);
            alert.setContentText("Cette personne est un membre du groupe ");
            alert.showAndWait();
               ;}}
            else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Personne non ajoutée");
            alert.setHeaderText(null);
            alert.setContentText("Cette personne a quitté le groupe définitivement");
            alert.showAndWait(); 
            }
            }
        });
    }    
    private boolean ctrlsaisie() {
        if (email.getText().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme");
            alert.setHeaderText("Email non Valide");
            alert.setContentText("Ecrire un email valide");
            alert.showAndWait();
            
            return false;

        }
        return true;
    }
    public void fill (Groupe g){
        g1=g;
    }

    @FXML
    private void mouseexited(MouseEvent event) {
        l1.setText("");
    }

    @FXML
    private void mouseentered(MouseEvent event) {
        l1.setText("Appuyer pour ajouter");
    }
}
