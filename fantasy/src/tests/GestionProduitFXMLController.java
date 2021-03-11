/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fantasy;

import entities.Produit;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import services.ServiceProduit;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class GestionProduitFXMLController implements Initializable {

    @FXML
    private TextField tfPrixUnitaire;
    @FXML
    private TextField tfNomProduit;
    @FXML
    private TextField tfImageP;
    @FXML
    private TextField tfQuantiteP;
    @FXML
    private TextField tfDescriptionP;
    private TextField tfIdModif;
    private TextField tfIdSupp;
    private TextField tfIdCategorie;
    @FXML
    private ComboBox<?> idCategorieCom;
    
      public static int id_prod;
    
     ObservableList list=FXCollections.observableArrayList();  
     ObservableList prodlist=FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> idProdCombo;
    @FXML
    private ImageView ImageView;
      


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        fillComboCategorie();
        fillComboProduit();
        
    }    

    @FXML
    
    
    private void addProductButton(ActionEvent event) {
        
        ServiceProduit serprod = new  ServiceProduit();
        Produit p= new Produit();
       int id_cat=Integer.parseInt(idCategorieCom.getSelectionModel().getSelectedItem().toString());
        p.setIdCategorie(id_cat);
        p.setNomProduit(tfNomProduit.getText());
        p.setDescription(tfDescriptionP.getText());
        p.setImage(tfImageP.getText());
        p.setPrixUnitaire(Float.parseFloat(tfPrixUnitaire.getText()));
        p.setQuantite(Integer.parseInt(tfQuantiteP.getText()));
        
        serprod.ajouterProduit(p);
       
        
        
        
    }

    @FXML
    private void updateProductButton(ActionEvent event) {
        
             
             ServiceProduit serprod = new  ServiceProduit();
        Produit p= new Produit();
        
            int id_cat=Integer.parseInt(idCategorieCom.getSelectionModel().getSelectedItem().toString());
        p.setIdCategorie(id_cat);
        
        p.setIdCategorie(id_cat);
        p.setNomProduit(tfNomProduit.getText());
        p.setDescription(tfDescriptionP.getText());
        p.setImage(tfImageP.getText());
        p.setPrixUnitaire(Float.parseFloat(tfPrixUnitaire.getText()));
        p.setQuantite(Integer.parseInt(tfQuantiteP.getText()));
           
          
           
           serprod.modifierProduit(p,id_prod);
    }

    @FXML
    private void deleteProductButton(ActionEvent event) {
        
         
          ServiceProduit serprod = new  ServiceProduit();
        
      serprod.supprimmerProduit(id_prod);
      
     
    
   
      
    }

    @FXML
    private void displayProductButton(ActionEvent event) throws IOException {
        
              
  
          Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherProduitFXML.fxml"));
       Scene home_page_scene = new Scene (home_page_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
   
    }
    

 
    @FXML
    private void selectIdCategorieCombobox(ActionEvent event) {
 
        
    }
    
    public void fillComboCategorie()
    {
              try {
            String requete = "SELECT id_categorie FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
              
                
                list.add(rs.getInt("id_categorie"));

       
             idCategorieCom.setItems(list);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void selectIdProduitCombobox(ActionEvent event) {
        
        id_prod=Integer.parseInt( idProdCombo.getSelectionModel().getSelectedItem().toString());
        
        
        
    }
   
        public void fillComboProduit()
    {
              try {
            String requete = "SELECT id_produit FROM produit";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
              
                
                prodlist.add(rs.getInt("id_produit"));

       
            idProdCombo.setItems(prodlist);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   

    @FXML
    private void addImageButton(ActionEvent event) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filname = f.getAbsolutePath();
            tfImageP.setText(filname);
            Image getAbsolutePath=null;  
             
             Image image = new Image(new FileInputStream(filname));
             
             
             ImageView.setImage(image);
             ImageView.setPreserveRatio(true);
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
        
        
    }
}
