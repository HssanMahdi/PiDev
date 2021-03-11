/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Services.ServiceEquipe;
import Services.ServiceJoueur;
import entities.Equipe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ListeEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> tabEq;

    @FXML
    private TableColumn<Equipe, ImageView> idlogo;
    @FXML
    private TableColumn<Equipe, String> nomeq;
    @FXML
    private TableColumn<Equipe, String> stadeeq;
    @FXML
    private Button DisplayJ;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField logoEq;
    @FXML
    private TextField tfStade;
    @FXML
    private Button browse;
    ServiceEquipe SE = new ServiceEquipe();
    @FXML
    private Button btnModif;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();

    }

    private void loadDate() {

        nomeq.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
        stadeeq.setCellValueFactory(new PropertyValueFactory<Equipe, String>("Stade"));
        idlogo.setCellValueFactory(new PropertyValueFactory<Equipe, ImageView>("logoimage"));

        ObservableList<Equipe> res;

        res = FXCollections.observableArrayList(SE.getEquipes());
        tabEq.setItems(res);

    }

    @FXML
    private void DisplayPlayer(ActionEvent event) {
        Equipe eq = tabEq.getSelectionModel().getSelectedItem();
        ServiceJoueur sj = new ServiceJoueur();
        ServiceEquipe se = new ServiceEquipe();

        int id = eq.getId();
        String a = se.getById(id).getNom();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML.fxml"));
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println("Erreur");
        }

        FXMLController fxmlcontroller = loader.getController();
        fxmlcontroller.loadData(sj.getJoueurEquipe(id));
        fxmlcontroller.loadComboBox(a);

        DisplayJ.getScene().getWindow().hide();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
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

            String f = Copyfile.toURI().getPath();
            logoEq.setText(f);
        }
    }

    @FXML
    private void AjouterEquipe(ActionEvent event) {
        Equipe e = new Equipe();
        if(validateFields()){
        e.setNom(tfNom.getText());
        e.setLogo_equipe(logoEq.getText());
        e.setstade(tfStade.getText());

        SE.addEquipe(e);
        loadDate();
        }
    }

    private boolean validateFields() {
        if (tfNom.getText().isEmpty() | logoEq.getText().isEmpty() | tfStade.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valider les champs");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir les champs");
            alert.showAndWait();

            return false;

        }
        return true;
    }

    @FXML
    private void DeleteEquipe(ActionEvent event) {
        Equipe e = tabEq.getSelectionModel().getSelectedItem();
        SE.deleteEquipe(e);
        loadDate();
    }

    @FXML
    private void ModifierEquipe(ActionEvent event) {
        Equipe eq = tabEq.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifEquipe.fxml"));

        try {

            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(ListeEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModifEquipeController modifEquipe = loader.getController();
        modifEquipe.setTextField(eq.getId(), eq.getNom(), eq.getLogo_equipe(), eq.getStade());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        loadDate();
    }

    @FXML
    private void Search(ActionEvent event) {
        String charac = search.getText();

        ArrayList<Equipe> a = (ArrayList<Equipe>) SE.SearchTeam(charac);
        ObservableList<Equipe> obs = FXCollections.observableArrayList(a);
        tabEq.setItems(obs);
    }

}
