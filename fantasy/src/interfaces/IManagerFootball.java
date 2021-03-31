/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.ManagerFootball;
import java.util.List;

/**
 *
 * @author MediaStudio
 */
public interface IManagerFootball<user> {

    public void ajouterManagerFootball(ManagerFootball m);

    public void supprimerManagerFootball(ManagerFootball m);

    public void updateManagerFootball(ManagerFootball m);

    public List<ManagerFootball> displayManagerFootball();

    public ManagerFootball connecter(int id);

    public List<ManagerFootball> Rechercher(String s);

}
