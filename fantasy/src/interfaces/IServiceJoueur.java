/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entities.Joueur;
import java.sql.SQLException;
import java.util.List;

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
     
     public List<Joueur> SearchPlayer(String character);
 
}
