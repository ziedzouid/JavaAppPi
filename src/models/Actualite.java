/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Actualite {
    
   
    private int id;
    private String type;
    private String rubrique;
    private String contenu;
    private Date dateActualite;
    private User user;

    public Actualite() {
    }

    public Actualite(int id, String type, String rubrique, String contenu, Date dateActualite, User user) {
        this.id = id;
        this.type = type;
        this.rubrique = rubrique;
        this.contenu = contenu;
        this.dateActualite = dateActualite;
        this.user =user;
    }
    
    
    

    public Actualite(String type, String rubrique, String contenu, Date dateActualite,User user) {
 
        this.type = type;
        this.rubrique = rubrique;
        this.contenu = contenu;
        this.dateActualite =  dateActualite;
        this.user = user;
    }
    
    /*pour la modification*/
    public Actualite(int id,String type, String rubrique, String contenu,User user) {
        this.id=id;
        this.type = type;
        this.rubrique = rubrique;
        this.contenu = contenu;
        this.user = user;
    }
    
    /*pour l'ajout*/
    public Actualite(String type, String rubrique, String contenu,User user) {
 
        this.type = type;
        this.rubrique = rubrique;
        this.contenu = contenu;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRubrique() {
        return rubrique;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateActualite() {
        return dateActualite;
    }

    public void setDateActualite(Date dateActualite) {
        this.dateActualite = dateActualite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
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
        final Actualite other = (Actualite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actualite{" + "id=" + id + ", type=" + type + ", rubrique=" + rubrique + ", contenu=" + contenu + ", dateActualite=" + dateActualite + ", user=" + user + '}';
    }
    
    
    
        
    
}
