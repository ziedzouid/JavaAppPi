/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import models.Voiture;
import techniques.DataSource;
import interfaces.IVoitureServices;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class VoitureServices implements IVoitureServices {

    Connection connection;

    public VoitureServices() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Voiture t) {

        String req = "insert into t_voiture (marque,modele,user_id) values (?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getMarque());
            preparedStatement.setString(2, t.getModele());
            preparedStatement.setInt(3, t.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Voiture> getAll() {
        List<Voiture> voitures = new ArrayList<Voiture>();
        String req = "select * from t_voiture";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Voiture voiture = new Voiture(resultSet.getInt("id"), resultSet.getString("marque"), resultSet.getString("modele"), resultSet.getInt("user_id"));
                voitures.add(voiture);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return voitures;
    }

    @Override
    public Voiture findById(Integer id) {
      Voiture voiture = null;
      String req = "select * from t_voiture where id=?";
      PreparedStatement preparedStatement;
        try {
            preparedStatement =connection.prepareStatement(req);
            preparedStatement.setInt(1,id);
            
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               voiture = new Voiture(resultSet.getInt("id"), resultSet.getString("marque"), resultSet.getString("modele"), resultSet.getInt("user_id"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
        return voiture;
      
    }

    @Override
    public void update(Voiture t) {
        String req = "update t_voiture set marque=?,modele=?,user_id=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(4, t.getId());
            preparedStatement.setString(1, t.getMarque());
            preparedStatement.setString(2, t.getModele());
            preparedStatement.setInt(3, t.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void remove(Integer id) {
        String req = "delete from t_voiture where id =?";
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
    public Voiture search(Voiture t) {
        Voiture voiture = null;
      String req = "select * from t_voiture where marque=?";
      PreparedStatement preparedStatement;
        try {
            preparedStatement =connection.prepareStatement(req);
            preparedStatement.setString(1,t.getMarque());
            
           ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               voiture = new Voiture(resultSet.getInt("id"), resultSet.getString("marque"), resultSet.getString("modele"), resultSet.getInt("user_id"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
        return voiture;
    
    }
   
    
}
