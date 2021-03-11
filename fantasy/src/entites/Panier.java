/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mhamdi iheb
 */
public class Panier {
    
   private List listProduit = new ArrayList();

    public List getListProduit() {
        return listProduit;
    }

    public Panier() {
    }

    public void setListProduit(List listProduit) {
        this.listProduit = listProduit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.listProduit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Panier other = (Panier) obj;
        if (!Objects.equals(this.listProduit, other.listProduit)) {
            return false;
        }
        return true;
    }
    
    
    
}
