/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ProduitController implements Initializable {

    @FXML
    private TableView<?> tableproduct;
    @FXML
    private JFXButton addprod;
    @FXML
    private JFXButton removeprod;
    @FXML
    private JFXButton editprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addproduct(ActionEvent event) {
    }

    @FXML
    private void deleteproduct(ActionEvent event) {
    }

    @FXML
    private void editproduct(ActionEvent event) {
    }
    
}
