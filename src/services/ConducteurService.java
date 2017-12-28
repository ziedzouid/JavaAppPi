/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conducteur;
import interfaces.IConducteurService;
import techniques.DataSource;

/**
 *
 * @author esprit
 */
public class ConducteurService implements IConducteurService {

    Connection connection;

    public ConducteurService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Conducteur c) {
        System.out.println(c.getCircle().getId());
        String req = "insert into user(nom,prenom,sexe,date_naissance,telephone,email,password,avatar,role,circle_id,roles,username,username_canonical,email_canonical,enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, c.getNom());
            preparedStatement.setString(2, c.getPrenom());
            preparedStatement.setString(3, c.getSexe());
           // java.sql.Date sqlDate = new java.sql.Date(c.getDate_naissance().getTime());
            preparedStatement.setDate(4, (Date) c.getDate_naissance());
            preparedStatement.setInt(5, c.getTel());
            preparedStatement.setString(6, c.getEmail());
            preparedStatement.setString(7, c.getMdp());
            preparedStatement.setString(8, c.getAvatar());
            preparedStatement.setString(9, "Conducteur");
            preparedStatement.setInt(10, c.getCircle().getId());

            String ch = "a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}";
            preparedStatement.setString(11, ch);

            preparedStatement.setString(12, c.getUsername());
            preparedStatement.setString(13, c.getUsername());
            preparedStatement.setString(14, c.getEmail());
            preparedStatement.setInt(15, 1);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from user where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*A modifier pour un bon fnct*/ 
    @Override
    public void update(Conducteur conduc) {
        String req = "update user set nom=?,prenom=?,sexe=?,date_naissance=?,telephone=?,email=?,password=?,avatar=?,role=?,circle_id=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, conduc.getNom());
            preparedStatement.setString(2, conduc.getPrenom());
            preparedStatement.setString(3, conduc.getSexe());
            java.sql.Date sqlDate = new java.sql.Date(conduc.getDate_naissance().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, conduc.getTel());
            preparedStatement.setString(6, conduc.getEmail());
            preparedStatement.setString(7, conduc.getMdp());
            preparedStatement.setString(8, conduc.getAvatar());
            preparedStatement.setString(9, "Conducteur");
            preparedStatement.setInt(10, conduc.getCircle().getId());
            preparedStatement.setInt(11, conduc.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public List<Conducteur> getAll() {
        List<Conducteur> conducteurs = new ArrayList<>();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Conducteur conduc;
                conduc = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));

                conducteurs.add(conduc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conducteurs;
    }

    @Override
    public Conducteur findById(Integer id) {
        Conducteur conduc = null;
        String req = "select * from user where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                conduc = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conduc;
    }
}
