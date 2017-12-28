/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 *  @author Azzen Abidi <azzendotabidiatespritdottn>
 */
 
public class Livraison {

    private int id;
    private Date dateLivraison;
    

    public Livraison() {
    }

    public Livraison(int id, Date dateLivraison) {
        this.id = id;
        this.dateLivraison = dateLivraison;
    }

    public Livraison( Date dateLivraison) {
        
        this.dateLivraison = dateLivraison;
        
    }

    
    @Override
    public String toString() {
        return "Livraison{" + "id=" + getId() + ", dateLivraison=" + getDateLivraison()  + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Livraison other = (Livraison) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dateLivraison
     */
    public Date getDateLivraison() {
        return dateLivraison;
    }

    /**
     * @param dateLivraison the dateLivraison to set
     */
    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

}
