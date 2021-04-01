/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Commande;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class PasserCommandeController implements Initializable {

    @FXML
    private TableColumn<Commande, Integer> idCommande;
    @FXML
    private TableColumn<Commande, String> AdresseLaivriason;
    @FXML
    private TableColumn<Commande, String> Country;
    @FXML
    private TableColumn<Commande, Integer> postCode;
    @FXML
    private TableColumn<Commande, String> DateCommande;
    @FXML
    private TableColumn<Commande, Double> totalePriceCommande;
    @FXML
    private TableColumn<Commande, String> StatusCommande;
    @FXML
    private TableColumn<Commande, String> productListCommande;
    @FXML
    private TableView<Commande> orderTable;

       ObservableList<Commande> orders_historylist_commande = FXCollections.observableArrayList();
   
     ServiceCommande hoo = new  ServiceCommande();
     int activ_user_id=10;
     
      public static boolean confirmedCommande = false;
      public static int idCommandeModif = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        create_ordersHistory_Table();
       
    }    
    
        public void create_ordersHistory_Table() {

        
 
//  cart_table.setItems(cartlist);
       
    addButtonToTableCartDeleteUpdate( activ_user_id);
          orders_historylist_commande = hoo.getInitialTableData_Orders_History(activ_user_id);
      idCommande.setCellValueFactory(new PropertyValueFactory<>("idCmd"));
        AdresseLaivriason.setCellValueFactory(new PropertyValueFactory<>("adrLivraison"));
        Country.setCellValueFactory(new PropertyValueFactory<>("country"));
        postCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        DateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
       totalePriceCommande.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
         StatusCommande.setCellValueFactory(new PropertyValueFactory<>("status"));
        productListCommande.setCellValueFactory(new PropertyValueFactory<>("listeProduit"));
  
     orderTable.setItems(orders_historylist_commande);
        
        
        }
     public void refrechTableorders(int activ_user_id) {
        ServiceCommande h= new  ServiceCommande();
        orders_historylist_commande.clear();
       orders_historylist_commande = hoo.getInitialTableData_Orders_History(activ_user_id);
        orderTable.setItems(orders_historylist_commande);
        
        

    }
     
         private void addButtonToTableCartDeleteUpdate(int activ_user_id) {
        TableColumn<Commande, Void> colBtn1 = new TableColumn("cancel Commande");
        ServiceCommande sc = new ServiceCommande();
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
//
//                    private final Button btn1 = new Button();
//
//                    {
//                        ImageView btnimg = new ImageView("/img/icon-delete.png");
//                        btnimg.setFitWidth(30);
//                        btnimg.setFitHeight(30);
//                        btn1.setGraphic(btnimg);
//
//                        btn1.setOnAction((ActionEvent event) -> {
//                          Commande data = getTableView().getItems().get(getIndex());
//                
//                          sc.delete_order(data.getIdCmd(), activ_user_id);
//                          refrechTableorders(activ_user_id);
//                        
//                          
////                            sc.delete_product_from_panier(data.getIdProduit(), activ_user_id);
////                            refrechTableCart(activ_user_id);
////                            Double t=0.0;
////                            refrech_tot_price(t, activ_user_id);
//                        });
//                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                         FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:40px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:40px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                               Commande data = getTableView().getItems().get(getIndex());
                
                          sc.delete_order(data.getIdCmd(), activ_user_id);
                          refrechTableorders(activ_user_id);
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                   
                              Commande data = getTableView().getItems().get(getIndex());
                            idCommandeModif= data.getIdCmd();  
                            try {
                                 Parent parent = FXMLLoader.load(getClass().getResource("UpdateCommande.fxml"));
                                 Scene scene = new Scene(parent);
                                 Stage popup = new Stage();
                                 
                                 popup.setScene(scene);
                                 popup.setTitle("modifier Commande");
                     
                                  scene.setFill(Color.TRANSPARENT);
                                 popup.showAndWait();
                                    if (confirmedCommande == true) {
                                    popup.close();
                                    System.out.println("fy wolst l if te3 confirmed = true");
                                  refrechTableorders(activ_user_id);
                                } else {
                                    System.out.println("Exit");
                                }
                             } catch (IOException ex) {
                                 Logger.getLogger(PasserCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             
                               refrechTableorders(activ_user_id);

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn1.setCellFactory(cellFactory);

       orderTable.getColumns().add(colBtn1);

    }


     
}
