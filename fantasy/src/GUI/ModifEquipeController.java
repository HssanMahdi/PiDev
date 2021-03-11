/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Services.ServiceEquipe;
import entities.Equipe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifEquipeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfStade;
    @FXML
    private TextField tfLogo;
    @FXML
    private Button modif;
    int idEquipe;
    Equipe eq = new Equipe();
    ServiceEquipe SE = new ServiceEquipe();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void ModifierEquipe(ActionEvent event) {
        eq.setIdG(idEquipe);

        eq.setNom(tfNom.getText());

        eq.setLogo_equipe(tfLogo.getText());
        eq.setstade(tfStade.getText());

        SE.updateEquipe(eq);

    }

    void setTextField(int id, String nom, String logo, String stade) {
        idEquipe = id;
        tfNom.setText(nom);
        tfLogo.setText(logo);
        tfStade.setText(stade);

    }

}
