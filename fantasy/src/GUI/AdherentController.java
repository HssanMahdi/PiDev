/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entites.Adherent;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.AdherentCRUD;
import tools.Mail;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class AdherentController implements Initializable {

    @FXML
    private PasswordField tfMotdepasse;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnaj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void AjouterAdherent(ActionEvent event) {
     AdherentCRUD ad = new AdherentCRUD();
        Adherent a = new Adherent();
        
    if(validerChamps()){
        
        
             String mdp =encrypt(tfMotdepasse.getText()) ;
             a.setNom_user(tfNom.getText());
             a.setEmail(tfEmail.getText());
             a.setPassword(mdp);
             ad.ajouterAdherent(a);
             
             
//         Password md = new Password();
//          String mdpCrypte1 = Password.hashPassword(tfMotdepasse.getText());


        Mail mail = new Mail();
        mail.envoyer1(tfEmail.getText(),tfNom.getText() ,tfMotdepasse.getText());
         try {
        btnaj.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("GestionUser.fxml"));
        Scene scene = new Scene(root);
        Stage mainStage= new Stage();
        mainStage.setScene(scene);
        mainStage.show();
         } catch (IOException ex) {
             Logger.getLogger(AdherentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        

    }}
    
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
    
     @FXML
    private void precedent(ActionEvent event) throws IOException {
        btnBack.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
     public String encrypt(String pw){
     
     return Base64.getEncoder().encodeToString(pw.getBytes()) ;
    }
     public String decrypt(String pw){
     
     return new String (Base64.getMimeDecoder().decode(pw)) ;
    }
    
}
