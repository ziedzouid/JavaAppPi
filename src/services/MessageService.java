/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IMessageService;
import models.Message;
import techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MessageService implements IMessageService {

    Connection connection;
    private PreparedStatement pst;

    public MessageService() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Message t) {
        String req = "insert into message (source,contenu,from_id,to_id) values (?,?,?,?)";

        try {
            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getSource());
            pst.setString(2, t.getContenue());
            pst.setInt(3, t.getFromId());
            pst.setInt(4, t.getToId());
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
    public void update(Message t) {
        String req = "update message set source=?,contenu=?,verif=?,from_id=?,to_id=? where id = ?";

        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getSource());
            pst.setString(2, t.getContenue());
            pst.setInt(3, t.getFromId());
            pst.setInt(4, t.getVerif());
            pst.setInt(5, t.getFromId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from message where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Message findById(Integer id) {
        Message message = null;
        String req = "select * from message where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                message = new Message(resultSet.getInt(1), resultSet.getString(3), date, resultSet.getString(2), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return message;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        String req = "select * from message";
        try {
            pst = connection.prepareStatement(req);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Message message = null;
                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                message = new Message(resultSet.getInt(1), resultSet.getString(3), date, resultSet.getString(2), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                messages.add(message);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
    }

    /*retourner les msgs envoyé a chaque user*/
    public List<Message> getMsgByuser(int id) {
        List<Message> messages = new ArrayList<>();
        String req = "select * from message where to_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                Message message = new Message(resultSet.getInt(1), resultSet.getString(3), date, resultSet.getString(2), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                messages.add(message);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;

    }
    
        public List<String> getObjectMsgbyuser(int id) {
        List<String> messages = new ArrayList<>();
        
        String req = "select * from message where to_id=?";;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rtt = resultSet.getString("source")  + " just send you a message" + " at " + resultSet.getString(4);
                messages.add(rtt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
}
}

