/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.ManagerFootball;
import entites.Publication;
import services.PubService;
import tools.Mail;

import tools.UploadVideo;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AddHighlightsController implements Initializable {

    @FXML
    private TextField tag;

    File f;
    @FXML
    private MediaView PM;
    @FXML
    private TextField tfVid;
    @FXML
    private Button browsebtn;
    FXMLLoginController log= new FXMLLoginController();
    ManagerFootball mn = log.manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         idusers = loginController.userIden;

    }

    @FXML
    private void BrowseAction(ActionEvent event) {

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File(*.mp4)", "*.mp4");
        fc.getExtensionFilters().add(filter);

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tous les fichiers", "*.mp4"));

        f = fc.showOpenDialog(null);
        if (f != null) {

            String s = f.getName();
            String ur = "C:\\wamp64\\www\\PIProjet\\" + s;
            tfVid.setText(ur);
            Media MD;
            MediaPlayer MDP;
            MD = new Media(new File(f.getAbsolutePath()).toURI().toString());
            MDP = new MediaPlayer(MD);
            PM.setMediaPlayer(MDP);
        }
    }

    private boolean isInputValid() {
        if (tag.getText().isEmpty() | tfVid.getText().isEmpty()) {
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

    @FXML
    private void ajout(ActionEvent event) {

        if (isInputValid()) {
            UploadVideo ui = new UploadVideo();
            String path = ui.upload(f);
            tfVid.setText(path);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            PubService sp = new PubService();
            Publication p = new Publication();
            p.setnom_pub(tag.getText());
            p.setdate_pub(date);
            p.setcontenu_pub(tfVid.getText());

            sp.ajouter(p);

            Notifications notificationBuilder = Notifications.create()
                    .title("Post")
                    .text("Your Post have been added Successfully")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("clicked");
                    });

            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
            Mail mail = new Mail();
            mail.envoyerPub(mn, tag.getText(), date);
        }
    }

}
