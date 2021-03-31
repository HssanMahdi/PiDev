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
public class Adherent extends Personne{
      private int score_user;
    private int solde;

    

    public Adherent(int id_user, String nom_user, String email, String password, String type_user, int score_user, int solde) {
        super(id_user, nom_user, email, password, type_user);

        this.score_user = score_user;
        this.solde = solde;
    }

    public Adherent() {

    }

    public void setScore_user(int score_user) {
        this.score_user = score_user;
    }

    public int getScore_user() {
        return score_user;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return solde;
    }

    @Override
    public String toString() {
        return super.toString() + "score_user=" + score_user + "solde=" + solde;
    }

    @Override
    public void setType_user(String type_user) {
        super.setType_user(type_user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom_user(String nom_user) {
        super.setNom_user(nom_user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIduser(int id_user) {
        super.setIduser(id_user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType_user() {
        return super.getType_user(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom_user() {
        return super.getNom_user(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId_user() {
        return super.getId_user(); //To change body of generated methods, choose Tools | Templates.
    }
    
  


}

    
