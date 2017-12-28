/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DELL
 */
public class Evenement {
    private int id;
    private String type;
    private String contenu;

    public Evenement() {
    }
    
    

    public Evenement(int id, String type, String contenu) {
        this.id = id;
        this.type = type;
        this.contenu = contenu;
    }

    public Evenement(String type, String contenu) {
        this.type = type;
        this.contenu = contenu;
    }

    public Evenement(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.contenu == null) ? (other.contenu != null) : !this.contenu.equals(other.contenu)) {
            return false;
        }
        return true;
        
        
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", type=" + type + ", contenu=" + contenu + '}';
    }


    
    
    
}
