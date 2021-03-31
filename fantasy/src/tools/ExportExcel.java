/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mahdi
 */
public class ExportExcel {
    Connection cnx = MyConnection.getInstance().getCnx();
ResultSet rs;
PreparedStatement pst;
private static int ind = 0;

public void exelm(Adherent u){
    try {
            String query = "SELECT * from Joueur";
            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Formation");
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

public void exportFile(){
     try {
            String query = "SELECT * from Joueur";
            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Player Détails");
            XSSFRow header = sheet.createRow(0);
        
            header.createCell(0).setCellValue("Nom Joueur");
            header.createCell(1).setCellValue("Prenom Joueur");
            header.createCell(2).setCellValue("Position");
            header.createCell(3).setCellValue("Score Joueur");
            header.createCell(4).setCellValue("Prix Joueur");
            

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
         
            
            // sheet.setColumnWidth(3, 256 * 25);
            sheet.setZoom(150);

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
          
                row.createCell(0).setCellValue(rs.getString("nom_joueur"));
                row.createCell(1).setCellValue(rs.getString("prenom_joueur"));
                row.createCell(2).setCellValue(rs.getString("position"));
                row.createCell(3).setCellValue(rs.getInt("score_joueur"));
                row.createCell(4).setCellValue(rs.getInt("prix_joueur"));
                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("PlayerDetails.xlsx");
            wb.write(fileOut);

            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inforamtion dialog");
            alert.setHeaderText(null);
            alert.setContentText("Player Details Exported in Excel Sheet");
            alert.showAndWait();
          
            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
public void exportxL() {
        try {
            String query = "SELECT * from user where type_user='MF'";
            pst = cnx.prepareStatement(query);
            rs = pst.executeQuery();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Les Managers Football");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nom d'utilisateur");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Type");
            header.createCell(3).setCellValue("Score");
            header.createCell(4).setCellValue("Solde");

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setColumnWidth(1, 256 * 25);
            sheet.setZoom(150);

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("nom_user"));
                row.createCell(1).setCellValue(rs.getString("email"));
                row.createCell(2).setCellValue(rs.getString("type_user"));
                row.createCell(3).setCellValue(rs.getInt("score_user"));
                row.createCell(4).setCellValue(rs.getInt("solde"));

                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("ManagersFootball.xlsx");
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inforamtion dialog");
            alert.setHeaderText(null);
            alert.setContentText("Managers Football Exported in Excel Sheet");
            alert.showAndWait();

            pst.close();
            rs.close();

        } catch (SQLException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
}
