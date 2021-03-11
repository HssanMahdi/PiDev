/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fantasy;

import entities.Categorie;
import entities.Produit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import services.ServiceProduit;
import tools.MyConnection;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class AfficherProduitFXMLController implements Initializable {

    @FXML
    private TableColumn<Produit, ImageView> CoImage;
    @FXML
    private TableColumn<Produit, Integer> CoIdProduit;
    @FXML
    private TableColumn<Produit, Integer> CoIdCategorie;
    @FXML
    private TableColumn<Produit, String> CoNomProduit;
    @FXML
    private TableColumn<Produit, Float> CoPrixUnitaire;
    @FXML
    private TableColumn<Produit, Integer> CoQuantite;
    @FXML
    private TableColumn<Produit, String> CoDescription;
    @FXML
    private TableView<Produit> tableProduit;
    private PieChart pieChart;
    
   

      
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                  
    ServiceProduit serprod = new  ServiceProduit();
     
          List<Produit> L = new ArrayList();
          
                 L=serprod.displayProduit();
                 ObservableList viewlist = FXCollections.observableArrayList(L);
              
               
             CoImage.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
             CoIdProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
             CoIdCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
             CoNomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
             CoPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
             CoQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          
           
           
              CoDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
              
//            


          
        tableProduit.setItems(viewlist);
   
        // TODO
    }    

    @FXML
    private void statisticPrductGtegorie(ActionEvent event) {
          ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        
         try {
            String requete = "SELECT * FROM produit";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
             
           data.add(new PieChart.Data(rs.getString("nom_produit"),rs.getInt("quantite")));
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        pieChart.setTitle("Stat");
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setData(data);
        
        
    }

    @FXML
    private void exportListProduitButton(ActionEvent event) {
        
       
   
        try {
            String query = "SELECT * from produit";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("produit Infos");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_produit");
            header.createCell(1).setCellValue("id_categorie");
            header.createCell(2).setCellValue("nom_produit");
            header.createCell(3).setCellValue("prix_unitaire");
            header.createCell(4).setCellValue("quantite");
             header.createCell(5).setCellValue("description");
       

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
          
            sheet.setColumnWidth(3, 256 * 25);
            sheet.setZoom(150);
            
            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getInt("id_produit"));
                row.createCell(1).setCellValue(rs.getInt("id_categorie"));
                row.createCell(2).setCellValue(rs.getString("nom_produit"));
                row.createCell(3).setCellValue(rs.getFloat("prix_unitaire"));
                row.createCell(4).setCellValue(rs.getInt("quantite"));
                row.createCell(5).setCellValue(rs.getString("description"));
              
               
                
                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("produit Infos" + index + ".xlsx");
            index++;
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inforamtion dialog");
            alert.setHeaderText(null);
            alert.setContentText("products Details Exported in Excel Sheet");
            alert.showAndWait();

            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
        
        
    
    
}
