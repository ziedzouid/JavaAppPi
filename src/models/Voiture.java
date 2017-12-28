/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DELL
 */
public class Voiture {
    private int id;
    private int user_id;
    private String marque;
    private String modele;

    

    public Voiture(int id, String marque, String modele,int id_user) {
        this.id = id;
        this.user_id =id_user;
        this.marque = marque;
        this.modele = modele;
    }

    public Voiture() {
    }
    

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Voiture(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }

    public int getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Voiture other = (Voiture) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if ((this.marque == null) ? (other.marque != null) : !this.marque.equals(other.marque)) {
            return false;
        }
        if ((this.modele == null) ? (other.modele != null) : !this.modele.equals(other.modele)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id  + ", marque=" + marque + ", modele=" + modele + ", id_user=" + user_id + '}';
    }


    
    
    
}
