/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.jfoenix.controls.JFXTextField;
import entites.Produit;
import static GUI.Fantasy.xOffset;
import static GUI.Fantasy.yOffset;
import interfaces.MyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import services.ServicePanier;
import services.ServiceProduit;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class UserProductDisplayFXMLController implements Initializable {

    @FXML
    private VBox chosenProductCard;
 
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label productNameLable;
    @FXML
    private Label productPriceLabel;
    @FXML
    private ImageView ProductImg;
    @FXML
    private Text productDescriptionLable;
    @FXML
    private JFXTextField tfQuantitieDemander;

    
 
    private MyListener myListener;
    private MyListener myListenerCart;
    @FXML
    private Label fruitNameLable1;
      ObservableList panierList = FXCollections.observableArrayList();
      
      private static int idProduitPanier;
    @FXML
    private ImageView consulterCartButton;
     int activ_user_id =10; 
    @FXML
    private ComboBox<String> CatCom;
    @FXML
    private ComboBox<String> PriceCom;
    @FXML
    private TextField tfRecherche;
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillComboCategorie2();
        fillComboPrix();
         DisplayProduct();
//     DisplayProduct();
        
        
    }    
    
 public void DisplayProduct()
 {

           List<Produit>produits = new ArrayList<>();
   
   
     String triePrice = PriceCom.getSelectionModel().getSelectedItem();
     
if (triePrice == "prix croissante")//sorted((e1,e2)-> e1.getSalary()-e2.getSalary())
{
            produits = getData().stream().sorted((p1,p2)->(int)p1.getPrixUnitaire()-(int)p2.getPrixUnitaire()).collect(toList());
}

else if(triePrice =="prix decroissante")
{
          produits = getData().stream().sorted((p1,p2)->(int)p2.getPrixUnitaire()-(int)p1.getPrixUnitaire()).collect(toList());
}
else 
{
     produits = getData().stream().collect(toList());
}
                
                
                
        if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit p) {
                         
                    setChosenFruit(p);

                }
               
            };
        }
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 }
 
  public void DisplayProduct2()
 {

           List<Produit>produits = new ArrayList<>();
   
   
   
     produits.addAll(getData());

                
                
                
        
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("joueur.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                JoueurController joueurController = fxmlLoader.getController();
                joueurController.setData2(produits.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 }
 
  public void DisplayProductcombo()
  {
      List<Produit>produits = new ArrayList<>();
   
     String nom_cat = CatCom.getSelectionModel().getSelectedItem();
     String triePrice = PriceCom.getSelectionModel().getSelectedItem();
     
if (triePrice == "prix croissante")//sorted((e1,e2)-> e1.getSalary()-e2.getSalary())
{
            produits = getData().stream().filter(p->p.getNomCategorie().equalsIgnoreCase(nom_cat)).sorted((p1,p2)->(int)p1.getPrixUnitaire()-(int)p2.getPrixUnitaire()).collect(toList());
}

else if(triePrice == "prix decroissante")
{
          produits = getData().stream().filter(p->p.getNomCategorie().equalsIgnoreCase(nom_cat)).sorted((p1,p2)->(int)p2.getPrixUnitaire()-(int)p1.getPrixUnitaire()).collect(toList());
}
else 
{
     produits = getData().stream().filter(p->p.getNomCategorie().equalsIgnoreCase(nom_cat)).collect(toList());
}    
       
       
     
     
      
         if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit p) {
                         
                    setChosenFruit(p);

                }
               
            };
        }
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fantasyligue1/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  }

    private List<Produit> getData() {
    
    ServiceProduit serprod = new  ServiceProduit();
     
          List<Produit> L = new ArrayList();
          
                 L=serprod.displayProduit3();
                 
                 return L;
       
    }

    private void setChosenFruit(Produit p) {
        try {
            productNameLable.setText(p.getNomProduit());
            productPriceLabel.setText(Fantasy.CURRENCY + p.getPrixUnitaire());
            Image  image = new Image(new FileInputStream((p.getImage())));
            productDescriptionLable.setText(p.getDescription());
            ProductImg.setImage(image);
            idProduitPanier=p.getIdProduit();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserProductDisplayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void addToCartButton(ActionEvent event) {
          
        System.out.println(idProduitPanier);
        ServicePanier s = new ServicePanier(); 
         int q = Integer.parseInt( tfQuantitieDemander.getText());
        s.addProduitPanier(idProduitPanier,q,activ_user_id );
        
            Notifications notifictaionBuilder = Notifications.create()
                    .title("Cart")
                    .text("add to cart succefully")
                    .graphic(null)
                    .position(Pos.TOP_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
        
           notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
        
    }

    @FXML
    private void ShowCartButton(MouseEvent event) throws IOException {
        
       

  
          Parent home_page_parent = FXMLLoader.load(getClass().getResource("Cart.fxml"));
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
    
       public void fillComboCategorie2() {
           
            ObservableList list = FXCollections.observableArrayList();
             list.add("all category");
        try {
            String requete = "SELECT nom_categorie FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               
               
                list.add(rs.getString("nom_categorie"));
             
               CatCom.setItems(list);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
              public void fillComboPrix()
              {
                      ObservableList list = FXCollections.observableArrayList();
                     list.addAll("prix croissante","prix decroissante");
                     PriceCom.setItems(list);
              }

    @FXML
    private void DisplayProductWithCombo(ActionEvent event) {
        
       grid.setGridLinesVisible(false);
       grid.getColumnConstraints().clear();
       grid.getRowConstraints().clear();
       grid.getChildren().clear();
       
        
        if(CatCom.getSelectionModel().getSelectedItem()!="all category")
        {
        DisplayProductcombo();
        System.out.println("trass khw");
        }
        else if(CatCom.getSelectionModel().getSelectedItem()=="all category")
        {
            DisplayProduct();
            System.out.println("trass all category");
        }
    }

    @FXML
    private void DisplayProductWithComboPrix(ActionEvent event) {
        
          grid.setGridLinesVisible(false);
       grid.getColumnConstraints().clear();
       grid.getRowConstraints().clear();
       grid.getChildren().clear();
       
        
        if(CatCom.getSelectionModel().getSelectedItem()==null)
        {
            DisplayProduct();//prix khw
          
        System.out.println("trass prix khw ");
        }
        else if(CatCom.getSelectionModel().getSelectedItem()=="all category")
        {
            DisplayProduct();
            System.out.println("trass all category prix");
        }
        else 
        {
                 DisplayProductcombo();
                 System.out.println("fok alya");
        }
    }

    @FXML
    private void recherchce(ActionEvent event) {
        
              grid.setGridLinesVisible(false);
       grid.getColumnConstraints().clear();
       grid.getRowConstraints().clear();
       grid.getChildren().clear();
        DisplayProductRecherche();
    }
    
        
 public void DisplayProductRecherche()//mtaa recherche
 {

           List<Produit>produits = new ArrayList<>();
   
   
    
     

     produits = getData().stream().filter(p->p.getNomProduit().equals( tfRecherche.getText())).collect(toList());        //collect(toList());

                
                
                
        if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit p) {
                         
                    setChosenFruit(p);

                }
               
            };
        }
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fantasyligue1/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 }
 
    
}
