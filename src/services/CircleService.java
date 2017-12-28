/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICircleService;
import models.Circle;
import techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Conducteur;
import models.Passager;
import models.User;

/**
 *
 *  @author Azzen Abidi <azzendotabidiatespritdottn>
 */
public class CircleService implements ICircleService {

    Connection connection;
    private PreparedStatement pst;

    public CircleService() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Circle t) {
        String req = "insert into circle (nom,type,description) values (?,?,?)";

        try {
            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setString(3, t.getDescription());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs != null && rs.first()) {
                int generatedId = rs.getInt(1);
                t.setId(generatedId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Circle t) {
        String req = "update circle set nom=?,type=?, description=? where id = ?";

        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from circle where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Circle findById(Integer id) {
           Circle circle = null;
        String req = "select * from circle where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
               
                circle = new Circle(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return circle;  
    }

    @Override
    public List<Circle> getAll() {
            List<Circle> circles = new ArrayList<>();
        String req = "select * from circle";
        try {
            pst = connection.prepareStatement(req);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {    
                Circle circle;
                circle = new Circle(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                circles.add(circle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return circles;  
    }
    
        public int findCircleIdByUser(Integer id) {
       int crl = 0;
        String req = "select * from user where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
               
                 crl = resultSet.getInt(11);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return crl;  
    }
        
        public List findUsersByCircle(Integer id){
           
        List<User> users = new ArrayList<>();
        String req = "select * from user where circle_id = ? ";
        PreparedStatement preparedStatement;
        try {
           pst = connection.prepareStatement(req);
           pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            
            while (resultSet.next()) {
                User user = null;
 
                    user = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(id));
                    users.add(user);
    
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
        }
        
        
}

