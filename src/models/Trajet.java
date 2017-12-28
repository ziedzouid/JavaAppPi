/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author achraf
 */
public class Trajet {
    
    
     /***new copy*/
    private int id;
    private String ville_depart;
    private String ville_arrive;
    private Date date_trajet;
    private Timestamp date_annonce;
    private int nombre_place;
    private double prix;
    private int id_evenement;
    private int id_user;

    public Trajet() {
    }

   
   public Trajet(int id, String ville_depart, String ville_arrive,Date date_trajet,Timestamp date_annonce, int nombre_place, double prix, int id_evenement, int id_user) {
        this.id = id;
        this.ville_depart = ville_depart;
        this.ville_arrive = ville_arrive;
        this.date_trajet = date_trajet;
        this.date_annonce = date_annonce;
        this.nombre_place = nombre_place;
        this.prix = prix;
        this.id_evenement = id_evenement;
        this.id_user = id_user;
    }

    public Trajet(String ville_depart, String ville_arrive, Date date_trajet, Timestamp date_annonce, int nombre_place, double prix, int id_evenement, int id_user) {
        this.ville_depart = ville_depart;
        this.ville_arrive = ville_arrive;
        this.date_trajet = date_trajet;
        this.date_annonce = date_annonce;
        this.nombre_place = nombre_place;
        this.prix = prix;
        this.id_evenement = id_evenement;
        this.id_user = id_user;
    }

  
    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getVille_depart() {
        return ville_depart;
    }

    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }

    public String getVille_arrive() {
        return ville_arrive;
    }

    public void setVille_arrive(String ville_arrive) {
        this.ville_arrive = ville_arrive;
    }

    public Date getDate_trajet() {
        return date_trajet;
    }

    public void setDate_trajet(Date date_trajet) {
        this.date_trajet = date_trajet;
    }

    public Timestamp getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Timestamp date_annonce) {
        this.date_annonce = date_annonce;
    }

    public int getNombre_place() {
        return nombre_place;
    }

    public void setNombre_place(int nombre_place) {
        this.nombre_place = nombre_place;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", ville_depart=" + ville_depart + ", ville_arrive=" + ville_arrive + ", date_trajet=" + date_trajet + ", date_annonce=" + date_annonce + ", nombre_place=" + nombre_place + ", prix=" + prix + ", id_evenement=" + id_evenement + ", id_user=" + id_user + '}';
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
        final Trajet other = (Trajet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}