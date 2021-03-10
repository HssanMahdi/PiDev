/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import testpi.newpackage.Adherent;
import testpi.newpackage.Groupe;
import testpi.newpackage.GroupeCRUD;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AfficherlesAdherentdeGroupeController implements Initializable {

    @FXML
    private TableView<Adherent> Tab;
    @FXML
    private TableColumn<Adherent, String> nom;
    @FXML
    private TableColumn<Adherent, String> score;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    public void filltable(List<Adherent> l){
    
        nom.setCellValueFactory(new PropertyValueFactory<Adherent, String>("nom_user"));
        score.setCellValueFactory(new PropertyValueFactory<Adherent, String>("score_user"));
        ObservableList<Adherent> res;
        res = FXCollections.observableArrayList(l);
        Tab.setItems(res);
        btnback.setOnAction((e)->{ 
        FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("AfficherlesGroupedeAdherentController.fxml"));
          try {
            loader.load();
            } catch (IOException ex) {
            System.out.println(ex);
        }
          
          btnback.getScene().getWindow().hide();
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
        });
    
    };
}
