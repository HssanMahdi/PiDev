/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.RatingJoueur;
import services.ServiceRating;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RatingJRController implements Initializable {

    @FXML
    private Button validBtn;
    RatingJoueur rj = new RatingJoueur();
    ServiceRating sr = new ServiceRating();
    @FXML
    private AnchorPane main;
    @FXML
    private Pane panRat;
    int idRating;
    int joueurID;
    int userID;
    double valueRat;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    Rating rat = new Rating(5);
    RatingJoueur rt = new RatingJoueur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void afficher(double rj) {

        rat.setRating(rj);
        rat.setLayoutX(60);
        rat.setLayoutY(14);
        panRat.getChildren().add(rat);
    }

    @FXML
    private void Valid(ActionEvent event) {
        if(sr.UserExist(joueurID, userID)){
            rt.setId_joueur(joueurID);
        rt.setRatingValue(rat.getRating());
        rt.setId_joueur(joueurID);
        rt.setId_user(userID);
        sr.updateRating(rt);
        }
      

    }

    public void getDetails(int id, double value, int idJoueur, int idUser) {
        idRating = id;

        valueRat = value;

        userID = idUser;

        joueurID = idJoueur;

    }

}
