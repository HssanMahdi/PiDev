/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import static GUI.PasserCommandeController.confirmedCommande;
import static GUI.PasserCommandeController.idCommandeModif;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import services.ServiceCommande;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author mhamdi iheb
 */
public class UpdateCommandeController implements Initializable {

    @FXML
    private JFXTextField tfadresseLaivraison;
    @FXML
    private JFXTextField tfCountry;
    @FXML
    private JFXTextField tfCodePostal;
    
   public int activ_user_id=10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        Connection cnx = MyConnection.getInstance().getCnx();
            try {
                String requeteee = "SELECT * FROM commande WHERE id_cmd  = '" + idCommandeModif + "' AND id_User ='" + activ_user_id + "' ";
               
                Statement psttt = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rss = psttt.executeQuery(requeteee);
                while (rss.next()) {

                    //tfIdCategorie.setText(String.valueOf(rss.getInt(2)));
                    // tfNomProduit.setText(rss.getString(3));
            tfadresseLaivraison.setText(rss.getString("adr_livraison"));
           tfCountry.setText(rss.getString("country"));
           tfCodePostal.setText(String.valueOf(rss.getInt("post_code")));
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }    

    @FXML
    private void saveModifButton(ActionEvent event) {
        
       
            System.out.println("Reeena fel Modifier Taw");
            ServiceCommande serprod = new ServiceCommande();

          
          String adresse = tfadresseLaivraison.getText();
    
         String country =   tfCountry.getText();;
         
         int codeP=   Integer.parseInt( tfCodePostal.getText().toString());
           
      

            serprod.update_order(adresse, country, codeP, idCommandeModif, activ_user_id);
                    
           confirmedCommande = true;
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        
        
    }

    @FXML
    private void CancelButton(ActionEvent event) {
        
                confirmedCommande = true;
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
    }
    
}
