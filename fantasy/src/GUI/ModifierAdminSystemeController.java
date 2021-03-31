/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.AdminSysteme;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.AdminSystemeCRUD;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class ModifierAdminSystemeController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;
    int idAdmin;
    AdminSysteme add= new AdminSysteme();

    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         add = FXMLLoginController.admin;
        tfNom.setText(add.getNom_user());
        tfEmail.setText(add.getEmail());
        tfMotdepasse.setText(add.getPassword());
        // TODO
    }

    @FXML
    private void btnmodifier(ActionEvent event) {
        AdminSysteme as = new AdminSysteme();
        AdminSystemeCRUD asc = new  AdminSystemeCRUD();
        as.setIduser(idAdmin);

        as.setNom_user(tfNom.getText());
        as.setEmail(tfEmail.getText());
        as.setPassword(encrypt(tfMotdepasse.getText()));
        System.out.println(as.getId_user());
        asc.updateAdminSysteme(as);

    }

    void fillTextField(int id, String nom, String email, String mdp) {
        idAdmin = id;
        tfNom.setText(nom);
        tfEmail.setText(email);
        tfMotdepasse.setText(mdp);
        
    
    }
    public String encrypt(String pw){
     
     return Base64.getEncoder().encodeToString(pw.getBytes()) ;
    }
    
}
