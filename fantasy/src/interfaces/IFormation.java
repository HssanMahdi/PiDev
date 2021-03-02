/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Mahdi
 */
public interface IFormation<I,K,R> {
     public void creeFormation(R a);
     public void ajouterJoueurauFormation(I f, K j);
     public void supprimerJoueurduFormation(I f, K j);
     public void supprimerJoueurduFormation2(K j); // quand un joueur est supprimer de la BD
}
