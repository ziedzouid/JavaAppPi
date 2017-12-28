/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

;

import java.util.Date;


/**
 *
 * @author ASUS
 */
public class Feedback {
    
    private int id;
    private String titre;
    private String contenu;
    private Date dateFeedback;
    private User user;

    public Feedback() {
    }

    public Feedback(String titre, String contenu, User user) {
        this.titre = titre;
        this.contenu = contenu;
        this.user = user;
    }
    
    
    
    public Feedback(int id, String titre, String contenu, Date dateFeedback, User user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.dateFeedback = dateFeedback;
        this.user = user;
    }
    
    
    public Feedback(String titre, String contenu,Date dateFeedback,User user) {
        this.titre = titre;
        this.contenu = contenu;
        this.dateFeedback = dateFeedback;
        this.user= user;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateFeedback() {
        return dateFeedback;
    }

    public void setDateFeedback(Date dateFeedback) {
        this.dateFeedback = dateFeedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", dateFeedback=" + dateFeedback + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Feedback other = (Feedback) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
        
    
    
}
