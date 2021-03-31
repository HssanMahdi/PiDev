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
public interface IGroupe<I,K> {

     public I getGroupebyId(int id);
     public void creeGroupe(I g, K u);
     public void ajouterAdherentauGroupe(I g, K u);
     public void supprimerAdherentduGroupe(I g, K u);
     public void supprimerGroupe(I g);
     public void updateGroupe(I g);
     public void quitGroupe(I g,K u);
     public boolean verifonentry(I g,K u);
     public boolean verifexist(I g,K u);
     public List<I> displayOwnedGroupspourAdherent(K u);
     public List<I> displayGrouppourAdherent(K u);
     public List<K> displayAdherentdeGroupe(I g);
     

    
}
