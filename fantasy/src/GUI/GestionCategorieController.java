/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Categorie;
import static GUI.Fantasy.xOffset;
import static GUI.Fantasy.yOffset;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class GestionCategorieController implements Initializable {

    @FXML
    private TableColumn<Categorie,Integer> IDColum;
    @FXML
    private TableColumn<Categorie,String> NomCatrgorieColu;
    @FXML
    private JFXTextField tfNomCategorie;
    @FXML
    private Pane navicon;
    @FXML
    private TableView<Categorie> CatTable;
    private int idModif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButtonToTableCartDeleteUpdate();
        displayCategorieButton();
        
    }  
    
         private void addButtonToTableCartDeleteUpdate() {
        TableColumn<Categorie, Void> colBtn1 = new TableColumn("cancel Commande");
        ServiceCategorie sc = new ServiceCategorie();
        Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>> cellFactory = new Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>>() {
            @Override
            public TableCell<Categorie, Void> call(final TableColumn<Categorie, Void> param) {
                final TableCell<Categorie, Void> cell = new TableCell<Categorie, Void>() {
//
//           

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

                               Categorie data = getTableView().getItems().get(getIndex());
                               int idSupp= data.getIdCategorie();
                               sc.supprimmerCategorie(idSupp);
                                 displayCategorieButton();
                          
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             Categorie data = getTableView().getItems().get(getIndex());
                             idModif=data.getIdCategorie();
                             tfNomCategorie.setText(data.getNomCategorie());
                                              
                                    
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

      CatTable.getColumns().add(colBtn1);

    }

    @FXML
    private void navicon(MouseEvent event) {
    }

    @FXML
    private void addCategorieButton(ActionEvent event) {
        
              ServiceCategorie sercat = new  ServiceCategorie();
        Categorie c = new  Categorie();
        c.setNomCategorie(tfNomCategorie.getText());
        
        sercat.ajouterCategorie(c);
    }
    
     private void displayCategorieButton() {
        
        ServiceCategorie serprod = new  ServiceCategorie();
     
          List<Categorie> L = new ArrayList();
          
                 L=serprod.displayCategorie();
                 ObservableList viewlist = FXCollections.observableArrayList(L);
              
            
              IDColum.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
            
             NomCatrgorieColu.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

          
         CatTable.setItems(viewlist);
        
    }

    @FXML
    private void SaveModificationButton(ActionEvent event) {
        
              ServiceCategorie sercat = new  ServiceCategorie();
        Categorie c = new  Categorie();
        c.setNomCategorie(tfNomCategorie.getText());
        
        sercat.modifierCategorie(c, idModif);
        displayCategorieButton();
        tfNomCategorie.clear();
        
    }

    @FXML
    private void BackToProduct(ActionEvent event) throws IOException {
        
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
}
