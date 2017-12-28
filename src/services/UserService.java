/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IUserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Admin;
import models.Conducteur;
import models.Passager;
import models.User;
import static services.UserService.idlogger;
import techniques.DataSource;
import utils.VariablesGlobale;

/**
 *
 * @author esprit
 */
public class UserService implements IUserService {

    public static int idlogger;

    Connection connection;
    private PreparedStatement pst;

    public UserService() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(User u) {
        String req = "insert into user(nom,prenom,sexe,date_naissance,telephone,email,password,avatar,role,circle_id) values (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getPrenom());
            preparedStatement.setString(3, u.getSexe());
            java.sql.Date sqlDate = new java.sql.Date(u.getDate_naissance().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, u.getTel());
            preparedStatement.setString(6, u.getEmail());
            preparedStatement.setString(7, u.getMdp());
            preparedStatement.setString(8, u.getAvatar());

            preparedStatement.setString(9, "Passager");
            preparedStatement.setInt(10, 1);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User u) {
        String req = "update user set nom=?,prenom=?,sexe=?,date_naissance=?,telephone=?,email=?,password=?,avatar=?,role=?,circle_id=? where id =?";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getPrenom());
            preparedStatement.setString(3, u.getSexe());
            preparedStatement.setDate(4, (java.sql.Date) u.getDate_naissance());
            preparedStatement.setInt(5, u.getTel());
            preparedStatement.setString(6, u.getEmail());
            preparedStatement.setString(7, u.getMdp());
            preparedStatement.setString(8, u.getAvatar());
            preparedStatement.setString(9, "Passager");
            preparedStatement.setInt(10, 1);
            preparedStatement.setInt(11, u.getId());
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
    public User findById(Integer id) {
        User user = null;
        String req = "select * from user where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String req = "select * from user  ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u;
                u = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public User findUser(String mail) {

        User user = null;
        String req = "select * from user where email_canonical=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;

    }

    public boolean exist(String s) {
        String req = "select * from user where email_canonical=?  ";

        //List<Admin> Admins = new ArrayList<>();
        try {
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, s);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int verif(String s, String r) throws SQLException {
        String req = "select * from user where (email_canonical=?and password=?)  ";
        Admin a = null;
        Conducteur c = null;
        Passager p = null;
        //List<Admin> Admins = new ArrayList<>();
        try {
            PreparedStatement pst;
            pst = connection.prepareStatement(req);

            pst.setString(2, r);
            pst.setString(1, s);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                if ((resultSet.getString(20)).equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    a = new Admin(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
                    {
                        idlogger = a.getId();

                        VariablesGlobale.PassagerCourant = VariablesGlobale.ipassagerService.findById(idlogger);
                        System.out.println(VariablesGlobale.PassagerCourant);
                        return 1;
                    }

                } else if ((resultSet.getString(20)).equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                    p = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    idlogger = p.getId();
                    VariablesGlobale.PassagerCourant = VariablesGlobale.ipassagerService.findById(idlogger);
                    System.out.println(idlogger);
                    System.out.println(VariablesGlobale.PassagerCourant);
                    return 2;
                } else if ((resultSet.getString(20)).equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                    c = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    idlogger = c.getId();
                    VariablesGlobale.ipassagerService.findById(idlogger);
                    return 3;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;

    }

    public String returnRole(String s, String r) throws SQLException {

        String req = "select * from user where (email_canonical=? and password=?)  ";
        Admin a = null;
        Conducteur c = null;
        Passager p = null;
        //List<Admin> Admins = new ArrayList<>();
        try {
            PreparedStatement pst;
            pst = connection.prepareStatement(req);

            pst.setString(2, r);
            pst.setString(1, s);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                if ((resultSet.getString(20)).equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    a = new Admin(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
                    {
                        return (resultSet.getString(20));
                    }

                } else if ((resultSet.getString(20)).equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                    p = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    idlogger = p.getId();
                    System.out.println(idlogger);
                    return (resultSet.getString(20));
                } else if ((resultSet.getString(20)).equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                    c = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    idlogger = c.getId();
                    return (resultSet.getString(20));
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "introuvable";
    }


    /*Methodes implémenté par zied*/
    @Override
    public User findById2(Integer id) {
        User user = null;
        String req = "select * from user where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                String role = "";
                role = getRoleById2(id);
                if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                    user = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    return user;
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                    user = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    return user;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll2() {
        List<User> users = new ArrayList<>();
        String req = "select * from user ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = null;
                String role = "";
                int id = resultSet.getInt("id");
                role = getRoleById2(id);
                if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                    user = new Passager(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    users.add(user);
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                    user = new Conducteur(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), new CircleService().findById(1));
                    users.add(user);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public String getRoleById2(Integer id) {
        String role = "";
        String req = "select * from user where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                role = resultSet.getString(20);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }

}
