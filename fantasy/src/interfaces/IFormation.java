/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Mahdi
 */
public interface IFormation<I,K,R> {
     public void creeFormation(R a);
     public void ajouterJoueurauFormation(R a,I f, K j);//quand l'Adherent fait un achat d'un joueur
     public void supprimerJoueurduFormation(R a, K j);
     public void supprimerJoueurduFormation2(K j); // quand un joueur est supprimer de la BD
     public I getFormation(R a);
     public List<K> displayJoueurdeFormation(R a);
}
