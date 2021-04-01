/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class StatistiqueFXMLController implements Initializable {

    @FXML
    private BarChart<String, Double> barchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
            XYChart.Series<String, Double> set = new  XYChart.Series<>();
            
             try {
            String requete ="SELECT produit.nom_produit ,AVG(rating.rate) FROM produit INNER JOIN rating ON produit.id_produit=rating.id_produit";
            //"SELECT produit.nom_produit ,AVG(rating.rate) FROM produit INNER JOIN rating ON produit.id_produit=rating.id_produit"
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                System.out.println("t3ada");
           set.getData().add(new XYChart.Data<>(rs.getString("produit.nom_produit"), rs.getDouble("AVG(rating.rate)")));
           
           
       }
                 System.out.println("5raj");
           //  barchart.getData().add(set);
           barchart.getData().add(set);
                 System.out.println("ala5ir");
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
      
     
    }    
    
}
