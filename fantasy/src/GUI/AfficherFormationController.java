/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import testpi.newpackage.Adherent;
import testpi.newpackage.FormationCRUD;
import testpi.newpackage.Joueur;
import testpi.newpackage.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AfficherFormationController implements Initializable {

    public static final String RESULT = "C:/Users/Mahdi/Desktop/MonFormation.pdf";
    @FXML
    private TableView<Joueur> tab;
    @FXML
    private TableColumn<Joueur, String> joueur;
    @FXML
    private TableColumn<Joueur, String> position;
    @FXML
    private Button btnvendre;
    @FXML
    private Label lb;
    FormationCRUD fcd = new FormationCRUD();
    Adherent u = new Adherent(2, 1, "Mahdi", "Mahdi", "mahdi.hssan@esprit.tn", "Mahdi", 10000, 0);
    @FXML
    private TableColumn<Joueur, Integer> prix;
    @FXML
    private Label lbusr;
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnexel;
    Connection cnx = MyConnection.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pst;
    int ind;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        joueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nomJoueur"));
        position.setCellValueFactory(new PropertyValueFactory<Joueur, String>("position"));
        prix.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("prixJoueur"));
        ObservableList<Joueur> res;
        res = FXCollections.observableArrayList(fcd.displayJoueurdeFormation(u));
        tab.setItems(res);

        btnvendre.setOnAction((e) -> {
            Joueur j = tab.getSelectionModel().getSelectedItem();
            fcd.supprimerJoueurduFormation(u, j);
            initialize(url, rb);
        });

        btnpdf.setOnAction((e) -> {
            try {
                pdfs(RESULT);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    @FXML
    private void moved(MouseEvent event) {
        lb.setText("Selectionner un joueur \n pour le supprimer");
    }

    @FXML
    private void exited(MouseEvent event) {
        lb.setText("");
    }

    private void pdfs(String filename) throws Exception {
        ResultSet rs1, query_set;
        Document my_pdf_report = new Document(PageSize.LETTER);
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(filename));
        my_pdf_report.open();
        PdfPTable my_report_table = new PdfPTable(3);
        PdfPCell table_cell;
        table_cell = new PdfPCell(new Phrase("Nom joueur"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Position de joueur"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Prix de joueur"));
        my_report_table.addCell(table_cell);
        try {

            String requete = "SELECT id_formation FROM formation WHERE id_user=" + u.getId_user() + ";";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
//               System.out.println(rs.getInt("id_user"));
                String requete1 = "SELECT id_joueur FROM formjoueur WHERE id_formation=" + rs.getInt("id_formation") + ";";
                Statement st1 = MyConnection.getInstance().getCnx()
                        .createStatement();
                rs1 = st1.executeQuery(requete1);

                while (rs1.next()) {
                    String requete2 = "SELECT * FROM joueur WHERE id_joueur=" + rs1.getInt("id_joueur") + ";";
                    Statement st2 = MyConnection.getInstance().getCnx()
                            .createStatement();
                    query_set = st2.executeQuery(requete2);

                    //  /* Step-2: Initialize PDF documents - logical objects */          
//                    //we have four columns in our table
                    while (query_set.next()) {
                        String nomjoueur = query_set.getString("nom_joueur");
                        table_cell = new PdfPCell(new Phrase(nomjoueur));
                        my_report_table.addCell(table_cell);
                        String pos = query_set.getString("position");
                        table_cell = new PdfPCell(new Phrase(pos));
                        my_report_table.addCell(table_cell);
                        int prixjoueur = query_set.getInt("prix_joueur");
                        String s = String.valueOf(prixjoueur);
                        table_cell = new PdfPCell(new Phrase(s));
                        my_report_table.addCell(table_cell);

                        /* Attach report table to PDF */
                    }
                }
            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
        } catch (SQLException ex) {
            System.out.println("rr");
//    } catch (FileNotFoundException | DocumentException e) {
//
//    System.out.println(e.getMessage());
//    }

        }
    }

    @FXML
    private void ExportExcel(ActionEvent event) {
        try {
            String query = "SELECT * from Joueur";
            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Formation Détails");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nom joueur");
            header.createCell(1).setCellValue("Position");
            header.createCell(2).setCellValue("Prix de joueur");

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            sheet.setColumnWidth(3, 256 * 25);
            sheet.setZoom(150);

            int index = 1;
            ResultSet rs1, rs2;
            try {
                String requete = "SELECT id_formation FROM formation WHERE id_user=" + u.getId_user() + ";";
                Statement st = MyConnection.getInstance().getCnx()
                        .createStatement();
                ResultSet rs = st.executeQuery(requete);
                while (rs.next()) {
//               System.out.println(rs.getInt("id_user"));
                    String requete1 = "SELECT id_joueur FROM formjoueur WHERE id_formation=" + rs.getInt("id_formation") + ";";
                    Statement st1 = MyConnection.getInstance().getCnx()
                            .createStatement();
                    rs1 = st1.executeQuery(requete1);
                    while (rs1.next()) {
                        String requete2 = "SELECT * FROM joueur WHERE id_joueur=" + rs1.getInt("id_joueur") + ";";
                        Statement st2 = MyConnection.getInstance().getCnx()
                                .createStatement();
                        rs2 = st2.executeQuery(requete2);
                        while (rs2.next()) {

                            Joueur j = new Joueur();
                            XSSFRow row = sheet.createRow(index);
                            row.createCell(0).setCellValue(rs2.getString("nom_joueur"));
                            row.createCell(1).setCellValue(rs2.getString("position"));
                            row.createCell(2).setCellValue(rs2.getInt("prix_joueur"));

                            index++;
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            FileOutputStream fileOut = new FileOutputStream("FormationDetails.xlsx");
            
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inforamtion dialog");
            alert.setHeaderText(null);
            alert.setContentText("Formation Details Exported in Excel Sheet");
            alert.showAndWait();

            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
            System.out.println("Erreur");
        } catch (IOException ex) {
            System.out.println("Erreur");
        }

    }

}
