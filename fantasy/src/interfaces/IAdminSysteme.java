/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.AdminSysteme;
import java.util.List;

/**
 *
 * @author MediaStudio
 */
public interface IAdminSysteme {

    public void ajouterAdminSysteme(AdminSysteme a);

    public void supprimerAdminSysteme(AdminSysteme a);

    public void updateAdminSysteme(AdminSysteme a);

    public List<AdminSysteme> displayAdminSysteme();

    public AdminSysteme connecter(int id);

    public List<AdminSysteme> Rechercher(String s);

    public List<AdminSysteme> Trier(int i);

}
