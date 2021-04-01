/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.MyConnection;

/**
 *
 * @author mhamdi iheb
 */
public class ServicePanier {
    
        public void addProduitPanier(int product_id, int quantite, int user_id) {
        Statement st;

        try {
            st = MyConnection.getInstance().getCnx().createStatement();
            String rec2 = "SELECT id_produit FROM Panier WHERE id_produit = '" + product_id + "' AND + id_User = '" + user_id + "'";

            ResultSet rs = st.executeQuery(rec2);
            if (rs.next()) {
                System.err.println("product exists in cart");
            } else {
                try {
                    st = MyConnection.getInstance().getCnx().createStatement();
                    String rec = " INSERT INTO `Panier`(`id_produit`, `quantite`, `id_User`) VALUES ('" + product_id + "','" + quantite + "','" + user_id + "')";
                    st.executeUpdate(rec);
                    System.err.println("add successful");
                } catch (SQLException ex) {
                    Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete_product_from_panier(int product_id, int activ_user_id) {
        PreparedStatement pt;
        try {
            pt = MyConnection.getInstance().getCnx().prepareStatement("delete from panier where id_produit=?  AND id_User=? ");
            pt.setInt(1, product_id);
            pt.setInt(2, activ_user_id);
            pt.executeUpdate();
            System.out.println("product deleted from cart");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void vider_panier(int user_id) {
        PreparedStatement pt;
        try {
            pt = MyConnection.getInstance().getCnx().prepareStatement("delete from panier where id_User=? ");
            pt.setInt(1, user_id);
            pt.executeUpdate();
            System.out.println("cart vider");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update_panier_product_quantity(int user_id, int product_id, int quantity) {
        PreparedStatement pt;
        try {
            pt = MyConnection.getInstance().getCnx().prepareStatement("UPDATE panier SET quantite =?  WHERE id_User =? AND id_produit =? ");
            pt.setInt(1, quantity);
            pt.setInt(2, user_id);
            pt.setInt(3, product_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
 

    public ObservableList<Produit> getInitialTableData_Cart(int user_id) {
        String sql = "SELECT produit.nom_produit, produit.image, produit.prix_unitaire, panier.quantite, panier.id_produit FROM produit INNER JOIN panier ON produit.id_produit=panier.id_produit WHERE panier.id_User = '" + user_id + "'";
        ObservableList<Produit> data = FXCollections.observableArrayList();
        try {
            PreparedStatement s = MyConnection.getInstance().getCnx().prepareStatement(sql);
            s.executeQuery(sql);
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                File file = new File(rs.getString("produit.image"));
                Image imgg = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(imgg);
                imgView.setFitWidth(60);
                imgView.setFitHeight(60);
                
                Produit p = new Produit();
                
//               Produit p = new Produit(res.getInt(5), res.getString(1), 1, res.getDouble(3), "categorie", res.getInt(4), res.getString(2));
//                p.setImagev(imgView);

                p.setIdProduit(rs.getInt("panier.id_produit"));
          
                p.setNomProduit(rs.getString("produit.nom_produit"));
             
                p.setPrixUnitaire(rs.getFloat("produit.prix_unitaire"));
                p.setQuantite(rs.getInt("panier.quantite"));
                p.setImagedisplay(imgView);
             
                
              
            
              
                data.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error in Building Data" + ex);
        }
        return data;
    }

    public double total_price_calcul(int activ_user_id) {
        double total_price = 0;
        final String sql = "SELECT produit.prix_unitaire, panier.quantite FROM produit INNER JOIN panier ON produit.id_produit=panier.id_produit WHERE panier.id_User = '" + activ_user_id + "'";

        try (PreparedStatement stm = MyConnection.getInstance().getCnx().prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                
                 total_price = total_price + (rs.getDouble("produit.prix_unitaire")*rs.getInt("panier.quantite"));
//                total_price = total_price + (rs.getDouble(1)*rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("totalprice "+total_price);
        return total_price;

    }
    
}
