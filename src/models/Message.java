/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Message {

    private int id;
    private String contenue;
    private Date dateMessage;
    private String source;
    private int verif;
    private int toId;
    private int fromId;

    public Message() {
    }

    public Message(String contenue, Date dateMessage, String source, int verif, int fromId, int toId) {
        this.contenue = contenue;
        this.dateMessage = dateMessage;
        this.source = source;
        this.verif = verif;
        this.toId = toId;
        this.fromId = fromId;
    }

    public Message(int id, String contenue, Date dateMessage, String source, int verif, int fromId, int toId) {
        this.id = id;
        this.contenue = contenue;
        this.dateMessage = dateMessage;
        this.source = source;
        this.verif = verif;
        this.toId = toId;
        this.fromId = fromId;
    }

    public Message(String contenue, String source, int verif, int fromId, int toId) {
        this.contenue = contenue;
        this.source = source;
        this.verif = verif;
        this.toId = toId;
        this.fromId = fromId;
    }

    public Message(String contenue, String source, int fromId, int toId) {
        this.contenue = contenue;
        this.source = source;
        this.toId = toId;
        this.fromId = fromId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getSource() {
        return source;
    }

    public void setsource(String source) {
        this.source = source;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getVerif() {
        return verif;
    }

    public void setVerif(int verif) {
        this.verif = verif;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", contenue=" + contenue + ", dateMessage=" + dateMessage + ", source=" + source + ", verif=" + verif + ", toId=" + toId + ", fromId=" + fromId + '}';
    }



    @Override
    public int hashCode() {
        int hash = 5;
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
        final Message other = (Message) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
