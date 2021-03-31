/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import entites.Adherent;
import entites.Groupe;
import services.GroupeCRUD;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class QuitdefController implements Initializable {
    Adherent u = new Adherent(2, 1, "Mahdi", "Mahdi", "mahdi.hssan@esprit.tn", "Mahdi", 10000, 0);
    Groupe g = new Groupe();
    @FXML
    private Button btnoui;

    @FXML
    private Button btnnon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnnon.setOnAction((e) -> {
        btnnon.getScene().getWindow().hide();
        });
        btnoui.setOnAction((e) -> {
            GroupeCRUD gcd=new GroupeCRUD();
            gcd.quitGroupe(g, u);
            btnoui.getScene().getWindow().hide();
        });
        
    }    

    public void fill(Groupe a){
    g=a;
    }

}
