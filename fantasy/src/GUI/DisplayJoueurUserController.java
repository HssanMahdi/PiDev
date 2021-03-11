/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Services.ServiceEquipe;
import Services.ServiceJoueur;
import entities.Joueur;
import java.io.IOException;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DisplayJoueurUserController implements Initializable {

     @FXML
    private TableView<Joueur> tabAffiche;

    @FXML
    private TableColumn<Joueur, String> nom;
    @FXML
    private TableColumn<Joueur, String> prenom;
    @FXML
    private TableColumn<Joueur, String> position;
    @FXML
    private TableColumn<Joueur, Integer> score;
    @FXML
    private TableColumn<Joueur, ImageView> logo;
    @FXML
    private TableColumn<Joueur, Integer> prix;



    ServiceEquipe SE = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    @FXML
    private Button btnBack;


    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   

    }

    public void loadData(List<Joueur> j) {
        ServiceJoueur SJ = new ServiceJoueur();
        nom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomJoueur"));
        prenom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenomJoueur"));
        position.setCellValueFactory(new PropertyValueFactory<Joueur, String>("position"));
     

        logo.setCellValueFactory(new PropertyValueFactory<Joueur, ImageView>("logoimage"));

        prix.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("prixJoueur"));
 

        ObservableList<Joueur> res;

        res = FXCollections.observableArrayList(j);
        tabAffiche.setItems(res);

    }


 


    @FXML
    private void Back(ActionEvent event) {
       
        try {
            btnBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEquipeUser.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }





}
