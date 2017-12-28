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
import java.util.Date;
import java.util.List;
import models.Passager;
import interfaces.IPassagerService;
import techniques.DataSource;

/**
 *
 * @author esprit
 */
public class PassagerService implements IPassagerService {

    Connection connection;

    public PassagerService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Passager p) {
        String req = "insert into user(id,nom,prenom,sexe,date_naissance,telephone,email,password,avatar,role,circle_id,roles,username,username_canonical,email_canonical,enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, p.getId());
            preparedStatement.setString(2, p.getNom());
            preparedStatement.setString(3, p.getPrenom());
            preparedStatement.setString(4, p.getSexe());
            java.sql.Date sqlDate = new java.sql.Date(p.getDate_naissance().getTime());
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setInt(6, p.getTel());
            preparedStatement.setString(7, p.getEmail());
            preparedStatement.setString(8, p.getMdp());
            preparedStatement.setString(9, p.getAvatar());

            preparedStatement.setString(10, "Passager");
            preparedStatement.setInt(11, p.getCircle().getId());

            String ch = "a:1:{i:0;s:13:\"ROLE_PASSAGER\";}";
            preparedStatement.setString(12, ch);

            preparedStatement.setString(13, p.getUsername());
            preparedStatement.setString(14, p.getUsername());
            preparedStatement.setString(15, p.getEmail());
            preparedStatement.setInt(16, 1);

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

    @Override
    public void update(Passager p) {

        String req = "update user set nom=?,prenom=?,sexe=?,date_naissance=?,telephone=?,email=?,password=?,avatar=?,role=?,circle_id where id =?";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getNom());
            preparedStatement.setString(2, p.getPrenom());
            preparedStatement.setString(3, p.getSexe());
            java.sql.Date sqlDate = new java.sql.Date(p.getDate_naissance().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, p.getTel());
            preparedStatement.setString(6, p.getEmail());
            preparedStatement.setString(7, p.getMdp());
            preparedStatement.setString(8, p.getAvatar());
            preparedStatement.setString(9, "Passager");
            preparedStatement.setInt(10, p.getCircle().getId());
            preparedStatement.setInt(11, p.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override

    public List<Passager> getAll() {

        List<Passager> passagers = new ArrayList<>();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:1:{i:0;s:13:\"ROLE_PASSAGER\";}");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passager p;
                p = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                passagers.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return passagers;
    }

    @Override
    public Passager findById(Integer id) {
        Passager passager = null;
        String req = "select * from user where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passager = new Passager(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return passager;
    }

}
