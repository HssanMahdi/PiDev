/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entites.Produit;
import static GUI.HomeController.ActionType;
import static GUI.HomeController.idProduit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import services.ServiceProduit;
import tools.MyConnection;
import tools.UploadImageImage;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class AddUpdateFXMLController implements Initializable {

    @FXML
    private JFXTextField tfNomProduit;
    @FXML
    private JFXTextField tfPrixUnitaire;
    @FXML
    private JFXTextField tfIdCategorie;
    @FXML
    private JFXTextField tfQuantiteP;
    @FXML
    private JFXComboBox<String> NomCategorieCom;
    @FXML
    private ImageView ImageView;
    private JFXTextField tfImageP;
    @FXML
    private JFXTextField tfDescriptionP;

    ObservableList list = FXCollections.observableArrayList();
    
    String path ="";
      List<File> files;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillComboCategorie();

        if (ActionType == "Modifier") {
            System.out.println("iDProduit a Modifier " + idProduit);
            Connection cnx = MyConnection.getInstance().getCnx();
            try {
                String requeteee = "SELECT * FROM produit WHERE id_produit  = '" + idProduit + "'";
                Statement psttt = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rss = psttt.executeQuery(requeteee);
                while (rss.next()) {

                    tfIdCategorie.setText(String.valueOf(rss.getInt(2)));
                    tfNomProduit.setText(rss.getString(3));
                    NomCategorieCom.setValue(rss.getString(4));
                    tfDescriptionP.setText(rss.getString(8));
                  path=rss.getString(7);
                    tfPrixUnitaire.setText(String.valueOf(rss.getFloat(5)));
                    tfQuantiteP.setText(rss.getString(6));
                   Image image; 
                    try {
                        image = new Image(new FileInputStream(path));
                        ImageView.setImage(image);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AddUpdateFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void SelectImageButton(ActionEvent event) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filname = f.getAbsolutePath();
            tfImageP.setText(filname);

            Image getAbsolutePath = null;

            Image image = new Image(new FileInputStream(filname));

            ImageView.setImage(image);
            ImageView.setPreserveRatio(true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveProductButton(ActionEvent event) {
        if (ActionType == "Modifier") {
            System.out.println("Reeena fel Modifier Taw");
            ServiceProduit serprod = new ServiceProduit();
              UploadImageImage uiu =new UploadImageImage();
            uiu.saveFile(files.get(0));

            Produit p = new Produit();

            p.setIdCategorie(Integer.parseInt(tfIdCategorie.getText().toString()));
            p.setNomProduit(tfNomProduit.getText());
            p.setNomCategorie(NomCategorieCom.getSelectionModel().getSelectedItem());
            p.setDescription(tfDescriptionP.getText());
        p.setImage(path);

            p.setPrixUnitaire(Float.parseFloat(tfPrixUnitaire.getText().toString()));
            p.setQuantite(Integer.parseInt(tfQuantiteP.getText()));

            serprod.modifierProduit(p, idProduit);
                    
            HomeController.confirmed = true;
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }
        if (ActionType == "Ajouter") {
            
//            new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if(tfIdCategorie.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your adress");
//                    
//                } 
//                if(tfNomProduit.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your country");
//                    
//                } 
//                if(NomCategorieCom.getSelectionModel().getSelectedItem()==null) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
//                }
//                   if(tfDescriptionP.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
//                }
//                      if(path==null) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
//                }
//                         if(tfPrixUnitaire.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
//                }
//                         
//                                if(tfQuantiteP.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a postcode");
//                }
//            }
//            }
            
            UploadImageImage ui =new UploadImageImage();
            ui.saveFile(files.get(0));
            
         
            System.out.println("Reeena fel Ajouter Taw");

            ServiceProduit serprod = new ServiceProduit();

            Produit p = new Produit();

            p.setIdCategorie(Integer.parseInt(tfIdCategorie.getText().toString()));
            p.setNomProduit(tfNomProduit.getText());
            p.setNomCategorie(NomCategorieCom.getSelectionModel().getSelectedItem());
            p.setDescription(tfDescriptionP.getText());
            
            

            p.setImage(path);
          
            

            p.setPrixUnitaire(Float.parseFloat(tfPrixUnitaire.getText().toString()));
            p.setQuantite(Integer.parseInt(tfQuantiteP.getText()));

            serprod.ajouterProduit(p);

            HomeController.confirmed = true;
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

    }

    public void fillComboCategorie() {
        try {
            String requete = "SELECT nom_categorie FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                list.add(rs.getString("nom_categorie"));

                NomCategorieCom.setItems(list);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int selectIdCategorie(String nomCat) {

        int idCat = 0;
        try {
            String requete = "SELECT id_categorie FROM categorie where nom_categorie='" + nomCat + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                idCat = rs.getInt("id_categorie");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return idCat;
    }

    @FXML
    private void SelectCategorieButton(ActionEvent event) {
        String nom_cat = NomCategorieCom.getSelectionModel().getSelectedItem();
        int idCat = selectIdCategorie(nom_cat);
        tfIdCategorie.setText(String.valueOf(idCat));
    }

    @FXML
    private void handleDragOver_imgProduit(DragEvent event) {
                if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        
    }

    @FXML
    private void handleDrop_imgProduit(DragEvent event) throws FileNotFoundException {
           files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        ImageView.setImage(img);
        path = "C:\\wamp64\\www\\PIProjet\\"+files.get(0).getName();
       // path = files.get(0).getAbsolutePath();
        System.out.println(path);    
        
    }
    
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
        
    }

}
