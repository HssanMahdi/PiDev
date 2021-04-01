/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import entites.Commande;
import entites.Produit;
import static GUI.Fantasy.xOffset;
import static GUI.Fantasy.yOffset;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import services.ServiceCommande;
import services.ServicePanier;
import tools.Mail;


/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class CartController implements Initializable {

    @FXML
    private TableView<Produit> cart_table;
    @FXML
    private TableColumn<Produit, String> product_name_cart;
    @FXML
    private TableColumn<Produit, ImageView> product_image_cart;
    @FXML
    private TableColumn<Produit, String> price_cart;
    @FXML
    private TableColumn<Produit, String> quantity_cart;
    @FXML
    private Label tot_price;
    
  
 
    ObservableList<Commande> orders_historylist = FXCollections.observableArrayList();
   
     ServiceCommande ho = new  ServiceCommande();
      ServicePanier s = new ServicePanier();
   static ObservableList<Produit> cartlist = FXCollections.observableArrayList();
   
    int activ_user_id =10; 
    @FXML
    private JFXButton checkout_butt;
    @FXML
    private JFXButton orders_history_button;
    @FXML
    private JFXButton Clear_Cart;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
       
   
    //cart table affich
   
        product_name_cart.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        product_image_cart.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
        price_cart.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        quantity_cart.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ServicePanier sc = new ServicePanier();
        Double total_price=sc.total_price_calcul(activ_user_id);
        tot_price.setText(String.valueOf(total_price));
       cartlist = sc.getInitialTableData_Cart(activ_user_id); //get liste cart of user id 10 replace with activ user
        cart_table.setItems(cartlist);
        
        addButtonToTableCartQuantityMinus(activ_user_id);
        addButtonToTableCartQuantity(activ_user_id);
     addButtonToTableCartDelete(activ_user_id);

        ImageView clearimg = new ImageView("/img/icon-delete.png");
        clearimg.setFitWidth(30);
        clearimg.setFitHeight(30);
        Clear_Cart.setGraphic(clearimg);
        Clear_Cart.setOnAction((ActionEvent event) -> {
            sc.vider_panier(activ_user_id);
            refrechTableCart(activ_user_id);
            Double t=0.0;
                            refrech_tot_price(t, activ_user_id);
            Notifications notifictaionBuilder = Notifications.create()
                    .title("Cart")
                    .text("Cart cleared")
                    .graphic(null)
                    .position(Pos.TOP_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
        });
        
    checkout_butt.setOnAction((ActionEvent event) -> {
           double pricet=sc.total_price_calcul(activ_user_id);
           tot_price.setText(String.valueOf(pricet));
            create_checkout_page(activ_user_id,pricet);
        });

        orders_history_button.setOnAction((ActionEvent event) -> {
           refrechTableorders(activ_user_id);
            try {
                
                   Parent parent = FXMLLoader.load(getClass().getResource("PasserCommande.fxml"));
                                Stage popup = new Stage();
                                Scene scene = new Scene(parent);
                                popup.setScene(scene);
                                popup.showAndWait();
                
//                Parent home_page_parent = FXMLLoader.load(getClass().getResource("PasserCommande.fxml"));
//                Scene home_page_scene = new Scene (home_page_parent);
//                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                app_stage.setScene(home_page_scene);
//                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        orders_historylist = ho.getInitialTableData_Orders_History(activ_user_id);


     

   
        
        
    }    
    
    private void addButtonToTableCartDelete(int activ_user_id) {
        TableColumn<Produit, Void> colBtn1 = new TableColumn("Delete From Cart");
        ServicePanier sc = new ServicePanier();
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn1 = new Button();

                    {
                        ImageView btnimg = new ImageView("/img/icon-delete.png");
                        btnimg.setFitWidth(30);
                        btnimg.setFitHeight(30);
                        btn1.setGraphic(btnimg);

                        btn1.setOnAction((ActionEvent event) -> {
                           Produit data = getTableView().getItems().get(getIndex());

                            sc.delete_product_from_panier(data.getIdProduit(), activ_user_id);
                            refrechTableCart(activ_user_id);
                            Double t=0.0;
                            refrech_tot_price(t, activ_user_id);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn1);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn1.setCellFactory(cellFactory);

        cart_table.getColumns().add(colBtn1);

    }


private void addButtonToTableCartQuantity(int activ_user_id) {
        TableColumn<Produit, Void> colBtn1 = new TableColumn("+");
       ServicePanier sc = new ServicePanier();
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btnplus = new Button();

                    {
                        ImageView btnimg = new ImageView("/img/buttplus.png");
                        btnimg.setFitWidth(30);
                        btnimg.setFitHeight(30);
                        btnplus.setGraphic(btnimg);

                        btnplus.setOnAction((ActionEvent event) -> {
                            Produit data = getTableView().getItems().get(getIndex());

                            sc.update_panier_product_quantity(activ_user_id, data.getIdProduit(), data.getQuantite() + 1);
                            refrechTableCart(activ_user_id);
                            Double t=0.0;
                            refrech_tot_price(t, activ_user_id);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnplus);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn1.setCellFactory(cellFactory);

        cart_table.getColumns().add(colBtn1);

    }

    private void addButtonToTableCartQuantityMinus(int activ_user_id) {
        TableColumn<Produit, Void> colBtn1 = new TableColumn("-");
       ServicePanier sc = new ServicePanier();
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btnplus = new Button();

                    {
                        ImageView btnimg = new ImageView("/img/btnminus.png");
                        btnimg.setFitWidth(30);
                        btnimg.setFitHeight(30);
                        btnplus.setGraphic(btnimg);

                        btnplus.setOnAction((ActionEvent event) -> {
                            Produit data = getTableView().getItems().get(getIndex());
                            if (data.getQuantite() != 1) {
                               sc.update_panier_product_quantity(activ_user_id, data.getIdProduit(), data.getQuantite() - 1);
                            }
                            refrechTableCart(activ_user_id);
                            Double t=0.0;
                            refrech_tot_price(t, activ_user_id);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnplus);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn1.setCellFactory(cellFactory);

        cart_table.getColumns().add(colBtn1);

    }

     void refrechTableCart(int activ_user_id) {
        ServicePanier sc = new ServicePanier();
        cartlist.clear();
        cartlist = sc.getInitialTableData_Cart(activ_user_id);
        cart_table.setItems(cartlist);

    }
    
    
    




    


    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
        
    }
    
    public void refrech_tot_price(double total_price,int activ_user_id)
    {
        ServicePanier cc=new ServicePanier();
        total_price=cc.total_price_calcul(activ_user_id);
        
        tot_price.setText(String.valueOf(total_price));
    }

    @FXML
    private void checkout_butt(ActionEvent event) {
    }

    @FXML
    private void orders_history_button(ActionEvent event) {
    }
    
    public void refrechTableorders(int activ_user_id) {
        ServiceCommande h= new  ServiceCommande();
        orders_historylist.clear();
        orders_historylist = ho.getInitialTableData_Orders_History(activ_user_id);
        

    }
    
    ////////////////////////////////////////////////////////////////////
    public void create_ordersHistory_Table() {

        Stage stage = new Stage();
       AnchorPane root = new AnchorPane();
 root.setStyle("-fx-background-image: url('ordersbg.png');");
        Scene scene = new Scene(root, 1420, 620);
        TableView tableView = new TableView();

        TableColumn<Commande, String> Column1 = new TableColumn<>("order_id");
        Column1.setMinWidth(240);

        TableColumn<Commande, String> Column2 = new TableColumn<>("delivery address");
        Column2.setMinWidth(240);

        TableColumn<Commande, String> Column3 = new TableColumn<>("Country");
        Column3.setMinWidth(150);

        TableColumn<Commande, Date> Column4 = new TableColumn<>("Date");
        Column4.setMinWidth(240);

        TableColumn<Commande, Double> Column5 = new TableColumn<>("Total Price");
        Column5.setMinWidth(150);

        TableColumn<Commande, String> Column6 = new TableColumn<>("Status");
        Column6.setMinWidth(150);

        TableColumn<Commande, String> Column7 = new TableColumn<>("Order Products");
        Column7.setMinWidth(150);
        
        tableView.getColumns().add(Column1);
        tableView.getColumns().add(Column2);
        tableView.getColumns().add(Column3);
        tableView.getColumns().add(Column4);
        tableView.getColumns().add(Column5);
        tableView.getColumns().add(Column6);
        tableView.getColumns().add(Column7);

        Column1.setCellValueFactory(new PropertyValueFactory<>("idCmd"));
        Column2.setCellValueFactory(new PropertyValueFactory<>("adrLivraison"));
        Column3.setCellValueFactory(new PropertyValueFactory<>("country"));
        Column4.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        Column5.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        Column6.setCellValueFactory(new PropertyValueFactory<>("status"));
        Column7.setCellValueFactory(new PropertyValueFactory<>("listeProduit"));
         //multiline table cell
         Callback<TableColumn<Commande,String>, TableCell<Commande,String>> cellFactory7 = new Callback<TableColumn<Commande,String>, TableCell<Commande,String>>() {
			@Override
			public TableCell call( TableColumn param) {
				final TableCell cell = new TableCell() {
					private Text label;
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							label = new Text(item.toString());
							label.setWrappingWidth(140);
							setGraphic(label);
						}
					}
				};
				return cell;
			}
		};
         //multiline end
         
         Column1.setCellFactory(cellFactory7);
         Column7.setCellFactory(cellFactory7);
        tableView.setItems(orders_historylist);
        tableView.setMaxHeight(350);
        Button printbButton =new Button("");
        printbButton.setBackground(Background.EMPTY);
        printbButton.setPrefWidth(50);
       ImageView print=new ImageView("/img/buttplus.png");
       print.setFitWidth(50);
       print.setFitHeight(50);
       printbButton.setGraphic(print);
    root.getChildren().add(printbButton);
     printbButton.setLayoutX(1300);
     printbButton.setLayoutY(60);
     printbButton.setOnMouseEntered((MouseEvent me) -> {
                           printbButton.setCursor(Cursor.HAND);
                        });
     ServiceCommande ser=new ServiceCommande();
     printbButton.setOnAction(e->{
            try {
//                ser.pdfs(activ_user_id);
            } catch (Exception ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        root.getChildren().add(tableView);
        tableView.setLayoutY(140);
        tableView.setLayoutX(50);
      
        stage.setTitle("orders history");
        stage.setScene(scene);
        stage.show();

    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    public void create_checkout_page(int activ_user_id,double total_price) {
        Stage stage = new Stage();
        stage.setTitle("Checkout");
        StackPane root = new StackPane();
         GridPane gridPane = createRegistrationFormPane();
         addUIControls(gridPane,activ_user_id,total_price);
          Scene scene = new Scene(gridPane, 800, 500);
          stage.setScene(scene);
          stage.show();
    }
    
    public void addUIControls(GridPane gridPane,int activ_user_id,Double total_price) {
    // Add Header
    Label headerLabel = new Label("passer Commande");
    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    gridPane.add(headerLabel, 0,0,2,1);
    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

    // Add delivery Label
    Label nameLabel = new Label("Adresse laivraison : ");
    gridPane.add(nameLabel, 0,1);

    // Add Name Text Field
    TextField nameField = new TextField();
    nameField.setPrefHeight(40);
    gridPane.add(nameField, 1,1);


    // Add country Label
    Label emailLabel = new Label("Ville : ");
    gridPane.add(emailLabel, 0, 2);

    // Add country Text Field
    TextField emailField = new TextField();
    emailField.setPrefHeight(40);
    gridPane.add(emailField, 1, 2);

 // Add country Label
    Label postcode = new Label("code postale : ");
    gridPane.add(postcode, 0, 3);

    // Add country Text Field
    TextField postcodeField = new TextField();
    emailField.setPrefHeight(40);
    gridPane.add(postcodeField, 1, 3);   

    

    // Add Submit Button
    Button submitButton = new Button("Confirm Order");
    submitButton.setPrefHeight(40);
    submitButton.setDefaultButton(true);
    submitButton.setPrefWidth(150);
    gridPane.add(submitButton, 0, 4, 2, 1);
    GridPane.setHalignment(submitButton, HPos.CENTER);
    GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your adress");
                    
                } 
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your country");
                    
                } 
                if(postcodeField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
                    
                }
                String adress1 =nameField.getText(); 
                String country1=emailField.getText(); 
                int postcode1=Integer.parseInt(postcodeField.getText());
               ServicePanier ss=new ServicePanier();
                double pr=ss.total_price_calcul(activ_user_id);
                ServiceCommande so=new ServiceCommande(); 
                so.add_order(activ_user_id,adress1 , country1, postcode1,pr);
                refrechTableCart(activ_user_id);
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Order Confirmed!", "your order is being prepared" );
                nameField.clear();
                emailField.clear();
                postcodeField.clear();
                Notifications notifictaionBuilder = Notifications.create()
                    .title(" Confirmation")
                    .text("Order Confirmed")
                    .graphic(null)
                    .position(Pos.TOP_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
         Mail mm = new Mail();
         Date date;
        date = new Date();
       String dd= date.toString();
         mm.envoyerCommande(activ_user_id, dd);
            }     
                 
        });
    }

    @FXML
    private void returnToStore(ActionEvent event) throws IOException {
        
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("UserProductDisplayFXML.fxml"));
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

  
    
}
