/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Commande;
import static GUI.Fantasy.xOffset;
import static GUI.Fantasy.yOffset;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class AdminCommandeController implements Initializable {

    @FXML
    private TableColumn<Commande, Integer> idCommandeAdmin;
    @FXML
    private TableColumn<Commande, String> AdresseLaivriasonAdmin;
    @FXML
    private TableColumn<Commande, String> CountryAdmin;
    @FXML
    private TableColumn<Commande, Integer> postCodeAmin;
    @FXML
    private TableColumn<Commande, String> DateCommandeAdmin;
    @FXML
    private TableColumn<Commande, Double> totalePriceCommandeAdmin;
    @FXML
    private TableColumn<Commande, String> StatusCommandeAdmin;
    @FXML
    private TableColumn<Commande, String> productListCommandeAdmin;
    @FXML
    private TableView<Commande> orderTableAdmin;
    @FXML
    private TableColumn<Commande, Integer> IdUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        displayProduct();
        
    }    

    @FXML
    private void returnPageProduit(ActionEvent event) throws IOException {
        
          Parent home_page_parent = FXMLLoader.load(getClass().getResource("home.fxml"));
       Scene home_page_scene = new Scene (home_page_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
      
          ///////////////////////////////////////////////////////////////

     

        app_stage.setTitle("Hello World!");
    

         
        home_page_scene.setFill(Color.TRANSPARENT);

         app_stage.show();
         home_page_parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
       home_page_parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               app_stage.setX(event.getScreenX() - xOffset);
               app_stage.setY(event.getScreenY() - yOffset);
            }
        });
        
    }
    
    public void displayProduct()
    {
         ObservableList<Commande> ListCommandeAdmin = FXCollections.observableArrayList();
          ServiceCommande hoo = new  ServiceCommande();
           ListCommandeAdmin = hoo.getInitialTableData_Orders_History_For_Admin();
      idCommandeAdmin.setCellValueFactory(new PropertyValueFactory<>("idCmd"));
         IdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
      
        AdresseLaivriasonAdmin.setCellValueFactory(new PropertyValueFactory<>("adrLivraison"));
        CountryAdmin.setCellValueFactory(new PropertyValueFactory<>("country"));
       postCodeAmin.setCellValueFactory(new PropertyValueFactory<>("postCode"));
         DateCommandeAdmin.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
       totalePriceCommandeAdmin.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
         StatusCommandeAdmin.setCellValueFactory(new PropertyValueFactory<>("status"));
        productListCommandeAdmin.setCellValueFactory(new PropertyValueFactory<>("listeProduit"));
  
     orderTableAdmin.setItems(ListCommandeAdmin);
    }
    
}
