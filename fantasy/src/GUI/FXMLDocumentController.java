/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Services.ServiceEquipe;
import Services.ServiceJoueur;
import Tools.MyConnection;
import entities.Joueur;
import entities.Mail;
import entities.ManagerFootball;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfScore;
    @FXML
    private TextField tfLogo;
    @FXML
    private TextField tfPrix;
    private Label LAffiche;
    @FXML
    private TableView<Joueur> tabAffiche;
    private TableColumn<Joueur, Integer> id;
    @FXML
    private TableColumn<Joueur, String> nom;
    @FXML
    private TableColumn<Joueur, String> prenom;
    @FXML
    private TableColumn<Joueur, String> position;

    @FXML
    private TableColumn<Joueur, ImageView> logo;
    @FXML
    private TableColumn<Joueur, Integer> prix;
    public ObservableList<Joueur> data = FXCollections.observableArrayList();
    @FXML
    private Button export;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private ComboBox<String> combo;

    private static int ind = 0;
    @FXML
    private ComboBox<String> positionComoBox;
    @FXML
    private Button browse;
    @FXML
    private Button button;
    @FXML
    private TextField search;
    ServiceJoueur SJ = new ServiceJoueur();
    ServiceEquipe SE = new ServiceEquipe();
    @FXML
    private TableColumn<Joueur, String> nomEq;
    @FXML
    private ComboBox<String> sortcombo;
    @FXML
    private Button sort;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
                "Position",
                "Prix"
        );
        sortcombo.getItems().addAll(options);

        ObservableList<String> list = FXCollections.observableArrayList("mil", "Att", "Des", "G");
        positionComoBox.setItems(list);

        //Affichage Tableau
        loadData();
        //Liste des equipes
        combo.setItems(FXCollections.observableArrayList(FillComboBox()));

    }

    @FXML
    private void AjouterJoueur(ActionEvent event) {

        Joueur e = new Joueur();
        ManagerFootball mn = new ManagerFootball(1, "Ghada", "ghada.hajjaji@esprit.tn", "azz", "MF");
        if (validateFields()) {
            e.setNomJoueur(tfNom.getText());
            e.setPrenomJoueur(tfPrenom.getText());

            e.setPosition(positionComoBox.getSelectionModel().getSelectedItem());
            e.setScoreJoueur(Integer.parseInt(tfScore.getText()));
            e.setLogoJoueur(tfLogo.getText());
            e.setPrixJoueur(Integer.parseInt(tfPrix.getText()));

//
            int i = SE.getByName(combo.getValue()).getId();
            e.setIdG(i);

            SJ.addJoueur(e);
            String maprix = tfPrix.getText();

            Mail mail = new Mail();
            mail.envoyer2(mn, tfNom.getText(), tfPrenom.getText(), positionComoBox.getSelectionModel().getSelectedItem(), Integer.parseInt(tfScore.getText()), Integer.parseInt(tfPrix.getText()), combo.getValue());

            loadData();
        }

    }

    private void loadData() {

        Joueur j = new Joueur();

        nom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomJoueur"));
        prenom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenomJoueur"));
        position.setCellValueFactory(new PropertyValueFactory<Joueur, String>("position"));
        logo.setCellValueFactory(new PropertyValueFactory<Joueur, ImageView>("logoimage"));
        prix.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("prixJoueur"));

        nomEq.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomEquipe"));

        ObservableList<Joueur> res;

        res = FXCollections.observableArrayList(SJ.getJoueurs());
        tabAffiche.setItems(res);
//        tabAffiche.setTableMenuButtonVisible(true);
    }

    @FXML
    private void ExportExcel(ActionEvent event) {
        try {
            String query = "SELECT * from Joueur";
            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Player Détails");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Id Joueur");
            header.createCell(1).setCellValue("Nom Joueur");
            header.createCell(2).setCellValue("Prenom Joueur");
            header.createCell(3).setCellValue("Position");
            header.createCell(4).setCellValue("Score Joueur");
            header.createCell(5).setCellValue("Logo Joueur");
            header.createCell(6).setCellValue("Prix Joueur");
            header.createCell(7).setCellValue("idG");

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.setColumnWidth(3, 256 * 25);
            sheet.setZoom(150);

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getInt("id_joueur"));
                row.createCell(1).setCellValue(rs.getString("nom_joueur"));
                row.createCell(2).setCellValue(rs.getString("prenom_joueur"));
                row.createCell(3).setCellValue(rs.getString("position"));
                row.createCell(4).setCellValue(rs.getInt("score_joueur"));
                row.createCell(5).setCellValue(rs.getString("logo_joueur"));
                row.createCell(6).setCellValue(rs.getInt("prix_joueur"));
                row.createCell(7).setCellValue(rs.getInt("id_equipe"));
                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("PlayerDetails" + ind + ".xlsx");
            ind++;
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inforamtion dialog");
            alert.setHeaderText(null);
            alert.setContentText("Player Details Exported in Excel Sheet");
            alert.showAndWait();

            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
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
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            tfLogo.setText(f);
        }
    }

    private boolean validateFields() {
        if (tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() | tfScore.getText().isEmpty()
                | tfLogo.getText().isEmpty() | tfPrix.getText().isEmpty()) {
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
    private void DeleteJoueur(ActionEvent event) {
        Joueur j = (Joueur) tabAffiche.getSelectionModel().getSelectedItem();
        SJ.deleteJoueur(j);
        loadData();
    }

    @FXML
    private void Search(ActionEvent event) {

        String charac = search.getText();

        ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.SearchPlayer(charac);
        ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
        tabAffiche.setItems(obs);
    }

    @FXML
    private void Modifier(ActionEvent event) {
        Joueur jr = tabAffiche.getSelectionModel().getSelectedItem();
        ServiceJoueur sj = new ServiceJoueur();
        ServiceEquipe se = new ServiceEquipe();
        String a = se.getById(jr.getIdG()).getNom();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifJoueur.fxml"));

        try {

            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(ListeEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModifJoueurController modifJoueur = loader.getController();
        modifJoueur.ComboBoxPosition(jr.getPosition());
        modifJoueur.ComboBoxEquipe(a);

        modifJoueur.setTextField(jr.getIdJoueur(), jr.getNomJoueur(), jr.getPrenomJoueur(), jr.getScoreJoueur(), jr.getLogoJoueur(), jr.getPrixJoueur());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        loadData();
    }

    @FXML
    private void sort(MouseEvent event) {
        if (sortcombo.getValue().equals("Prix")) {

            Services.ServiceJoueur SJ = new ServiceJoueur();
            ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.Trier(1);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            tabAffiche.setItems(obs);

        } else if (sortcombo.getValue().equals("Position")) {

            Services.ServiceJoueur SJ = new ServiceJoueur();
            ArrayList<Joueur> a = (ArrayList<Joueur>) SJ.Trier(2);
            ObservableList<Joueur> obs = FXCollections.observableArrayList(a);
            tabAffiche.setItems(obs);
        }
    }

}
