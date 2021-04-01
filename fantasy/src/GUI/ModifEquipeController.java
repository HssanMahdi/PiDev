/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Equipe;
import services.ServiceEquipe;
import tools.UploadImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifEquipeController implements Initializable {

    File f;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfStade;
    @FXML
    private TextField tfLogo;
    @FXML
    private Button modif;
    int idEquipe;
    String logoPath;
    Equipe eq = new Equipe();
    ServiceEquipe SE = new ServiceEquipe();
    @FXML
    private AnchorPane main;
    @FXML
    private ImageView imgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void ModifierEquipe(ActionEvent event) {
        if (!logoPath.equals(tfLogo.getText())) {
            UploadImage ui = new UploadImage();
            String path = ui.saveFile(f);
            tfLogo.setText(path);
        }
        if (validateFields()) {
            eq.setIdG(idEquipe);

            eq.setNom(tfNom.getText());

            eq.setLogo_equipe(tfLogo.getText());
            eq.setstade(tfStade.getText());

            SE.updateEquipe(eq);
            Notifications notificationBuilder = Notifications.create()
                    .title("Alerte")
                    .text("Equipe Modifiée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("clicked");
                    });

            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();

        }
    }

    void setTextField(int id, String nom, String logo, String stade) {
        idEquipe = id;
        logoPath = logo;
        tfNom.setText(nom);
        tfLogo.setText(logo);
        tfStade.setText(stade);
        String url = "file:" + logo;
        Image image;
        image = new Image(url);
        imgView.setImage(image);
        imgView.setPreserveRatio(true);

    }

    private boolean validateFields() {
        if (tfNom.getText().isEmpty() | tfLogo.getText().isEmpty() | tfStade.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Alerte")
                    .text("Veuillez saisir les champs")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("clicked");
                    });

            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();

            return false;

        }
        return true;
    }

}
