/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class AfficherlesGroupedeAdherentControllerController implements Initializable {

    @FXML
    private TableView<Groupe> tab;
    @FXML
    private TableColumn<Groupe, String> nomdegroupe;
    @FXML
    private Button btnaf;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnact;
    @FXML
    private Button btnquit;
    Groupe g=new Groupe();
    @FXML
    private Button btnsup;
    @FXML
    private Label lbusr;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GroupeCRUD gcd = new GroupeCRUD();
        Adherent u = new Adherent(2,1,"Mahdi","Mahdi","mahdi.hssan@esprit.tn","Mahdi",10000,0); 
        lbusr.setText("User name "+u.getNom_user()+"\n Score "+u.getScore()+"\n Solde "+u.getSolde());
        nomdegroupe.setCellValueFactory(new PropertyValueFactory<Groupe, String>("nom"));
        ObservableList<Groupe> res;
        res = FXCollections.observableArrayList(gcd.displayGrouppourAdherent(u));
        tab.setItems(res);
        
        btnaf.setOnAction((e)->{
           g= tab.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("AfficherlesAdherentdeGroupe.fxml"));
          try {
            loader.load();
            } catch (IOException ex) {
            System.out.println(ex);
        }
          
          AfficherlesAdherentdeGroupeController controller = loader.getController();
          controller.filltable(gcd.displayAdherentdeGroupe(g));
          btnaf.getScene().getWindow().hide();
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
          
        
        });
        
        btnact.setOnAction((e)->{
            initialize(url, rb);
        });
        
        btnajout.setOnAction((e)->{
            g= tab.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("AjoutAdherentauGroupe.fxml"));
          try {
            loader.load();
            } catch (IOException ex) {
            System.out.println(ex);
        }
          AjoutAdherentauGroupeController controller = loader.getController();
          controller.fill(g);
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
        });
        
        btnmodif.setOnAction((e)->{
         g= tab.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("ModifGroupe.fxml"));
          try {
            loader.load();
            } catch (IOException ex) {
            System.out.println(ex);
        }
          ModifGroupeController controller = loader.getController();
          controller.fill(g);
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.show();
        });

        btnquit.setOnAction((e)->{
             g= tab.getSelectionModel().getSelectedItem();
             gcd.supprimerAdherentduGroupe(g, u);
             initialize(url, rb);
                });
        btnsup.setOnAction((e)->{
             g= tab.getSelectionModel().getSelectedItem();
             gcd.supprimerGroupe(g);
             initialize(url, rb);
                });
    }    
    
}
