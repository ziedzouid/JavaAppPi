/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Poste;
import techniques.DataSource;
import interfaces.IPosteService;
import java.sql.Statement;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class PosteService implements IPosteService {
    
    Connection connection;
    
    public PosteService() {
        connection = DataSource.getInsatance().getConnection();
    }

    //a modifier apres integration
    /* @Override
    public List<Poste> getByUser(User user) {
        List<Poste> postes = new ArrayList<>();
        String req = "select * from poste where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Poste poste;
                poste = new Poste(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), 222);// a verifier
                postes.add(poste);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return postes;
    }*/
    @Override
    public void add(Poste p) {
        
        String req = "insert into poste (contenu,date_post,user_id) values (?,?,?)";
        
        PreparedStatement preparedStatement;
        try {
            // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            // String date =(dateFormat.format(e.getMyDate()));
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getContenu());
            preparedStatement.setTimestamp(2, p.getDate_poste());
            preparedStatement.setInt(3, p.getPassager().getId());//a modifier

            preparedStatement.executeUpdate();
          

           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //////////// recuperation d'auto increment id poste
        String req1 = "select last_insert_id() as last_id from poste";
        PreparedStatement preparedStatement1;
        try {
            preparedStatement1 = connection.prepareStatement(req1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getInt("last_id"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Poste findById(Integer id) {
        
        Poste poste = null;
        
        String req = "select * from poste where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                
                poste = new Poste(resultSet.getInt(1), resultSet.getString(2), resultSet.getTimestamp(3), new PassagerService().findById(resultSet.getInt(4)));// a modifier...

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return poste;
        
    }
    
    @Override
    public void update(Poste p) {
        String req = "update poste set contenu=?,date_post=?,user_id=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getContenu());
            preparedStatement.setTimestamp(2, p.getDate_poste());
            preparedStatement.setInt(3, p.getPassager().getId()); //a modifier
            //  preparedStatement.setInt(4, p.getId());
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void remove(Integer id) {
        String req = "delete from poste where id =?";
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
    public List<Poste> getAll() {
        List<Poste> postes = new ArrayList<>();
        String req = "select * from poste";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Poste poste;
                poste = new Poste(resultSet.getInt(1), resultSet.getString(2), resultSet.getTimestamp(3), new PassagerService().findById(resultSet.getInt(4)));// a modifier
                postes.add(poste);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return postes;
    }
    
}
