/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import entites.Adherent;
import entites.Joueur;
import services.FormationCRUD;
import tools.MyConnection;


/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AfficherFormationController implements Initializable {



    private Button btnvendre;
    @FXML
    private Label lb;
    FormationCRUD fcd = new FormationCRUD();
    Adherent u = new Adherent(2, 1, "Mahdi", "Mahdi", "mahdi.hssan@esprit.tn", "Mahdi", 10000, 0);
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnexel;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;
    int ind;
    @FXML
    private ComboBox<String> sortcombo;
    @FXML
    private ComboBox<String> formcombo;
    @FXML
    private ImageView imgform;
    @FXML
    private VBox ContainerForm;
    @FXML
    private ScrollPane co;
    @FXML
    private TextField search;
    @FXML
    private Button btn_equipe;
    @FXML
    private Button btn_joueur;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_stotr;
    @FXML
    private Button btn_highls;
    @FXML
    private FontAwesomeIconView grp_btn;
    @FXML
    private FontAwesomeIconView form_btn;
    @FXML
    private FontAwesomeIconView event_btn;
    @FXML
    private Label lbGroupe;
    @FXML
    private Label lbForm;
    @FXML
    private Label lbMatch;
    @FXML
    private Label lbdecnx;
    @FXML
    private Label lbGroupe1111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TextFields.bindAutoCompletion(search,fcd.displayJoueurdeFormation(u).stream().map(j->j.getNomJoueur()).collect(toList()));
        afficher(fcd.displayJoueurdeFormation(u));
        ExportPdf exp=new ExportPdf();
        imgset();

        btnpdf.setOnAction((e) -> {
            try {
                exp.pdfsM("Formation.pdf",u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export PDF");
            alert.setHeaderText(null);
            alert.setContentText("Formation exportée dans un fichier PDF.");
            alert.showAndWait();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        ObservableList<String> options = FXCollections.observableArrayList(
                "Trier",
                "Prix croissant",
                "Prix décroissant"
        );
        sortcombo.getItems().addAll(options);
      
        ObservableList<String> options1 = FXCollections.observableArrayList(
                "4-4-2",
                "4-3-3",
                "3-5-2",
                "5-4-1",
                "5-3-2"
        );
        formcombo.getItems().addAll(options1);
//        probform(4, 4, 2);
        formcombo.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("4-3-3")) {
                File file = new File("C:/Users/Mahdi/Desktop/4-3-3.png");
                Image image = new Image(file.toURI().toString(), 150, 200, true, true);
                imgform.setImage(image);
                imgform.setFitWidth(150);
                imgform.setFitHeight(200);
                probform(4, 3, 3);
            } else if (newValue.equalsIgnoreCase("4-4-2")) {
                File file = new File("C:/Users/Mahdi/Desktop/4-4-2.png");
                Image image = new Image(file.toURI().toString(), 150, 200, true, true);
                imgform.setImage(image);
                imgform.setFitWidth(150);
                imgform.setFitHeight(200);
                probform(4, 4, 2);
            } else if (newValue.equalsIgnoreCase("3-5-2")) {
                File file = new File("C:/Users/Mahdi/Desktop/3-5-2.png");
                Image image = new Image(file.toURI().toString(), 150, 200, true, true);
                imgform.setImage(image);
                imgform.setFitWidth(150);
                imgform.setFitHeight(200);
                probform(3, 5, 2);
            } else if (newValue.equalsIgnoreCase("5-4-1")) {
                File file = new File("C:/Users/Mahdi/Desktop/5-4-1.png");
                Image image = new Image(file.toURI().toString(), 150, 200, true, true);
                imgform.setImage(image);
                imgform.setFitWidth(150);
                imgform.setFitHeight(200);
                probform(5, 4, 1);
            } else if (newValue.equalsIgnoreCase("5-3-2")) {
                File file = new File("C:/Users/Mahdi/Desktop/5-3-2.png");
                Image image = new Image(file.toURI().toString(), 150, 200, true, true);
                imgform.setImage(image);
                imgform.setFitWidth(150);
                imgform.setFitHeight(200);
                probform(5, 3, 2);
            }
        });

    }

    void afficher(List<Joueur> lsJoueur) {
        
        ContainerForm.getChildren().clear();  
        for (int i = 0; i < lsJoueur.size(); i++) {
            Joueur jr = lsJoueur.get(i);

            Pane single = new Pane();
            single.setStyle("-fx-border-color:#dacece; " + "  -fx-border-width: 0 0 1 0; ");
            single.setPrefHeight(170);
            single.setPrefWidth(549);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(23);
            logo.setLayoutY(18);
            logo.setFitWidth(100);
            logo.setFitHeight(100);
            logo.setPreserveRatio(false);
            Image image;
            String url = "file:"+jr.getLogoJoueur();
        
           
                image = new Image(url);
                logo.setImage(image);
         

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(166);
            name.setLayoutY(20);
            name.setStyle("-fx-font : 18pt \"Times New Roman \";");
            name.setText(jr.getNomJoueur() + " " + jr.getPrenomJoueur());

            Label position = new Label();
            position.setPrefHeight(27);
            position.setPrefWidth(USE_COMPUTED_SIZE);
            position.setLayoutX(183);
            position.setLayoutY(65);
            position.setStyle("-fx-font-size :15");
            position.setText("Position " + jr.getPosition());
            
             FontAwesomeIconView marker = new FontAwesomeIconView(FontAwesomeIcon.ARROWS);
            marker.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:13px;"
                    + "-fx-fill:black;"
            );
            marker.setLayoutX(163);
            marker.setLayoutY(82);

            Button btnvend = new Button("Vendre");
            btnvend.setPrefHeight(40);
            btnvend.setPrefWidth(USE_COMPUTED_SIZE);
            btnvend.setLayoutX(540);
            btnvend.setLayoutY(40);
            btnvend.getStylesheets().add(getClass().getResource("/CSS/ButtonStyles.css").toExternalForm());
            btnvend.setVisible(true);
            btnvend.setOnAction((e) -> {
                fcd.supprimerJoueurduFormation(u,jr);
                ContainerForm.getChildren().clear();
                afficher(fcd.displayJoueurdeFormation(u));

            });

//            ImageView markScore = new ImageView();
            FontAwesomeIconView markScore = new FontAwesomeIconView(FontAwesomeIcon.DOLLAR);
            markScore.setStyle(
                    " -fx-cursor: hand ;"
                    + "-glyph-size:16px;"
                    + "-fx-fill: #c00909;"
            );
            markScore.setLayoutX(166);
            markScore.setLayoutY(121);

//            markScore.setPreserveRatio(false);
//            Image markScr = new Image("uploads/price.png");
//            markScore.setImage(markScr);
            Label prix = new Label();
            prix.setPrefHeight(27);
            prix.setPrefWidth(USE_COMPUTED_SIZE);
            prix.setLayoutX(183);
            prix.setLayoutY(103);
            prix.setStyle("-fx-font-size :15");
            prix.setText(jr.getPrixJoueur() + " TND");

            Label eq = new Label();
            eq.setPrefHeight(27);
            eq.setPrefWidth(USE_COMPUTED_SIZE);
            eq.setLayoutX(183);
            eq.setLayoutY(132);
            eq.setStyle("-fx-font: bold  13pt \"Times New Roman\";");
            eq.setText(jr.getNomEquipe());

         

            single.getChildren().add(btnvend);
            single.getChildren().add(marker);
            single.getChildren().add(name);
            single.getChildren().add(position);
            single.getChildren().add(logo);
            single.getChildren().add(markScore);
            single.getChildren().add(prix);

            single.getChildren().add(eq);
          

            ContainerForm.getChildren().add(single);

        }
    }

    private void moved(MouseEvent event) {
        lb.setText("Selectionner un joueur \n pour le supprimer");
    }

    private void exited(MouseEvent event) {
        lb.setText("");
    }

    

    @FXML
    private void ExportExcel(ActionEvent event) {
        ExportExcel exp=new ExportExcel();
        exp.exelm(u);

    }


    @FXML
    private void sortcombo(ActionEvent event) {
        
             if("Prix croissant".equals(sortcombo.getSelectionModel().getSelectedItem())){
        List<Joueur> lst =fcd.displayJoueurdeFormation(u).stream().sorted((j1,j2)->(int)j1.getPrixJoueur()-j2.getPrixJoueur()).collect(toList());
        ContainerForm.getChildren().clear();
        afficher(lst);
               }else if(  "Prix décroissant".equals(sortcombo.getSelectionModel().getSelectedItem())){
        List<Joueur> lst =fcd.displayJoueurdeFormation(u).stream().sorted((j1,j2)->(int)j2.getPrixJoueur()-j1.getPrixJoueur()).collect(toList());
        afficher(lst);
               }else{afficher(fcd.displayJoueurdeFormation(u));}
    }
    public void imgset() {

        File file = new File("C:/Users/Mahdi/Desktop/4-4-2.png");
        Image image = new Image(file.toURI().toString(), 150, 200, true, true);
        imgform.setImage(image);
        imgform.setFitWidth(150);
        imgform.setFitHeight(200);
    }

    public void probform(int def, int mil, int att) {
        List<Joueur> l = new ArrayList<>();
        l = fcd.displayJoueurdeFormation(u);
        int pmil = 0;
        int patt = 0;
        int pdef = 0;
        int g = 0;
        for (Joueur j1 : l) {
            if (j1.getPosition().equalsIgnoreCase("ATT")) {
                patt++;
            } else if (j1.getPosition().equalsIgnoreCase("MIL")) {
                pmil++;
            } else if (j1.getPosition().equalsIgnoreCase("G")) {
                g++;
            } else {
                pdef++;
            }
        }

        int pmilm = mil - pmil;
        int pattm = att - patt;
        int pdefm = def - pdef;
        int gm = 1 - g;
        if (pmilm < 0) {
            pmilm = 0;
        };
        if (pattm < 0) {
            pattm = 0;
        };
        if (pdefm < 0) {
            pdefm = 0;
        };
        if ((pmilm != 0) || (pattm != 0) || (pdefm != 0) || (gm != 0)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Probleme Formation");
            alert.setHeaderText("Probleme Formation");
            alert.setContentText("Votre formation est " + def + "-" + mil + "-" + att + " il vous manque : \n" + pattm + " attaquant(s) \n " + pmilm + " milieu(s) \n " + pdefm + " deffenseur(s) \n " + gm + " gardien.");
            alert.showAndWait();
        }
    }

