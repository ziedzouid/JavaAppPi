/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuVersionEssai implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox boxMenus;
    @FXML
    private AnchorPane paneUsers;
    @FXML
    private AnchorPane feedessayadmin;
    @FXML
    private AnchorPane feedessayeclient;
    @FXML
    private AnchorPane actsessaye;
    @FXML
    private StackPane fabsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void switchToEspaceAdmin(MouseEvent event) {
        
        

    }

    @FXML
    private void consultfeeds(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/feedbacksAdmin.fxml"));

            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
           // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void makefeeed(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/feedbacksClient.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewactsclient(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/actualities.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
