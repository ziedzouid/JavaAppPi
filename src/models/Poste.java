/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class Poste {

    private int id;
    private String contenu;
    private Timestamp date_poste;
    private Passager passager;

    public Poste() {
    }

    public Poste(int id, String contenu, Timestamp date_poste, Passager passager) {
        
        this.id = id;
        this.contenu = contenu;
        this.date_poste = date_poste;
        this.passager = passager;
    }

    public Poste(String contenu, Timestamp date_poste, Passager passager) {
        this.contenu = contenu;
        this.date_poste = date_poste;
        this.passager = passager;
    }

    public Poste(String contenu, Passager passager) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
       // Date date = Date.valueOf(LocalDate.now());
        this.date_poste = date;
        this.contenu = contenu;
        this.passager = passager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDate_poste() {
        return date_poste;
    }

    public void setDate_poste(Timestamp date_poste) {
        this.date_poste = date_poste;
    }

     

  

    /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/
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
        final Poste other = (Poste) obj;
        if (this.id != other.id) {
            return false;
        }

        return true;
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", contenu=" + contenu + ", date_poste=" + date_poste + ", passager=" + passager + '}';
    }

    

}
