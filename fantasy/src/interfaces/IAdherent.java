/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.Adherent;
import java.util.List;

/**
 *
 * @author MediaStudio
 */
public interface IAdherent<user> {

    public void ajouterAdherent(Adherent a);

    public void supprimerAdherent(Adherent a);

    public void updateAdherent(Adherent a);

    public Adherent connecter(int id);

    public List displayAdherent();

}
