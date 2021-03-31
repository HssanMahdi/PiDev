/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Publication;
import services.PubService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifHighlsController implements Initializable {

    int idPub;
    @FXML
    private TextField tfNom;
    @FXML
    private Button modif_btn;
    Publication pb = new Publication();
    PubService ps = new PubService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setTextField(int id, String nom) {
        idPub = id;
        tfNom.setText(nom);
    }

    @FXML
    private void Modifier(ActionEvent event) {
        pb.setid_pub(idPub);
        if(validateFields()){
        pb.setnom_pub(tfNom.getText());

        ps.updatePub(pb);}
    }
     private boolean validateFields() {
        if (tfNom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valider les champs");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir les champs");
            alert.showAndWait();

            return false;
        } else {
        }
        return true;
    }
}
