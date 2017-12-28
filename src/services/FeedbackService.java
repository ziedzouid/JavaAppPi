/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IFeedbackService;
import models.Feedback;
import techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FeedbackService implements IFeedbackService {

    Connection connection;
    private PreparedStatement pst;

    public FeedbackService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Feedback t) {
        String req = "insert into feedback (titre,contenu,user_id) values (?,?,?)";

        try {
            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getContenu());
            System.out.println(t.getUser());
            pst.setInt(3, t.getUser().getId());
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
    public void update(Feedback t) {
        String req = "update feedback set titre=?,contenu=?,user_id=? where id = ?";
    
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getContenu());
            pst.setInt(3, t.getUser().getId());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
            String req = "delete from feedback where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
        @Override
    public void removeall() {
            String req = "delete from feedback ";
        try {
            pst = connection.prepareStatement(req);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Feedback findById(Integer id) {
    Feedback feedback = null;
        String req = "select * from feedback where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                feedback = new Feedback(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),date, new UserService().findById2( resultSet.getInt(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedback;   
    }

    @Override
    public List<Feedback> getAll() {
             List<Feedback> feedbacks = new ArrayList<>();
        String req = "select * from feedback";
        try {
            pst = connection.prepareStatement(req);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
           java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                
                Feedback feedback = new Feedback(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),date,new UserService().findById2( resultSet.getInt(5)));
                feedbacks.add(feedback);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbacks;

    }

}
