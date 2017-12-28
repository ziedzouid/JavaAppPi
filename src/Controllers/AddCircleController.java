/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Circle;
import services.CircleService;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AddCircleController implements Initializable {

    @FXML
    private JFXTextField circlename;
    @FXML
    private JFXTextField circletype;
    @FXML
    private JFXTextField descriptioncircle;
    @FXML
    private JFXButton addCircle;

    CircleService cs = new CircleService();
    @FXML
    private Button retaddcr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addCircle(ActionEvent event) {
        String th = circlename.getText();
        String cn = circletype.getText();
        String desc = descriptioncircle.getText();
        Circle c = new Circle(th, cn, desc);
        cs.add(c);
    }

    @FXML
    private void returnfromaddcr(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenuConducteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
