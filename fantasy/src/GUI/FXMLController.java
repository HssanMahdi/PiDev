/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ServiceEquipe;
import services.ServiceJoueur;
import tools.MyConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Joueur;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXMLController Controller class
 *
 * @author PC
 */
public class FXMLController implements Initializable {

    public static final String RESULT = "C:/Users/PC/Desktop/PlayerDetails.pdf";

    @FXML
    private TableView<Joueur> tabAffiche;


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
    @FXML
    private TableColumn<Joueur, String> equipe;
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
    ServiceEquipe SE = new ServiceEquipe();
    ServiceJoueur SJ = new ServiceJoueur();
    @FXML
    private AnchorPane asso;
    @FXML
    private Button btnBack;
    @FXML
    private Button modif;
    @FXML
    private Button btnpdf;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet query_set;
    PreparedStatement pst;
    List<Joueur> ListeJoueurs;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("mil", "Att", "Des", "G");
        positionComoBox.setItems(list);
        btnpdf.setOnAction((e)->{
            try {
                pdfs(RESULT);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
       

    }
    
 

    public void loadData(List<Joueur> j) {
        ServiceJoueur SJ = new ServiceJoueur();
        nom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomJoueur"));
        prenom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenomJoueur"));
        position.setCellValueFactory(new PropertyValueFactory<Joueur, String>("position"));
        

        logo.setCellValueFactory(new PropertyValueFactory<Joueur, ImageView>("logoimage"));

        prix.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("prixJoueur"));
        equipe.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomEquipe"));

        ObservableList<Joueur> res;

        res = FXCollections.observableArrayList(j);
        tabAffiche.setItems(res);
  

    }

    @FXML
    private void AjouterJoueur(ActionEvent event) {
        Joueur e = new Joueur();        if(validateFields()){
        e.setNomJoueur(tfNom.getText());
        e.setPrenomJoueur(tfPrenom.getText());

        e.setPosition(positionComoBox.getSelectionModel().getSelectedItem().toString());
        e.setScoreJoueur(Integer.parseInt(tfScore.getText()));
        e.setLogoJoueur(tfLogo.getText());
        e.setPrixJoueur(Integer.parseInt(tfPrix.getText()));

//
        int i = SE.getByName(combo.getValue()).getId();
        e.setIdG(i);

        SJ.addJoueur(e);
        }
    }
       private boolean validateFields() {
        if (tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() | tfScore.getText().isEmpty()
                | tfLogo.getText().isEmpty() | tfPrix.getText().isEmpty()  ) {
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

    public void loadComboBox(String nom) {
        combo.setItems(FXCollections.observableArrayList(nom));
        combo.getSelectionModel().selectFirst();
    }

    @FXML
    private void DeletePlayer(ActionEvent event) {
        Joueur e = tabAffiche.getSelectionModel().getSelectedItem();
        SJ.deleteJoueur(e);
        loadData(ListeJoueurs);

    }

    @FXML
    private void Back(ActionEvent event) {

        try {
            btnBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEquipe.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Modifier(ActionEvent event) {
        Joueur jr = tabAffiche.getSelectionModel().getSelectedItem();

        String a = SE.getById(jr.getIdG()).getNom();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifJoueurEquipe.fxml"));

        try {

            loader.load();

        } catch (IOException ex) {
         //   Logger.getLogger(ListeEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModifJoueurEquipeController modifJoueur = loader.getController();
        modifJoueur.ComboBoxPosition(jr.getPosition());
        modifJoueur.ComboBoxEquipe(a);

        modifJoueur.setTextField(jr.getIdJoueur(), jr.getNomJoueur(), jr.getPrenomJoueur(), jr.getScoreJoueur(), jr.getLogoJoueur(), jr.getPrixJoueur());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void pdfs (String filename) throws Exception{
        Document my_pdf_report = new Document(PageSize.LETTER);
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(filename));
             my_pdf_report.open();
               PdfPTable my_report_table = new PdfPTable(6);
                     PdfPCell table_cell;
                    table_cell=new PdfPCell(new Phrase("Nom joueur"));
                    my_report_table.addCell(table_cell);
                    table_cell=new PdfPCell(new Phrase("Prenom Joueur"));
                    my_report_table.addCell(table_cell);
                    table_cell=new PdfPCell(new Phrase("Position de joueur"));
                    my_report_table.addCell(table_cell);
                    table_cell=new PdfPCell(new Phrase("Score Joueur"));
                    my_report_table.addCell(table_cell);
                    table_cell=new PdfPCell(new Phrase("Prix de joueur"));
                    my_report_table.addCell(table_cell);
     try{
     String query = "SELECT * from Joueur";
            pst = cnx.prepareStatement(query);
            query_set = pst.executeQuery();
            while (query_set.next()) {  
                                    String nomjoueur = query_set.getString("nom_joueur");
                                    table_cell=new PdfPCell(new Phrase(nomjoueur));
                                    my_report_table.addCell(table_cell);
                                    String prenomjoueur = query_set.getString("prenom_joueur");
                                    table_cell=new PdfPCell(new Phrase(nomjoueur));
                                    my_report_table.addCell(table_cell);
                                    String pos=query_set.getString("position");
                                    table_cell=new PdfPCell(new Phrase(pos));
                                    my_report_table.addCell(table_cell);
                                    int scorejoueur=query_set.getInt("score_joueur");
                                    String s =String.valueOf(scorejoueur);
                                    table_cell=new PdfPCell(new Phrase(s));
                                    my_report_table.addCell(table_cell);
                                    int prixjoueur=query_set.getInt("prix_joueur");
                                    String s1 =String.valueOf(prixjoueur);
                                    table_cell=new PdfPCell(new Phrase(s1));
                                    my_report_table.addCell(table_cell);
            }
            my_pdf_report.add(my_report_table);
   my_pdf_report.close();
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
     }
    }

   

    
}
