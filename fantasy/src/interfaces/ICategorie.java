/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Categorie;
import java.util.List;

/**
 *
 * @author mhamdi iheb
 */
public interface ICategorie {
    
    public void ajouterCategorie(Categorie c);

    /**
     *
     * @param idSupp
     */
    public void supprimmerCategorie(int idSupp);
      public void modifierCategorie(Categorie c, int idMod);
       
    /**
     *
     * @return
     */
    public List<Categorie> displayCategorie();
    
}
