/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.RatingJoueur;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IServiceRating {

    public void addRating(RatingJoueur r);

    public List<RatingJoueur> afficheRating();

    public void deleteRating(int idUser, int idJoueur);

    public RatingJoueur getById(int id);

    public double calculAvis(int id);

    public boolean UserExist(int idJR, int idUser);
}
