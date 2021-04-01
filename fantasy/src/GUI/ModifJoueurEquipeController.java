/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Joueur;
import services.ServiceEquipe;
import services.ServiceJoueur;
import tools.MyConnection;
import tools.UploadImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifJoueurEquipeController implements Initializable {
    File f;
    String logoPath;
    int JoueurId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button browse;
    @FXML
    private ComboBox<String> combo;
    
    @FXML
    private TextField tfLogo;
    @FXML
    private TextField tfPrix;
    @FXML
    private ComboBox<String> positionComoBox;
    @FXML
    private Button modif;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;
    ServiceEquipe SE = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    Joueur j = new Joueur();
    @FXML
    private ImageView imgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("mil", "Att", "Des", "G");
        positionComoBox.setItems(list);
        combo.setItems(FXCollections.observableArrayList(FillComboBox()));
    }

    void setTextField(int id, String nom, String prenom, int score, String logo, int prix) {
        JoueurId = id;
        logoPath =logo;
        tfNom.setText(nom);
        tfPrenom.setText(prenom);
        tfLogo.setText(logo);
        String url = "file:" + logo;
        Image image;
        image = new Image(url);
        imgView.setImage(image);
        imgView.setPreserveRatio(true);
        tfPrix.setText("" + prix);

    }

    @FXML
    private void Browse(ActionEvent event) {
      try {
            final FileChooser fileChooser = new FileChooser();
            final Stage stage = null;

            f = fileChooser.showOpenDialog(stage);
            if (f != null) {
                String file = f.toURI().getPath();
                Image image;
                image = new Image(new FileInputStream(file));
                imgView.setImage(image);
                imgView.setPreserveRatio(true);
                String url = "C:\\wamp64\\www\\PIProjet\\" + f.getName();
                tfLogo.setText(file);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ModifierPl(ActionEvent event) {
        j.setIdJoueur(JoueurId);
        if (!logoPath.equals(tfLogo.getText())) {
            UploadImage ui = new UploadImage();
            String path = ui.saveFile(f);
            tfLogo.setText(path);
        }
        if(validateFields()){
        j.setNomJoueur(tfNom.getText());
        j.setPrenomJoueur(tfPrenom.getText());

        j.setPosition(positionComoBox.getSelectionModel().getSelectedItem());
        j.setLogoJoueur(tfLogo.getText());
        j.setPrixJoueur(Integer.parseInt(tfPrix.getText()));

//
        int i = SE.getByName(combo.getValue()).getId();
        j.setIdG(i);
        SJ.updateJoueur(j);
    }}
        private boolean validateFields() {
        if ( tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty()
                | tfLogo.getText().isEmpty() | tfPrix.getText().isEmpty()) {
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

    public void ComboBoxEquipe(String nom) {
        combo.getSelectionModel().select(nom);
    }

    public void ComboBoxPosition(String nom) {
        positionComoBox.getSelectionModel().select(nom);
    }

    private List<String> FillComboBox() {
        ObservableList option = FXCollections.observableArrayList();
        String query = "SELECT nom_equipe FROM Equipe";
        try {

            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                option.add(rs.getString("nom_equipe"));

            }

            pst.close();
            rs.close();
            return option;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

}
