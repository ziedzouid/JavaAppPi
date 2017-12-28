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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import interfaces.ITrajetService;
import models.User;

/**
 *
 * @author achraf
 */

public class TrajetService implements ITrajetService {

    /*new service*/
    private PreparedStatement pst;
    Connection connection;

    public TrajetService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Trajet t) {
        String req = "insert into trajet (ville_depart,ville_arrive,date_trajet,nombre_place,prix,user_id) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getVille_depart());
            preparedStatement.setString(2, t.getVille_arrive());
            preparedStatement.setDate(3, t.getDate_trajet());
            preparedStatement.setInt(4, t.getNombre_place());
            preparedStatement.setDouble(5, t.getPrix());
            //preparedStatement.setInt(6, t.getId_evenement());
            preparedStatement.setInt(6, t.getId_user());  //t.getConducteur().getId()
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from trajet where id =?";
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
    public void update(Trajet t) {

        String req = "update trajet set ville_depart=?,ville_arrive=?,date_trajet=?,nombre_place=?,prix=?,evenement_id=?,user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getVille_depart());
            preparedStatement.setString(2, t.getVille_arrive());
            preparedStatement.setDate(2, t.getDate_trajet());
            preparedStatement.setInt(5, t.getNombre_place());
            preparedStatement.setInt(6, (int) t.getPrix());
            preparedStatement.setInt(7, t.getId_evenement());
            preparedStatement.setInt(8, t.getId_user());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override

    public List<Trajet> getAll() {
        List<Trajet> trajets = new ArrayList<>();

        String req = "select * from trajet ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //  Timestamp date1 = Timestamp.valueOf(LocalDateTime.now()) ;
                Trajet trajet = new Trajet(resultSet.getInt("id"),
                         resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getTimestamp(5),
                         resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));
                trajets.add(trajet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trajets;
    }

    @Override
    public Trajet findById(Integer id) {
        Trajet trajet = null;
        String req = "select * from trajet where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Timestamp date = Timestamp.valueOf(LocalDateTime.now());
            while (resultSet.next()) {

                trajet = new Trajet(resultSet.getInt("id"),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), date,
                         resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trajet;
    }

    public Trajet UserInTarget(User u) throws SQLException {
        Trajet trajet = null;
        String req = "select * from trajet where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, u.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Timestamp date = Timestamp.valueOf(LocalDateTime.now());
            while (resultSet.next()) {

                trajet = new Trajet(resultSet.getInt("id"),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), date,
                         resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trajet;
    }

    @Override
    public List<Trajet> getByUser(Integer r) {
        List<Trajet> trajets = new ArrayList<>();
        String req = "select * from trajet where user_id=?";
        PreparedStatement preparedStatement;
        try {
            //Timestamp date = Timestamp.valueOf(LocalDateTime.now()) ;
            preparedStatement = connection.prepareStatement(req);
           // System.out.println(UserService.idlogger);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trajet trajet;
                trajet = new Trajet(resultSet.getInt("id"),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getTimestamp(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));
                trajets.add(trajet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trajets;
    }

}
