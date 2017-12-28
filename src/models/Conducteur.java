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
public class Conducteur extends User{
    
 private Circle circle ;

    public Conducteur(int id, String nom, String prenom, String sexe, Date date_naissance, int tel, String mail, String mdp, String avatar, Circle circle) {
        super(id, nom, prenom, sexe, date_naissance, tel, mail, mdp, avatar);        
   this.circle=circle; }

    public Conducteur(String nom, String prenom, String sexe, Date date_naissance, int tel, String email, String mdp, String avatar,Circle circle) {
        super(nom, prenom, sexe, date_naissance, tel, email, mdp, avatar);
      this.circle=circle;  
    }

  
    

  
 
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    @Override
    public String toString() {
        return "Conducteur{" + "circle=" + circle + '}';
    }



   
    
    
}
