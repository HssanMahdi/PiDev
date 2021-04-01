/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entites.Commande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class ServiceCommande {
    
     public void add_order(int activ_user_id,String adr_livraison, String country,int post_code, double total_price ) {
        Statement st;
      
        Date date;
        String status="payment no valide";
        date = new Date();
        String product_list ="";
        product_list=get_products_list_from_cart(activ_user_id);
        try {
            st = MyConnection.getInstance().getCnx().createStatement();
            String rec = " INSERT INTO `commande`(`id_User`, `adr_livraison`, `country`, `post_code`, `date`,`total_price`, `status`, `liste_produit`) VALUES ('" + activ_user_id+ "','" + adr_livraison + "','" + country + "','" + post_code+ "','" + date+ "','" + total_price+ "','" +status+ "','"+ product_list +"')";
            st.executeUpdate(rec);
            System.err.println("add successful");
            ServicePanier cs=new ServicePanier() ;
            cs.vider_panier(activ_user_id);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public void update_order(String adr_livraison, String country,int post_code, int idCommande, int idUser) {
        Statement st;
      
        Date date;
       
        date = new Date();
    
      
        try {
            st = MyConnection.getInstance().getCnx().createStatement();
            String rec = " UPDATE commande SET adr_livraison = '" + adr_livraison + "' , country = '" + country + "' ,  post_code = '" + post_code+ "' ,  date = '" + date+ "'  WHERE id_cmd  = '" + idCommande+ "' AND id_User=  '" + idUser+ "' ";
            //UPDATE panier SET quantite =?  WHERE id_User =? AND id_produit =? 
            st.executeUpdate(rec);
            System.err.println("update successful");
          
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public String get_products_list_from_cart(int user_id) 
    { 
        String l=""; 
       final String sql = "SELECT panier.id_produit, produit.nom_produit, panier.quantite FROM produit INNER JOIN panier ON produit.id_produit=panier.id_produit WHERE panier.id_User = '" + user_id + "'";
try (PreparedStatement stm = MyConnection.getInstance().getCnx().prepareStatement(sql);
        ResultSet rs = stm.executeQuery()) {
    while (rs.next()) {
        int product_list = rs.getInt("panier.id_produit");
       String product_name = rs.getString("produit.nom_produit");
        int product_quantite=rs.getInt("panier.quantite");
        
        l=l+"Product Id : "+product_list+" Product Name: "+product_name+" Quantity: "+product_quantite+"\n";
    }
} catch (SQLException e) {
    e.printStackTrace(System.out);
} 
     return l;
    }
    
      public void change_order_status(int user_id,String order_id,String new_status) {
        PreparedStatement pt;
        try {
            pt = MyConnection.getInstance().getCnx().prepareStatement("UPDATE commande SET status =?  WHERE id_User =? AND id_cmd =? ");
            pt.setString(1, new_status);
             pt.setInt(2, user_id);
              pt.setString(3, order_id);
            pt.executeUpdate();
            System.out.println("status changed to "+new_status);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      public ObservableList<Commande> getInitialTableData_Orders_History(int user_id) {
        String sql = "SELECT * FROM commande WHERE id_User = '" + user_id + "'";
        ObservableList<Commande> data = FXCollections.observableArrayList();
        try {
            PreparedStatement s =  MyConnection.getInstance().getCnx().prepareStatement(sql);
            s.executeQuery(sql);
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                
               Commande o=new Commande(res.getInt(1),user_id, res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getString(8), res.getString(9));
                
                data.add(o);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in Building Data"+ex);
        }
        return data;
    }
      
         public ObservableList<Commande> getInitialTableData_Orders_History_For_Admin() {
        String sql = "SELECT * FROM commande";
        ObservableList<Commande> data = FXCollections.observableArrayList();
        try {
            PreparedStatement s =  MyConnection.getInstance().getCnx().prepareStatement(sql);
            s.executeQuery(sql);
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                
               Commande o=new Commande(res.getInt(1),res.getInt(2),res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getString(8), res.getString(9));
                
                data.add(o);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in Building Data"+ex);
        }
        return data;
    }
      
     public void delete_order(int idCommande, int activ_user_id) {
        PreparedStatement pt;
        try {
            pt = MyConnection.getInstance().getCnx().prepareStatement("delete from commande where id_cmd=?  AND id_User=? ");
            pt.setInt(1, idCommande);
            pt.setInt(2, activ_user_id);
            pt.executeUpdate();
            System.out.println("order deleted ");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
      
//      public void mailing_order(int activ_user_id)
//    {
//         final String username = "game.division10@gmail.com";
//                final String password =  "gamedivision10";
//                
//                
//                Properties props = new Properties();
//                props.put("mail.smtp.starttls.enable", "true");
//                props.put("mail.smtp.auth", "true");
//                props.put("mail.smtp.host", "smtp.gmail.com");
//                props.put("mail.smtp.port", "587");
//                
//                Session session = Session.getInstance(props,
//                        new javax.mail.Authenticator() {
//                            @Override
//                            protected PasswordAuthentication getPasswordAuthentication() {
//                                return new PasswordAuthentication(username, password);
//                            }
//                        });
//                 try {
//              Statement stmp =MyConnection.getInstance().getCnx().createStatement();
//                     String req2 = "SELECT `User_Email` from users where User_id='"+activ_user_id+"'";
//        ResultSet resultat = stmp.executeQuery(req2);
//         while(resultat.next()){
//           String to= resultat.getString("User_Email");
//           
//           
//            try {
//                    
//                    Message message = new MimeMessage(session);
//                    message.setFrom(new InternetAddress("from-email@gmail.com"));
//                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//                    message.setSubject("Order Confirmed");
//                    message.setText("\n"+"Hi\n"+"Your order is being prepared\n"+"Thank you for using Game Division   ");
//                    Transport.send(message);
//                    JOptionPane.showMessageDialog(null, "Check your email");
//                } catch (MessagingException ex) {
//                    JOptionPane.showMessageDialog(null, "Oops something went wrong" + "\n" + ex.getMessage());
//                }
//         }
//           
//         } catch (SQLException ex) {
//           //  Logger.getLogger(Specialiste.class.getName()).log(Level.SEVERE, null, ex);
//         }
//
//         
//       }

//         
//           public void pdfs(int activ_user_id) 
//          throws Exception{
//        try {
//           Class.forName("com.mysql.jdbc.Driver");
//               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamedivision", "root", "");
//  Statement stmt = MyConnection.getInstance().getCnx().createStatement();
//                    /* Define the SQL query */
//                    ResultSet query_set = stmt.executeQuery("SELECT *From orders WHERE orders.user_id= '" + activ_user_id+ "'");
//                    /* Step-2: Initialize PDF documents - logical objects */
//                    Document my_pdf_report = new Document();
//                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("OrdersHistory.pdf"));
//                    my_pdf_report.open();            
//                    //we have four columns in our table
//                    PdfPTable my_report_table = new PdfPTable(8);
//                    //create a cell object
//                    PdfPCell table_cell;
//                    
//                    while (query_set.next()) {                
//                                    String Order_id = query_set.getString("order_id");
//                                    table_cell=new PdfPCell(new Phrase(Order_id));
//                                    my_report_table.addCell(table_cell);
//                                    String Address=query_set.getString("adr_livraison");
//                                    table_cell=new PdfPCell(new Phrase(Address));
//                                    my_report_table.addCell(table_cell);
//                                    String country=query_set.getString("country");
//                                    table_cell=new PdfPCell(new Phrase(country));
//                                    my_report_table.addCell(table_cell);
//                                    int post_code=query_set.getInt("post_code");
//                                   
//                                    table_cell=new PdfPCell(new Phrase(""+post_code));
//                                    my_report_table.addCell(table_cell);
//                                    String date=query_set.getString("date");
//                                     String dat=""+date;
//                                     table_cell=new PdfPCell(new Phrase(dat));
//                                    my_report_table.addCell(table_cell);
//                                    
//                                    double total_price=query_set.getInt("total_price");
//                                    String d=""+total_price;
//                                    table_cell=new PdfPCell(new Phrase(d));
//                                    my_report_table.addCell(table_cell);
//                                    String status=query_set.getString("status");
//                                    table_cell=new PdfPCell(new Phrase(status));
//                                    my_report_table.addCell(table_cell);
//                                    String products=query_set.getString("liste_produit");
//                                    table_cell=new PdfPCell(new Phrase(products));
//                                    my_report_table.addCell(table_cell);
//                                    //my_pdf_report.add(my_report_table);    
//                                    }
//                    /* Attach report table to PDF */
//                    
//                  my_pdf_report.add(my_report_table);                       
//                    my_pdf_report.close();
//
//                    /* Close all DB related objects */
//                    query_set.close();
//                    stmt.close(); 
//                    con.close();               
//
//
//
//    } catch (FileNotFoundException | DocumentException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//    }
//         TODO Auto-generated catch block
//        
//
//    
//    
//       }
    
}
