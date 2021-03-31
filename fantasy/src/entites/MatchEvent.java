/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author PC
 */
public class MatchEvent {

    private int idMatch;
    private String titre;
    private Date dateMatch;
    private int id_equipeA;
    private int id_equipeB;
    private String nomEquipeA;
    private String nomEquipeB;
    private ImageView logoimageA;
    private ImageView logoimageB;

    public void setLogoimageA(ImageView logoimageA) {
        this.logoimageA = logoimageA;
    }

    public void setLogoimageB(ImageView logoimageB) {
        this.logoimageB = logoimageB;
    }

    public ImageView getLogoimageA() {
        return logoimageA;
    }

    public ImageView getLogoimageB() {
        return logoimageB;
    }

    public MatchEvent() {
    }

    public MatchEvent(int idMatch, String titre, Date date, int id_equipeA, int id_equipeB) {
        this.idMatch = idMatch;
        this.titre = titre;
        this.dateMatch = date;
        this.id_equipeA = id_equipeA;
        this.id_equipeB = id_equipeB;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateMatch(Date date) {
        this.dateMatch = date;
    }

    public void setId_equipeA(int id_equipeA) {
        this.id_equipeA = id_equipeA;
    }

    public void setId_equipeB(int id_equipeB) {
        this.id_equipeB = id_equipeB;
    }

    public void setNomEquipeA(String nomEquipeA) {
        this.nomEquipeA = nomEquipeA;
    }

    public void setNomEquipeB(String nomEquipeB) {
        this.nomEquipeB = nomEquipeB;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public int getId_equipeA() {
        return id_equipeA;
    }

    public int getId_equipeB() {
        return id_equipeB;
    }

    public String getNomEquipeA() {
        return nomEquipeA;
    }

    public String getNomEquipeB() {
        return nomEquipeB;
    }

    @Override
    public String toString() {
        return "MatchEvent{" + "idMatch=" + idMatch + ", titre=" + titre + ", date=" + dateMatch + ", id_equipeA=" + id_equipeA + ", id_equipeB=" + id_equipeB + ", nomEquipeA=" + nomEquipeA + ", nomEquipeB=" + nomEquipeB + '}';
    }

}
