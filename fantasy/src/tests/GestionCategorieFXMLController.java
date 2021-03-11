/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fantasy;

import entities.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class GestionCategorieFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfIdSupp;
    @FXML
    private TextField tfIdMod;
    @FXML
    private Label LAfficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateCategorieButton(ActionEvent event) {
        
        int id=0;
          ServiceCategorie sercat = new  ServiceCategorie();
        Categorie c = new  Categorie();
           c.setNomCategorie(tfNom.getText());
           
           id=Integer.parseInt(tfIdMod.getText());
           
           sercat.modifierCategorie(c, id);
        
    }

    @FXML
    private void addCategorieButton(ActionEvent event) {
        
        ServiceCategorie sercat = new  ServiceCategorie();
        Categorie c = new  Categorie();
        c.setNomCategorie(tfNom.getText());
        
        sercat.ajouterCategorie(c);
 }

    @FXML
    private void displayCategorieButton(ActionEvent event) {
        
        ServiceCategorie sercat = new  ServiceCategorie();
        
       LAfficher.setText(sercat.displayCategorie().toString());
        
    }

    @FXML
    private void deleteCategorieButton(ActionEvent event) {
         
        int id=0;
        ServiceCategorie sercat = new  ServiceCategorie();
        
         
            id=Integer.parseInt(tfIdSupp.getText());
           
           sercat.supprimmerCategorie(id);
        
    }
    
}
