///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///*package services;
//
//import interfaces.ILivraisonService;
//import models.Livraison;
//import techniques.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
//  @author Azzen Abidi <azzendotabidiatespritdottn>
// */
//// fix date error in all methods 
//public class LivraisonService implements ILivraisonService {
//
//    Connection connection;
//    private PreparedStatement pst;
//
//    public LivraisonService() {
//        connection = DataSource.getInsatance().getConnection();
//
//    }
//
//    @Override
//    public void add(Livraison t) {
//        String req = "insert into livraison (dateLivraison) values (?)";
//
//        try {
//            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
//           // pst.setString(1, t.getDateLivraison());
//            pst.executeUpdate();
//            ResultSet rs = pst.getGeneratedKeys();
//            if (rs != null && rs.first()) {
//                int generatedId = rs.getInt(1);
//                t.setId(generatedId);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(Livraison t) {
//        String req = "update livraison set dateLivraison=? where id = ?";
//
//        try {
//            pst = connection.prepareStatement(req);
//         //   pst.setString(1, t.getDateLivraison());
//            pst.setInt(2, t.getId());
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void remove(Integer id) {
//        String req = "delete from livraison where id =?";
//        try {
//            pst = connection.prepareStatement(req);
//            pst.setInt(1, id);
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public Livraison findById(Integer id) {
//           Livraison livraison = null;
//        String req = "select * from livraison where id =?";
//        try {
//            pst = connection.prepareStatement(req);
//            pst.setInt(1, id);
//            ResultSet resultSet = pst.executeQuery();
//            while (resultSet.next()) {
//                java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
//               // livraison = new Livraison(resultSet.getInt(1), resultSet.getString(3), date,resultSet.getString(2),resultSet.getInt(5));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return livraison;  
//    }
//
//    // @Override
//  /*  public List<Livraison> getAll() {
//            List<Livraison> livraisons = new ArrayList<>();
//        String req = "select * from livraison";
//        try {
//            pst = connection.prepareStatement(req);
//            ResultSet resultSet = pst.executeQuery();
//            while (resultSet.next()) {
//           java.sql.Date date = new java.sql.Date(resultSet.getTimestamp(4).getTime());
//                
//             //   Livraison livraison = new Livraison(resultSet.getInt(1), resultSet.getString(3), date,resultSet.getString(2),resultSet.getInt(5));
//                //livraisons.add(livraison);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return livraisons;  
//    }*/
//}
//
//*/