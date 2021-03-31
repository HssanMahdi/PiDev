package GUI;


import entites.Adherent;
import entites.AdminSysteme;
import entites.ManagerFootball;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.AdherentCRUD;
import services.AdminSystemeCRUD;
import services.ManagerFootballCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class FXMLLoginController implements Initializable {

    public static int userIden = 0;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btnok;
    @FXML
    private PasswordField tfMotdepasse;
    @FXML
    private Label label;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button btncreer;
    static Adherent user = new Adherent();
    static ManagerFootball manager = new ManagerFootball();
    static AdminSysteme admin = new AdminSysteme();
    private Button btnrecuperer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void login(ActionEvent a) throws IOException {

        Stage mainStage = new Stage();
        if (validerChamps()) {

            String nom = tfNom.getText();
            String pass = encrypt(tfMotdepasse.getText());

            if (nom.equals("") && pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Username or Password blank");
            } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    pst = MyConnection.getInstance().getCnx()
                            .prepareStatement("select id_user from user where nom_user=? and password=?");
                    pst.setString(1, nom);
                    pst.setString(2, pass);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Success");
                        pst = MyConnection.getInstance().getCnx()
                                .prepareStatement("SELECT type_user FROM user WHERE id_user=" + rs.getInt("id_user") + ";");
                        ResultSet rs1 = pst.executeQuery();
                        if (rs1.next()) {
                            String t = rs1.getString("type_user");
                            System.out.println(t);
                            if (t.equals("ad")) {

                                AdherentCRUD us = new AdherentCRUD();
                                user = us.connecter(rs.getInt("id_user"));

                                userIden = user.getId_user();
                                // System.out.println("koukuuuuuuuuuuuuuu" + user.getEmail());
                                btnok.getScene().getWindow().hide();
                                Parent root = FXMLLoader.load(getClass().getResource("GestionUser.fxml"));
                                Scene scene = new Scene(root);
                                mainStage.setScene(scene);
                                mainStage.show();
                            } else if (t.equals("adS")) {
                                AdminSystemeCRUD us = new AdminSystemeCRUD();
                                admin = us.connecter(rs.getInt("id_user"));

                                userIden = admin.getId_user();
                                btnok.getScene().getWindow().hide();
                                Parent root1 = FXMLLoader.load(getClass().getResource("AdminSysteme.fxml"));
                                Scene scene1 = new Scene(root1);
                                mainStage.setScene(scene1);
                                mainStage.show();
                            } else {
                                ManagerFootballCRUD us = new ManagerFootballCRUD();
                                manager = us.connecter(rs.getInt("id_user"));

                                userIden = manager.getId_user();
                                btnok.getScene().getWindow().hide();
                                Parent root2 = FXMLLoader.load(getClass().getResource("GestionManagerFootball.fxml"));
                                Stage mainStage2 = new Stage();
                                Scene scene2 = new Scene(root2);
                                mainStage2.setScene(scene2);
                                mainStage2.show();
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                        tfNom.setText("");
                        tfMotdepasse.setText("");
                        tfNom.requestFocus();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @FXML
    private void CreateAccount(ActionEvent event) throws IOException {
        btncreer.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Adherent.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    private boolean validerChamps() {
        if (tfNom.getText().isEmpty() | tfMotdepasse.getText().isEmpty()) {
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

    public String decrypt(String pw) {

        return new String(Base64.getMimeDecoder().decode(pw));
    }

    public String encrypt(String pw) {

        return Base64.getEncoder().encodeToString(pw.getBytes());
    }

}
