/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fantasy;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class FXMLDocumentController implements Initializable {
    
    
    
 
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
   }    

    @FXML
    private void userEspaceButton(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("UserAfficherProduitFXML.fxml"));
       Scene home_page_scene = new Scene (home_page_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
        
    }

    @FXML
    private void adminEspaceButton(ActionEvent event) throws IOException {
        
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("GestionFXML.fxml"));
       Scene home_page_scene = new Scene (home_page_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
       
  }
    
}
