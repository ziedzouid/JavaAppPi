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
import java.util.List;
import interfaces.IEvaluationService;
import models.Evaluation;
import models.Passager;
import models.User;
;
import techniques.DataSource;
import utils.VariablesGlobale;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */


public class EvaluationService implements IEvaluationService {

    Connection connection;

    public EvaluationService() {
        connection = DataSource.getInsatance().getConnection();
    }

    //A revoir aprés l'integration des travaux//

    /*@Override
    public List<Evaluation> getByTrajet(Trajet trajet) {
        
        List<Evaluation> evaluations = new ArrayList<>();
        String req = "select * from evaluation where trajet_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, trajet.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evaluation evaluation;
                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new Trajet(), new User());// a verifier
                evaluations.add(evaluation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;
    }*/
    @Override
    public void add(Evaluation e) {
        String req = "insert into evaluation (note,date_evaluation,commentaire,trajet_id,user_id) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setByte(1, e.getNote());
            preparedStatement.setTimestamp(2, e.getMyDate());
            preparedStatement.setString(3, e.getCommentaire());
            preparedStatement.setInt(4, e.getTrajet().getId());//a modifier apres integration
            preparedStatement.setInt(5, e.getPassager().getId());//a modifier apres integration
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //////////// recuperation d'auto increment id poste
        String req1 = "select last_insert_id() as last_id from evaluation";
        PreparedStatement preparedStatement1;
        try {
            preparedStatement1 = connection.prepareStatement(req1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                e.setId(resultSet.getInt("last_id"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Evaluation> getAll() {
        List<Evaluation> evaluations = new ArrayList<>();
        String req = "select * from evaluation";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evaluation evaluation;
                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 
                evaluations.add(evaluation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;

    }

    @Override
    public void update(Evaluation e) {
        String req = "update evaluation set note=?,date_evaluation=?,commentaire=?,trajet_id=?,user_id=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setByte(1, e.getNote());
            preparedStatement.setTimestamp(2, e.getMyDate());
            preparedStatement.setString(3, e.getCommentaire());
            preparedStatement.setInt(4, e.getTrajet().getId());//a modifier aprs integration
            preparedStatement.setInt(5, e.getPassager().getId());//a modifier aprs integration
            preparedStatement.setInt(6, e.getId());          //              a changer
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from evaluation where id =?";
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
    public Evaluation findById(Integer id) {
        Evaluation evaluation = null;

        String req = "select * from evaluation where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluation;
    }

    @Override
    public List<Evaluation> getByTrajet(Integer r) {
        List<Evaluation> evaluations = new ArrayList<>();
        String req = "select * from evaluation where trajet_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evaluation evaluation;
                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 
                evaluations.add(evaluation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;
    }

    @Override
    public List<Evaluation> getByUser(Integer r) {
        List<Evaluation> evaluations = new ArrayList<>();
        String req = "select * from evaluation where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evaluation evaluation;
                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 
                evaluations.add(evaluation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;
    }

    @Override
    public Evaluation SearchByTrajetAndUserId(Integer r, Integer r1) {
        Evaluation evaluation = null;

        String req = "select * from evaluation where user_id=? and trajet_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(2, r);
            preparedStatement.setInt(1, r1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluation;
    }

    @Override
    public List<Evaluation> getByConduteur() {
        List<Evaluation> evaluations = new ArrayList<>();
        String req = "select * from evaluation ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            // preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evaluation evaluation;
                evaluation = new Evaluation(resultSet.getInt(1), resultSet.getByte(2), resultSet.getTimestamp(3), resultSet.getString(4), new TrajetService().findById(resultSet.getInt(5)), new PassagerService().findById(resultSet.getInt(6)));// a modifier apres integration 
                if (evaluation.getTrajet().getId_user()==VariablesGlobale.PassagerCourant.getId()) { // a changer  apres integration 
                    
                    evaluations.add(evaluation);
                    System.out.println(evaluation);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;
    }

}
