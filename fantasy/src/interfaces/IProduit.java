/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Categorie;
import entities.Produit;

import java.util.List;

/**
 *
 * @author mhamdi iheb
 */
public interface IProduit {
    
    public void ajouterProduit(Produit c);

    
    public void supprimmerProduit(int idSupp);
      public void modifierProduit(Produit p, int idMod);
       
    /**
     *
     * @return
     */
    public List<Produit> displayProduit();
    public List<Produit> displayProduit2();
    
}
