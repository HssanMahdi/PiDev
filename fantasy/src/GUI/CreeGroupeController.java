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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entites.Adherent;
import entites.Groupe;
import services.GroupeCRUD;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class CreeGroupeController implements Initializable {
    FXMLLoginController log= new FXMLLoginController();
    Adherent u = log.user;
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
            if(ctrlsaisie()){
           
          Groupe g = new Groupe(nom.getText());
          gcd.creeGroupe(g, u);
          btnCree.getScene().getWindow().hide();
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
          try {
            loader.load();
          DisplaylesGroupedeAdherent fxmlcontroller = loader.getController();
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
          } catch (IOException ex) {
            System.out.println(ex);
        }}
     
          
    });    
    
}
    
   private boolean ctrlsaisie() {
        if (nom.getText().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme");
            alert.setHeaderText("Champ Vide");
            alert.setContentText("Ecrire le nom de groupe");
            alert.showAndWait();
            
            return false;

        }
        return true;
    }
    
}
