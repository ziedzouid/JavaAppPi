/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IEvenementServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Evenement;
import techniques.DataSource;



/**
 *
 * @author DELL
 */
public class EvenementServices implements IEvenementServices{
    Connection connection;

    public EvenementServices() {
        
       connection = DataSource.getInsatance().getConnection();
    }
    
    

   /* @Override
    public Evenement search(Evenement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public List<Evenement> getAll() {
    List<Evenement> evenments = new ArrayList<Evenement>();
        String req = "select * from evenement";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement evenement = new Evenement(resultSet.getInt("id"), resultSet.getString("type"),resultSet.getString("contenu"));
                evenments.add(evenement);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evenments;    
    }

    @Override
    public Evenement findById(Integer id) {
      Evenement evenement = null;
      String req = "select * from evenement where id=?";
      PreparedStatement preparedStatement;
        try {
            preparedStatement =connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               evenement = new Evenement(resultSet.getInt("id"),resultSet.getString("type"),resultSet.getString("contenu"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
        return evenement;  
    }

    @Override
    public void remove(Integer id) {
      String req = "delete from evenement where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }

    @Override
    public void update(Evenement e) {
    String req = "update evenement set type=?,contenu=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(3, e.getId());
            preparedStatement.setString(1, e.getType());
            preparedStatement.setString(2, e.getContenu());            
           preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }

    @Override
    public void add(Evenement e) {
        String req = "insert into evenement (type , contenu) values (?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, e.getType());
            preparedStatement.setString(2, e.getContenu());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    
}
