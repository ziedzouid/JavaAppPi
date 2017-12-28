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
public class Admin extends User {

    public Admin(int id, String nom, String prenom, String sexe, Date date_naissance, int tel, String mail,String mdp, String avatar) {
        super(id, nom, prenom, sexe, date_naissance, tel, mail, mdp, avatar);
    }

    public Admin(String nom, String prenom, String sexe, Date date_naissance, int tel, String email, String mdp, String avatar) {
        super(nom, prenom, sexe, date_naissance, tel, email, mdp, avatar);
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString()+ '}';
    }

}
