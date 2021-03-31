/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class JavaFXApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("JoueurUserInt.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("EquipeUserInt.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("DisplayEquipe.fxml"));
      //   Parent root = FXMLLoader.load(getClass().getResource("DisplayPlayer.fxml"));
      //   Parent root = FXMLLoader.load(getClass().getResource("DisplayHighls.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("JoueurEquipe.fxml"));
// Parent root = FXMLLoader.load(getClass().getResource("AddHighlights.fxml"));
        //  Parent root = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));

        Scene scene = new Scene(root);
//        stage.initStyle(StageStyle.TRANSPARENT);
//
//        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
