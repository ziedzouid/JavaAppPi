/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class Evaluation {

    private int id;
    private byte note; //max 127
    private Timestamp myDate;//type date ?
    // private DATETIME date_evaluation ; //type date ?
    private String commentaire;
    private Trajet trajet;
    private  Passager passager;

    public Evaluation(byte note, String commentaire, Trajet trajet, Passager passager) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        //  Date date = Date.valueOf(LocalDate.now());
        this.myDate = date;
        this.note = note;
        this.commentaire = commentaire;
        this.trajet = trajet;
        this.passager = passager;
    }

    public Evaluation() {
        myDate=Timestamp.valueOf(LocalDateTime.now());
    }

    // les constructeur à modifier aprés integration
    public Evaluation(int id, byte note, Timestamp myDate, String commentaire, Trajet trajet, Passager passager) {
        this.id = id;
        this.note = note;
        this.myDate = myDate;
        this.commentaire = commentaire;
        this.trajet = trajet;
        this.passager = passager;
    }

    public Evaluation(byte note, Timestamp myDate, String commentaire, Trajet trajet, Passager passager) {

        this.note = note;
        this.myDate = myDate;
        this.commentaire = commentaire;
        this.trajet = trajet;
        this.passager = passager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getNote() {
        return note;
    }

    public void setNote(byte note) {
        this.note = note;
    }

    public Timestamp getMyDate() {
        return myDate;
    }

    public void setMyDate(Timestamp myDate) {
        this.myDate = myDate;
    }

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public Passager getPassager() {
        return passager;
    }

    /*public Trajet getTrajet() {
    return trajet;
    }
    public void setTrajet(Trajet trajet) {
    this.trajet = trajet;
    }*/
    public void setPassager(Passager passager) {
        this.passager = passager;
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
        final Evaluation other = (Evaluation) obj;
        if (this.id != other.id) {
            return false;
        }
    
        return true;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", note=" + note + ", myDate=" + myDate + ", commentaire=" + commentaire + ", trajet=" + trajet + ", passager=" + passager + '}';
    }

   
    

}
