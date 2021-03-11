/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fantasy;

import entities.Panier;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class UserAfficherProduitFXMLController implements Initializable {


    @FXML
    private TableView<Produit> storeTableView;
    @FXML
    private TableColumn<Produit,ImageView> imageCol;
    @FXML
    private TableColumn<Produit, String> nomlCol;
    @FXML
    private TableColumn<Produit , Float> prixPrCol;
    @FXML
    private TableColumn<Produit, String> ajouterCol;
    
    Produit p;

    /**
     * Initializes the controller class.
     */
    
    
 
    
    
    
    
    @Override
   public void initialize(URL url, ResourceBundle rb) {
//       ServiceProduit serprod = new  ServiceProduit();
//     
//          List<Produit> L = new ArrayList();
//          
//                 L=serprod.displayProduit();
//                 ObservableList storeList = FXCollections.observableArrayList(L);
//              
//               
//             imageCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
//
//             nomlCol.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
//             prixPrCol.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
              
      loadData();
        
      
        
    }   
   
       private void loadData() {
        
       
        
           ServiceProduit serprod = new  ServiceProduit();
     
          List<Produit> L = new ArrayList();
          
                 L=serprod.displayProduit();
                 ObservableList storeList = FXCollections.observableArrayList(L);
              
               
             imageCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));

             nomlCol.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
             prixPrCol.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
              
       storeTableView.setItems(storeList);
        
        //add cell of button edit 
         Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button ajouterPanierButton = new Button(); 
                        ajouterPanierButton.setText("ajouter au panier");
                    

                           ajouterPanierButton.setOnMouseClicked((MouseEvent event) -> {
                      
                         
                      
                         
                          
                        
                         });
                     

                        HBox managebtn = new HBox(ajouterPanierButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(ajouterPanierButton, new Insets(2, 2, 0, 3));
                     

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         ajouterCol.setCellFactory(cellFoctory);
          storeTableView.setItems(storeList);
         
         
    }
    
    
    
}
