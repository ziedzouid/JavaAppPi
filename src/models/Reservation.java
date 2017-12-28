/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author achraf
 */
public class Reservation {
  private int id;
  private Timestamp date_reservation;
  private int trajet_id;
  private int user_id; 

    public Reservation(int id, Timestamp date_reservation, int trajet_id, int user_id) {
        this.id = id;
        this.date_reservation = date_reservation;
        this.trajet_id = trajet_id;
        this.user_id = user_id;
    }

    public Reservation(int trajet_id, int user_id) {
        this.trajet_id = trajet_id;
        this.user_id = user_id;
    }

    public Reservation(Timestamp date_reservation, int trajet_id, int user_id) {
        this.date_reservation = date_reservation;
        this.trajet_id = trajet_id;
        this.user_id = user_id;
    }

  
    public Reservation(int id) {
        this.id = id;
    }


    public Timestamp getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Timestamp date_reservation) {
        this.date_reservation = date_reservation;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrajet_id() {
        return trajet_id;
    }

    public void setTrajet_id(int trajet_id) {
        this.trajet_id = trajet_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_reservation=" + date_reservation + ", trajet_id=" + trajet_id + ", user_id=" + user_id + '}';
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.date_reservation);
        hash = 79 * hash + this.trajet_id;
        hash = 79 * hash + this.user_id;
        return hash;
    }


  
}
