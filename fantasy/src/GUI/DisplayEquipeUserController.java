/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Services.ServiceEquipe;
import Services.ServiceJoueur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Equipe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DisplayEquipeUserController implements Initializable {

    @FXML
    private TableView<Equipe> TabEq;
    @FXML
    private TableColumn<Equipe, ImageView> logo;
    @FXML
    private TableColumn<Equipe, String> nomEq;
    @FXML
    private TableColumn<Equipe, String> stadeEq;
    Services.ServiceEquipe SE = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    private TableColumn<Equipe, String> btn;
    @FXML
    private TableColumn<Equipe, String> action;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }

    private void loadData() {

        nomEq.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
        stadeEq.setCellValueFactory(new PropertyValueFactory<Equipe, String>("Stade"));
        logo.setCellValueFactory(new PropertyValueFactory<Equipe, ImageView>("logoimage"));

        Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFactory
                = new Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>>() {
            @Override
            public TableCell call(final TableColumn<Equipe, String> param) {
                final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {

                    //final Button btn = new Button("Joueur");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.GAMEPAD);
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:40px;"
                                    + "-fx-fill:Black;"
                            );
                            editIcon.setOnMouseClicked(event -> {
                                Equipe person = getTableView().getItems().get(getIndex());

                                int id = person.getId();
                                String a = SE.getById(id).getNom();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("DisplayJoueurUser.fxml"));

                                try {

                                    loader.load();

                                } catch (IOException ex) {
                                    System.out.println("Erreur");
                                }

                                DisplayJoueurUserController displaycontroller = loader.getController();
                                displaycontroller.loadData(SJ.getJoueurEquipe(id));

                                editIcon.getScene().getWindow().hide();
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.show();
                            });
                            setGraphic(editIcon);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        action.setCellFactory(cellFactory);
        ObservableList<Equipe> res;

        res = FXCollections.observableArrayList(SE.getEquipes());
        TabEq.setItems(res);

    }

    @FXML
    private void Back(ActionEvent event) {
    }

}
