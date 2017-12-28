/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Azzen Abidi <azzendotabidiatespritdottn>
 */
public class Produit {

    private int id;
    private String type;
    private String poids;
   
   

    public Produit() {
    }

    public Produit(int id, String type, String poids ) {
        this.id = id;
        this.type = type;
        this.poids = poids;
    }
     public Produit( String type, String poids ) {
        this.type = type;
        this.poids = poids;
    }


       @Override
    public String toString() {
        return "Produit{" + "id=" + getId() + ", type=" + getType() + ", destination=" + getPoids() + '}';
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
        final Produit other = (Produit) obj;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the poids
     */
    public String getPoids() {
        return poids;
    }

    /**
     * @param poids the poids to set
     */
    public void setPoids(String poids) {
        this.poids = poids;
    }

}
