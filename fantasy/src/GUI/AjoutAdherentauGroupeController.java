/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import testpi.newpackage.Adherent;
import testpi.newpackage.Groupe;
import testpi.newpackage.GroupeCRUD;
import testpi.newpackage.Mail;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AjoutAdherentauGroupeController implements Initializable {

    @FXML
    private Label lb;
    @FXML
    private TextField email;
    @FXML
    private Button btnajt;
    Groupe g1=new Groupe();
    Adherent u= new Adherent();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Adherent u1 = new Adherent(2,1,"Mahdi","Mahdi","mahdi.hssan@esprit.tn","Mahdi",10000,0); 
        btnajt.setOnAction((e)->{
            GroupeCRUD gcd = new GroupeCRUD();

            u=Adherent.byemail(email.getText());
            System.out.println(u.toString());
            gcd.ajouterAdherentauGroupe(g1, u);
            Mail mail = new Mail();
            mail.envoyer(u1, g1, email.getText());
        });
    }    
    
    public void fill (Groupe g){
        g1=g;
    }
}
