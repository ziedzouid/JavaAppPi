/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IProduitService;
import models.Produit;
import techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azzen Abidi <azzendotabidiatespritdottn>
 */
public class ProduitService implements IProduitService {

    Connection connection;
    private PreparedStatement pst;

    public ProduitService() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Produit t) {
        String req = "insert into produit (type,poids) values (?,?)";

        try {
            pst = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getType());
            pst.setString(2, t.getPoids());
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
    public void update(Produit t) {
        String req = "update produit set destination=?,contenu=?,user_id=? where id = ?";

        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getType());
            pst.setString(2, t.getPoids());
            pst.setInt(3, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Integer id) {
        String req = "delete from produit where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Produit findById(Integer id) {
        Produit produit = null;
        String req = "select * from produit where id =?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                produit = new Produit(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produit;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produit";
        try {
            pst = connection.prepareStatement(req);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {

                Produit produit = new Produit(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                produits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }
}
