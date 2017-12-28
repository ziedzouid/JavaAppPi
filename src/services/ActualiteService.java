/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IActualiteService;
import interfaces.IFeedbackService;
import models.Actualite;
import models.Feedback;
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
 * @author ASUS
 */
public class ActualiteService implements IActualiteService {

    Connection connection;
    private PreparedStatement pst;

    public ActualiteService() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Actualite a) {
        String req = "insert into actualite (type,rubrique,contenu,user_id,circle_id) values (?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, a.getType());
            pst.setString(2, a.getRubrique());
            pst.setString(3, a.getContenu());
            pst.setInt(4, a.getUser().getId());
            String role = "";
            UserService us = new UserService();
            //System.out.println(a.getUser().getId());
            role = us.getRoleById2(a.getUser().getId());
            if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                Passager p = (Passager) a.getUser();
                System.out.println(p.getId());
                pst.setInt(5, p.getCircle().getId());
            }
            if (role.equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                Conducteur c = (Conducteur) a.getUser();
                pst.setInt(5, c.getCircle().getId());
            }
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs != null && rs.first()) {
                int generatedId = rs.getInt(1);
                a.setId(generatedId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Actualite a) {

        String req = "update actualite set rubrique=?,contenu=?,user_id=? where id = ?";

        try {
            pst = connection.prepareStatement(req);
           // pst.setString(1, a.getType());
            pst.setString(1, a.getRubrique());
            pst.setString(2, a.getContenu());
            pst.setInt(3, a.getUser().getId());
            pst.setInt(4, a.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from actualite where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Actualite findById(Integer id) {
        Actualite actualite = null;
        User user = null;
        String req = "select * from actualite where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                user = new UserService().findById(resultSet.getInt(6));
                actualite = new Actualite(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), date, user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualite;

    }

    @Override
    public List<Actualite> getAll() {
        List<Actualite> actualites = new ArrayList<>();
        User user = null;
        String req = "select * from actualite";
        try {
            pst = connection.prepareStatement(req);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                //java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
                user = new UserService().findById(resultSet.getInt(6));
                Actualite actualite = new Actualite(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getDate(4), user);
                actualites.add(actualite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualites;
    }

    @Override
    public List<Actualite> getActualitiesByUser(Integer userid) {
        List<Actualite> actualites = new ArrayList<>();
        User user = null;
        String req = "select * from actualite where user_id=? ORDER BY id DESC ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new UserService().findById(resultSet.getInt(6));
                Actualite actualite = new Actualite(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getDate(4), user);
                actualites.add(actualite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualites;
    }

    @Override
    public List<Actualite> getActualitiesByCircle(Integer circleid) {
        List<Actualite> actualites = new ArrayList<>();
        User user = null;

        String req = "select * from actualite where circle_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, circleid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new UserService().findById(resultSet.getInt(6));
                Actualite actualite = new Actualite(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getDate(4), user);
                actualites.add(actualite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualites;

    }

    public List<String> getActnameByCircle(int circleid) {
        List<String> actualites = new ArrayList<>();

        String req = "select * from actualite INNER JOIN user on actualite.user_id = user.id where actualite.circle_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, circleid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rtt = resultSet.getString("prenom") + " " + resultSet.getString(9) + " posted a new actuality" + " about " + resultSet.getString(3) + " at " + resultSet.getString(4);
                actualites.add(rtt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualites;

    }

}
