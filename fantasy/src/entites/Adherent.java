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
public class Adherent {
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
    
  


}

    
