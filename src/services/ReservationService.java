/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import models.Trajet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import models.Reservation;
import techniques.DataSource;
import interfaces.IReservationService;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 *
 * @author achraf
 */
public class ReservationService implements IReservationService {

    Connection connection;

    public ReservationService() {
        connection = DataSource.getInsatance().getConnection();
    }
 @Override
    public void add(Reservation r) {
        String req = "insert into reservation (trajet_id,user_id) values (?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r.getTrajet_id());
            preparedStatement.setInt(2, r.getUser_id());    
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
     @Override
    public void update(Reservation r) {
        
     String req = "update reservation set trajet_id=?,user_id=?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r.getTrajet_id());
            preparedStatement.setInt(2, r.getUser_id());;      
           preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void remove(Integer id) {
        String req = "delete from reservation where id =?";
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
    
 public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String req = "select * from reservation";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Timestamp date = Timestamp.valueOf(LocalDateTime.now()) ;
                Reservation reservation= new Reservation(resultSet.getInt("id"),date,resultSet.getInt(3),resultSet.getInt(4));
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Reservation findById(Integer id) {
        Reservation reservation = null;
        String req = "select * from reservation where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Timestamp date = Timestamp.valueOf(LocalDateTime.now()) ;
            while (resultSet.next()) {
                reservation= new Reservation(resultSet.getInt("id"),date,resultSet.getInt(3),resultSet.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservation;
    }
}
