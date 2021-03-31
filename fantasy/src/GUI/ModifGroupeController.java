/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entites.Groupe;
import services.GroupeCRUD;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class ModifGroupeController implements Initializable {

    private Label lb;
    @FXML
    private TextField nom;
    @FXML
    private Button btnmodif;
    Groupe g1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnmodif.setOnAction((e)->{
        GroupeCRUD gcd= new GroupeCRUD();
        g1.setNom(nom.getText());
        gcd.updateGroupe(g1);
        FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
          try {
            loader.load();
            } catch (IOException ex) {
            System.out.println(ex);
        }
        DisplaylesGroupedeAdherent controller = loader.getController();
        controller.afficher();
        btnmodif.getScene().getWindow().hide();
        Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
        });
        
    }    
    public void fill(Groupe g){
     
        nom.setText(g.getNom());
        g1=g;
        }
}
