/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Mahdi
 */
public class AdminSysteme {
    public AdminSysteme(int id_user, String nom_user, String email, String password, String type_user) {
        super(id_user, nom_user, email, password, type_user);
    }

    public AdminSysteme() {

    }
    

    @Override
    public String toString() {
        return super.toString();
    }
}
