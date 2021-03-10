/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpi.newpackage;

import java.util.List;

/**
 *
 * @author Mahdi
 */
public interface IGroupe<I,K> {

     public void creeGroupe(I g, K u);
     public void ajouterAdherentauGroupe(I g, K u);
     public void supprimerAdherentduGroupe(I g, K u);
     public void supprimerGroupe(I g);
     public void updateGroupe(I g);
     public List<I> displayGrouppourAdherent(K u);
     public List<K> displayAdherentdeGroupe(I g);

    
}
