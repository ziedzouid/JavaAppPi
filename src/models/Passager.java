/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;


/**
 *
 * @author esprit
 */
public class Passager extends User {
    
 private Circle circle;

    public Passager(int id, String nom, String prenom, String sexe, Date date_naissance, int tel, String mail, String mdp, String avatar, Circle circle) {
        super(id, nom, prenom, sexe, date_naissance, tel, mail, mdp, avatar);
  this.circle=circle;  }

    public Passager(String nom, String prenom, String sexe, Date date_naissance, int tel, String email,String mdp, String avatar,Circle circle) {
super(nom, prenom, sexe, date_naissance, tel, email, mdp, avatar); 
  this.circle=circle;  }


    @Override
    public String toString() {
        return "passager"+super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

   
    
}
