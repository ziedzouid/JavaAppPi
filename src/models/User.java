/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author esprit
 */
public class User {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String sexe;
    protected Date date_naissance;
    protected String email;
    protected int tel;
    protected String mdp;
    protected String avatar;
    protected String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(int id, String nom, String prenom,String sexe, Date date_naissance,int tel, String email, String mdp,String avatar) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe=sexe;
        this.date_naissance = date_naissance;
        this.email = email;
        this.tel = tel;
        this.mdp=mdp;
        this.avatar = avatar;
    }
public User(String nom, String prenom,String sexe, Date date_naissance,int tel, String email, String mdp,String avatar) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe=sexe;
        this.date_naissance = date_naissance;
        this.email = email;
        this.tel = tel;
        this.mdp=mdp;
        this.avatar = avatar;
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public int getTel() {
        return tel;
    }

   
   
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", date_naissance=" + date_naissance + ", email=" + email + ", tel=" + tel + ", mdp=" + mdp + ", avatar=" + avatar +  '}';
    }

    public String getSexe() {
        return sexe;
    }

    public String getMdp() {
        return mdp;
    }

    public String getAvatar() {
        return avatar;
    }


    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
}

