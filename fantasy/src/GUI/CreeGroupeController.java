/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import testpi.newpackage.Adherent;
import testpi.newpackage.Groupe;
import testpi.newpackage.GroupeCRUD;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class CreeGroupeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button btnCree;
    GroupeCRUD gcd = new GroupeCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCree.setOnAction((e)->{
           Adherent u = new Adherent(2,5,"Mahdi","Mahdi","mahdi.hssan@esprit.tn","Mahdi",10000,0);
          Groupe g = new Groupe(nom.getText());
          gcd.creeGroupe(g, u);
          btnCree.getScene().getWindow().hide();
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("AfficherlesGroupedeAdherentController.fxml"));
          try {
            loader.load();
          AfficherlesGroupedeAdherentControllerController fxmlcontroller = loader.getController();
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
          } catch (IOException ex) {
            System.out.println(ex);
        }
          
    });    
    
}
    
}
