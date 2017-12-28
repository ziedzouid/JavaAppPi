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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Admin;
import models.Passager;
import interfaces.IAdminService;
import techniques.DataSource;

/**
 *
 * @author esprit
 */
public class AdminService implements IAdminService {

    Connection connection;

    public AdminService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Admin a) {
        //String req = "insert into user(nom,prenom,sexe,date_naissance,telephone,email,password,avatar,role) values (?,?,?,?,?,?,?,?,?)";
        String req = "insert into user(nom,prenom,sexe,date_naissance,telephone,email,password,avatar,role,roles,username,username_canonical,email_canonical,enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, a.getNom());
            preparedStatement.setString(2, a.getPrenom());
            preparedStatement.setString(3, a.getSexe());
            java.sql.Date sqlDate = new java.sql.Date(a.getDate_naissance().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, a.getTel());
            preparedStatement.setString(6, a.getEmail());
            preparedStatement.setString(7, a.getMdp());
            preparedStatement.setString(8, a.getAvatar());

            preparedStatement.setString(9, "Administrateur");
            preparedStatement.setString(10, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
            preparedStatement.setString(11, a.getUsername());
            preparedStatement.setString(12, a.getUsername());
            preparedStatement.setString(13, a.getEmail());
            preparedStatement.setInt(14, 1);

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
    public void update(Admin a) {

        String req = "update user set nom=?,prenom=?,sexe=?,date_naissance=?,telephone=?,email=?,password=?,avatar=?,role=? where id = ?";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, a.getNom());
            preparedStatement.setString(2, a.getPrenom());
            preparedStatement.setString(3, a.getSexe());
            preparedStatement.setDate(4, (java.sql.Date) a.getDate_naissance());
            preparedStatement.setString(6, a.getEmail());
            preparedStatement.setInt(5, a.getTel());
            preparedStatement.setString(7, a.getMdp());
            preparedStatement.setString(8, a.getAvatar());
            preparedStatement.setString(9, "Administrateur");
            preparedStatement.setInt(10, a.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override

    public List<Admin> getAll() {

        List<Admin> admins = new ArrayList<>();
        String req = "select * from user where roles=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Admin a;
                a = new Admin(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
                admins.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin findById(Integer id) {
        Admin admin = null;
        String req = "select * from user where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin = new Admin(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admin;
    }

}
