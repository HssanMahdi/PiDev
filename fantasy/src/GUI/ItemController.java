/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Produit;
import entites.Rate;
import GUI.Fantasy;
import interfaces.MyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import services.ServiceRate;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Rating rateProduit;
    
   int  idUser = 10;
   int idProod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {  
         myListener.onClickListener(p);
    }

 
    private Produit p;
    private MyListener myListener;

    public void setData(Produit p, MyListener myListener) {
        try {
            this.p = p;
           
            this.myListener = myListener;
            idProod= p.getIdProduit();
            nameLabel.setText(p.getNomProduit());
            priceLable.setText(Fantasy.CURRENCY + p.getPrixUnitaire());
            Image  image = new Image(new FileInputStream((p.getImage())));
            img.setImage(image);
              ServiceRate rr = new ServiceRate();
        
        
     
      double restoreRate =  rr.afficheRating(idUser, idProod); 
        System.out.println("==================================");
        
        System.out.println(restoreRate);
        
        System.out.println("========================");
      rateProduit.setRating(restoreRate);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RrateProductButton(MouseEvent event) {
        
          
        ServiceRate rr = new ServiceRate();
        
        Rate ra = new Rate();
   
        
        double rate = rateProduit.getRating();
     
                ra.setRate(rate);
        ra.setIdProduit(idProod);
      rr.addRating2(ra, idUser);
        System.out.println("normalemtn 5edmet");
        
        
        
    }
    
}
