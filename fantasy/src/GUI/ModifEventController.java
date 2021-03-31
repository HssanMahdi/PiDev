/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.io.IOException;
import entites.MatchEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tools.MyConnection;
import services.ServiceMatchEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifEventController implements Initializable {
    List <MatchEvent>lsMatch;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField tfTitre;
    @FXML
    private DatePicker tfDtae;
    @FXML
    private Button modif;
    @FXML
    private ComboBox<String> comboA;
    @FXML
    private ComboBox<String> comboB;
    ServiceMatchEvent SM = new ServiceMatchEvent();
    int matchid;
    MatchEvent e = new MatchEvent();
    java.sql.Date dte;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboA.setItems(FillComboBox());
        comboB.setItems(FillComboBox());
    }

    @FXML
    private void ModifierEquipe(ActionEvent event) {
        e.setIdMatch(matchid);
        e.setTitre(tfTitre.getText());
        LocalDate ld = tfDtae.getValue();
        dte = java.sql.Date.valueOf(ld);
        e.setDateMatch(dte);
        e.setNomEquipeA(comboA.getSelectionModel().getSelectedItem());
        e.setNomEquipeB(comboB.getSelectionModel().getSelectedItem());
        int idA = SM.getEquipe(comboA.getSelectionModel().getSelectedItem()).getId();
        int idB = SM.getEquipe(comboB.getSelectionModel().getSelectedItem()).getId();
        e.setId_equipeA(idA);
        e.setId_equipeB(idB);

        SM.updateMatch(e);
        modif.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("AddEvent.fxml"));
          try {
            loader.load();
          AddEventController fxmlcontroller = loader.getController();
          fxmlcontroller.afficher(lsMatch);
          } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void setTextField(int id, String titre, java.sql.Date dateMatch) {
        matchid = id;
        tfTitre.setText(titre);
        LocalDate ld = dateMatch.toLocalDate();

        tfDtae.setValue(ld);

    }

    public void ComboBoxEquipeA(String nom) {
        comboA.getSelectionModel().select(nom);
    }

    public void ComboBoxEquipeB(String nom) {
        comboB.getSelectionModel().select(nom);
    }

    private ObservableList FillComboBox() {
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

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return option;
    }
    
    public void fill(List<MatchEvent>ls){
    lsMatch=ls;
    }
}
