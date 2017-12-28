/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static jdk.nashorn.internal.objects.NativeJava.type;
import models.Produit;
import services.ProduitService;



/**
 * FXML Controller class
 *
 * @author esprit
 */


public class AddProduitController implements Initializable {

    @FXML
    private JFXButton AddProduct;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXTextField Type;
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void AddProduct(ActionEvent event) {
        String wgt = weight.getText();
        String tp = Type.getText();

        Produit p = new Produit(wgt, tp);
        ps.add(p);
    }
}
