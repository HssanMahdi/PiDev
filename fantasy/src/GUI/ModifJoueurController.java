/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ServiceEquipe;
import services.ServiceJoueur;
import tools.MyConnection;
import entites.Joueur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifJoueurController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;

    @FXML
    private Button browse;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextField tfScore;
    @FXML
    private TextField tfLogo;
    @FXML
    private TextField tfPrix;
    @FXML
    private ComboBox<String> positionComoBox;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;
    ServiceEquipe SE = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    Joueur j = new Joueur();
    int JoueurId;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("mil", "Att", "Des", "G");
        positionComoBox.setItems(list);
        combo.setItems(FXCollections.observableArrayList(FillComboBox()));

    }

    @FXML
    private void ModifJoueur(ActionEvent event) {

        j.setIdJoueur(JoueurId);

//        if(validateFields()){
        j.setNomJoueur(tfNom.getText());
        j.setPrenomJoueur(tfPrenom.getText());

        j.setPosition(positionComoBox.getSelectionModel().getSelectedItem().toString());
        j.setScoreJoueur(Integer.parseInt(tfScore.getText()));
        j.setLogoJoueur(tfLogo.getText());
        j.setPrixJoueur(Integer.parseInt(tfPrix.getText()));

//
        int i = SE.getByName(combo.getValue()).getId();
        j.setIdG(i);
        SJ.updateJoueur(j);

    }

    @FXML
    private void Browse(ActionEvent event) {
               final FileChooser fileChooser = new FileChooser();
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String s = file.getName();
//            tfLogo.setText(s);
            InputStream inStream = null;
            OutputStream outStream = null;
            File Copyfile = new File("C:\\wamp\\www\\PIProjet\\" + s);
            try {

                inStream = new FileInputStream(file);
                outStream = new FileOutputStream(Copyfile);

                byte[] buffer = new byte[(int) file.length()];

                int length;
                //copy the file content in bytes 
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            String f = Copyfile.toURI().toString();
            tfLogo.setText(f);
        }
    }

    void setTextField(int id, String nom, String prenom, int score, String logo, int prix) {
        JoueurId = id;
        tfNom.setText(nom);
        tfPrenom.setText(prenom);
        tfScore.setText("" + score);
        tfLogo.setText(logo);
        tfPrix.setText("" + prix);

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

    public void ComboBoxPosition(String nom) {
        positionComoBox.getSelectionModel().select(nom);
    }

    public void ComboBoxEquipe(String nom) {
        combo.getSelectionModel().select(nom);
    }

}