//    private void search(ActionEvent event) {
//        if(!search.getText().isEmpty()){
//        List<Joueur> lst =fcd.displayJoueurdeFormation(u).stream().filter(j1->j1.getNomJoueur().equals(search.getText())).collect(toList());
//        afficher(lst);
//        }else{
//        afficher(fcd.displayJoueurdeFormation(u));
//        }
//        
//    }

    @FXML
    private void search(KeyEvent event) {
        if(!search.getText().isEmpty()){
        List<Joueur> lst =fcd.displayJoueurdeFormation(u).stream().filter(j1->j1.getNomJoueur().equals(search.getText())).collect(toList());
        afficher(lst);
        }else{
        afficher(fcd.displayJoueurdeFormation(u));
        }
    }

 
    @FXML
    private void interfaceEquipe(ActionEvent event) {
    }

    @FXML
    private void interfaceJoueur(ActionEvent event) {
    }

    @FXML
    private void interfaceStat(ActionEvent event) {
    }

    @FXML
    private void interfaceHighls(ActionEvent event) {
    }

    @FXML
    private void grpExist(MouseEvent event) {
        lbGroupe.setVisible(false);
    }

    @FXML
    private void grpRntred(MouseEvent event) {
        lbGroupe.setVisible(true);
    }

    @FXML
    private void GroupeInt(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AfficherFormationController.this.getClass().getResource("DisplaylesGroupedeAdherent.fxml"));
        grp_btn.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void formExi(MouseEvent event) {
        lbForm.setVisible(false);
    }

    @FXML
    private void formEnt(MouseEvent event) {
        lbForm.setVisible(true);
    }

    @FXML
    private void formationInt(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AfficherFormationController.this.getClass().getResource("AfficherFormation.fxml"));
        form_btn.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void evnExit(MouseEvent event) {
        lbMatch.setVisible(false);
    }

    @FXML
    private void evnEntr(MouseEvent event) {
        lbMatch.setVisible(true);
    }

    @FXML
    private void EventAct(MouseEvent event) {
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AfficherFormationController.this.getClass().getResource("EventforUser.fxml"));
        event_btn.getScene().getWindow().hide();
        try {

            loader.load();

        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void actionExit(MouseEvent event) {
        lbGroupe1111.setVisible(false);
    }

    @FXML
    private void actionentred(MouseEvent event) {
        lbGroupe1111.setVisible(true);
    }

    @FXML
    private void logExit(MouseEvent event) {
        lbdecnx.setVisible(false);
    }

    @FXML
    private void logEntr(MouseEvent event) {
        lbdecnx.setVisible(true);
    }

}
