/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.ManagerFootball;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ManagerFootballCRUD;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class ModifierManagerFootballController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;
    int idManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnmodifier(ActionEvent event) {
          ManagerFootball mn = new ManagerFootball();
        ManagerFootballCRUD mnc = new  ManagerFootballCRUD();
        mn.setIduser(idManager);

        mn.setNom_user(tfNom.getText());
        mn.setEmail(tfEmail.getText());
        mn.setPassword(encrypt(tfMotdepasse.getText()));
        System.out.println(mn.getId_user());
        mnc.updateManagerFootball(mn);
    }
    
    void fillTextField(int id, String nom, String email, String mdp) {
        idManager = id;
        tfNom.setText(nom);
        tfEmail.setText(email);
        tfMotdepasse.setText(mdp);
    }
    public String encrypt(String pw){
     
     return Base64.getEncoder().encodeToString(pw.getBytes()) ;
    }
    
}
