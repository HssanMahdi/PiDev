/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author PC
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Publication {

    public int id_pub;
    public String nom_pub;
    public Date date_pub;
    public String contenu_pub;
  

  
    

    public Publication(String tag, String contenu_pub) {

        this.nom_pub = tag;

        this.contenu_pub = contenu_pub;

    }

    public Publication() {

    }

    public Publication(String nom_pub, Date date_pub, String contenu_pub) {

        this.nom_pub = nom_pub;
        this.date_pub = date_pub;
        this.contenu_pub = contenu_pub;

    }

    public Publication(String nom_pub, int id_pub, Date date_pub, String contenu_pub) {
        this.id_pub = id_pub;

        this.nom_pub = nom_pub;
        this.date_pub = date_pub;
        this.contenu_pub = contenu_pub;

    }

    public Publication(int id_pub, String nom_pub, Date date_pub, String contenu_pub) {
        this.id_pub = id_pub;
        this.nom_pub = nom_pub;
        this.date_pub = date_pub;
        this.contenu_pub = contenu_pub;
    }


    public String getid() {
        return Integer.toString(id_pub);
    }

    @Override
    public String toString() {
        return "Publication{" + "id_pub=" + id_pub + ", nom_pub=" + nom_pub + ", date_pub=" + date_pub +'}';
    }

  

    public String getnom_pub() {
        return nom_pub;
    }

    public int getid_pub() {

        return id_pub;

    }

    public Date getdate_pub() {
        return date_pub;
    }

    public String getcontenu_pub() {
        return contenu_pub;
    }

    public void setnom_pub(String nom_pub) {
        this.nom_pub = nom_pub;
    }

    public void setid_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setdate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public void setcontenu_pub(String contenu_pub) {
        this.contenu_pub = contenu_pub;
    }
}
