/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entites.Produit;
import interfaces.MyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class JoueurController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label positionJoueur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
    }
    
      private Produit p;
  

    public void setData2(Produit p) {
        try {
            this.p = p;
        
            nameLabel.setText(p.getNomProduit());
            priceLable.setText(Fantasy.CURRENCY + p.getPrixUnitaire());
            Image  image = new Image(new FileInputStream((p.getImage())));
            img.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buyButton(ActionEvent event) {
        System.out.println("achter joueur");
        
        
        
        
    }
    
}
