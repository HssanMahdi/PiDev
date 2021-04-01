/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Produit;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class HomeController implements Initializable {

    private Pane sidebar;
    @FXML
    private Pane navicon;

    @FXML
    private TableView<Produit> tableProduit;
    @FXML
    private TableColumn<Produit, Integer> CoIdProduit;
    @FXML
    private TableColumn<Produit, Integer> CoIdCategorie;
    @FXML
    private TableColumn<Produit, String> CoNomProduit;
    @FXML
    private TableColumn<Produit, String> CoNomCategorie;
    @FXML
    private TableColumn<Produit, Integer> CoQuantite;
    @FXML
    private TableColumn<Produit, Float> CoPrixUnitaire;
    @FXML
    private TableColumn<Produit, ImageView> CoImage;
    @FXML
    private TableColumn<Produit, String> CoIAction;

    @FXML
    private TableColumn<Produit, String> CoIDescriptopn;

    public static boolean confirmed = false;
    public static int idProduit = 0;
    public static String ActionType = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
//        displayProduct();
        loadDate();

    }

    private void sidebar(MouseEvent event) {
       
    }

    @FXML
    private void navicon(MouseEvent event) {
        sidebar.setVisible(true);

    }

    public void displayProduct() {
        ServiceProduit serprod = new ServiceProduit();

        List<Produit> L = new ArrayList();

        L = serprod.displayProduit();
        ObservableList viewlist = FXCollections.observableArrayList(L);

        CoImage.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
        CoIdProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        CoIdCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        CoNomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        CoPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        CoQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        CoIDescriptopn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableProduit.setItems(viewlist);

    }

    private void loadDate() {

        ServiceProduit serprod = new ServiceProduit();

        List<Produit> L = new ArrayList();

        L = serprod.displayProduit();
        ObservableList viewlist = FXCollections.observableArrayList(L);

        CoImage.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
        CoIdProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        CoIdCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        CoNomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        CoPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        CoQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        CoIDescriptopn.setCellValueFactory(new PropertyValueFactory<>("description"));

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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                              Produit data = getTableView().getItems().get(getIndex());
                              ServiceProduit sr = new ServiceProduit();
                              sr.supprimmerProduit(data.getIdProduit());
                              loadDate();
//                            try {
//                                student = studentsTable.getSelectionModel().getSelectedItem();
//                                query = "DELETE FROM `student` WHERE id  ="+student.getId();
//                                connection = DbConnect.getConnect();
//                                preparedStatement = connection.prepareStatement(query);
//                                preparedStatement.execute();
//                                refreshTable();
//                                
//                            } catch (SQLException ex) {
//                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

//                            student = studentsTable.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader ();
//                            loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            
//                            AddStudentController addStudentController = loader.getController();
//                            addStudentController.setUpdate(true);
//                            addStudentController.setTextField(student.getId(), student.getName(), 
//                                    student.getBirth().toLocalDate(),student.getAdress(), student.getEmail());
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            ActionType = "Modifier";
                            idProduit = Integer.valueOf((tableProduit.getSelectionModel().getSelectedItem().getIdProduit()));
                            System.out.println("IDProduit Selctioned is ===> " + idProduit);

                            try {
                                Parent parent = FXMLLoader.load(getClass().getResource("AddUpdateFXML.fxml"));
                                Stage popup = new Stage();
                                Scene scene = new Scene(parent);
                                popup.setScene(scene);
                                popup.showAndWait();
                                if (confirmed == true) {
                                    popup.close();
                                    System.out.println("fy wolst l if te3 confirmed = true");
                                    loadDate();
                                } else {
                                    System.out.println("Exit");
                                }
                            } catch (IOException ex) {
                                System.out.println("Error Opening fxml!!");
                                ex.printStackTrace();
                            }

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
        };
        CoIAction.setCellFactory(cellFoctory);
        tableProduit.setItems(viewlist); //studentsTable.setItems(StudentList);

    }

    @FXML
    private void ajouterProduitButton(ActionEvent event) {
        ActionType = "Ajouter";
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddUpdateFXML.fxml"));
            Stage popup = new Stage();
            Scene scene = new Scene(parent);
            popup.setScene(scene);
            popup.showAndWait();
            if (confirmed == true) {
                popup.close();
                System.out.println("fy wolst l if te3 confirmed = true");
                loadDate();
            } else {
                System.out.println("Exit!");
            }
        } catch (IOException ex) {
            System.out.println("Error Opening fxml!!");
            ex.printStackTrace();
        }

    }

    @FXML
    private void consulterCommandeButton(ActionEvent event) throws IOException {
        
               Parent home_page_parent = FXMLLoader.load(getClass().getResource("AdminCommande.fxml"));
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

    @FXML
    private void consulterCategorieButton(ActionEvent event) throws IOException {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("GestionCategorie.fxml"));
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
