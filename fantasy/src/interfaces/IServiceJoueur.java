/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.Joueur;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghada HJ
 */
public interface IServiceJoueur {

    public void addJoueur(Joueur j);

    public List<Joueur> getJoueurs();

    public void deleteJoueur(Joueur j);

    public void updateJoueur(Joueur j);

    public List<Joueur> getJoueurEquipe(int id);

    public List<Joueur> SearchPlayer(String character, int id);

    public List<Joueur> Search(String character);

    public List<Joueur> Trier(int i);

    public List<Joueur> TrierPlayer(int i, int id);

    public ObservableList FillCombo();

}
