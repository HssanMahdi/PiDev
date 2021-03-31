/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
/**
 *
 * @author Mahdi
 */
public class ExportPdf {
    
    Connection cnx = MyConnection.getInstance().getCnx();
ResultSet query_set;
PreparedStatement pst;

public ExportPdf() {
}

public void pdfsM(String filename,Adherent u) throws Exception {
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

public void pdfs(String filename) {

try {
            Document my_pdf_report = new Document(PageSize.LETTER);

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("PlayerDetails.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(5);
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("Nom joueur"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Prenom Joueur"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Position de joueur"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Score Joueur"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Prix de joueur"));
            my_report_table.addCell(table_cell);
            try {
                String query = "SELECT * from Joueur";
                pst = cnx.prepareStatement(query);
                query_set = pst.executeQuery();
                while (query_set.next()) {
                    String nomjoueur = query_set.getString("nom_joueur");
                    table_cell = new PdfPCell(new Phrase(nomjoueur));
                    my_report_table.addCell(table_cell);
                    String prenomjoueur = query_set.getString("prenom_joueur");
                    table_cell = new PdfPCell(new Phrase(prenomjoueur));
                    my_report_table.addCell(table_cell);
                    String pos = query_set.getString("position");
                    table_cell = new PdfPCell(new Phrase(pos));
                    my_report_table.addCell(table_cell);
                    int scorejoueur = query_set.getInt("score_joueur");
                    String s = String.valueOf(scorejoueur);
                    table_cell = new PdfPCell(new Phrase(s));
                    my_report_table.addCell(table_cell);
                    int prixjoueur = query_set.getInt("prix_joueur");
                    String s1 = String.valueOf(prixjoueur);
                    table_cell = new PdfPCell(new Phrase(s1));
                    my_report_table.addCell(table_cell);
                }
                my_pdf_report.add(my_report_table);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inforamtion dialog");
                alert.setHeaderText(null);
                alert.setContentText("Player Details Exported in PDF Sheet");
                alert.showAndWait();
                my_pdf_report.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(ExportPdf.class.getName()).log(Level.SEVERE, null, ex);
        }}
    public void pdfsG() {
        try {
            Document my_pdf_report = new Document(PageSize.LETTER);

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("AdminSytemeDetails.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(5);
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("Nom Admin"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Email"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Type"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Score"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Solde"));
            my_report_table.addCell(table_cell);
            try {
                String query = "SELECT * from user where type_user='adS'";
                pst = cnx.prepareStatement(query);
                query_set = pst.executeQuery();
                while (query_set.next()) {
                    String nomuser = query_set.getString("nom_user");
                    table_cell = new PdfPCell(new Phrase(nomuser));
                    my_report_table.addCell(table_cell);
                    String emailUser = query_set.getString("email");
                    table_cell = new PdfPCell(new Phrase(emailUser));
                    my_report_table.addCell(table_cell);
                    String type_user = query_set.getString("type_user");
                    table_cell = new PdfPCell(new Phrase(type_user));
                    my_report_table.addCell(table_cell);
                    int scoreuser = query_set.getInt("score_user");
                    String s = String.valueOf(scoreuser);
                    table_cell = new PdfPCell(new Phrase(s));
                    my_report_table.addCell(table_cell);
                    int solde = query_set.getInt("solde");
                    String s1 = String.valueOf(solde);
                    table_cell = new PdfPCell(new Phrase(s1));
                    my_report_table.addCell(table_cell);
                }
                my_pdf_report.add(my_report_table);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inforamtion dialog");
                alert.setHeaderText(null);
                alert.setContentText("Admin Details Exported in PDF Sheet");
                alert.showAndWait();
                my_pdf_report.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println(ex);
        }
    }

}
