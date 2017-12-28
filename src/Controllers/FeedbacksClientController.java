/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import models.*;
import services.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomPasswordField;
import utils.Mailing;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FeedbacksClientController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private RadioButton ex1;
    @FXML
    private RadioButton ve1;
    @FXML
    private RadioButton mo1;
    @FXML
    private RadioButton ex2;
    @FXML
    private RadioButton ve2;
    @FXML
    private RadioButton mo2;
    @FXML
    private RadioButton ex3;
    @FXML
    private RadioButton ve3;
    @FXML
    private RadioButton mo3;
    @FXML
    private JFXButton submitfeed;
    @FXML
    private JFXTextArea claimclient;
    @FXML
    private ToggleGroup quest1;
    @FXML
    private ToggleGroup quest2;
    @FXML
    private ToggleGroup quest3;
    @FXML
    private JFXButton retfeedcln;

    @FXML
    private CustomPasswordField mailpass;
    @FXML
    private JFXButton sendclaim;

    String claim;
    @FXML
    private ImageView retfeedscl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void submitfeedclient(ActionEvent event) {
        int a1 = 0, a2 = 0, a3 = 0;
        int sum = 0;
        if (ex1.isSelected()) {
            a1 = 3;
        } else if (ve1.isSelected()) {
            a1 = 2;
        } else if (mo1.isSelected()) {
            a1 = 1;
        }

        if (ex2.isSelected()) {
            a2 = 3;
        } else if (ve2.isSelected()) {
            a2 = 2;
        } else if (mo2.isSelected()) {
            a2 = 1;
        }

        if (ex3.isSelected()) {
            a3 = 3;
        } else if (ve3.isSelected()) {
            a3 = 2;
        } else if (mo3.isSelected()) {
            a3 = 1;
        }
        sum = a1 + a2 + a3;
        // System.out.println(sum);

        this.claim = claimclient.getText();
        String etat = "";
        FeedbackService fs = new FeedbackService();

        if (sum == 9) {
            //System.out.println("extremly");
            etat = "Extremly satisfied";
        } else if (sum < 9 && sum >= 6) {
            //System.out.println("Very");
            etat = "Very satisfied";
        } else {
            //System.out.println("Moderatly");
            etat = "Moderatly satisfied";
        }

        Feedback f = new Feedback(etat, claim, new UserService().findById2(UserService.idlogger));/*le user id a changer apres integration */
        fs.add(f);

  
    }

 

    @FXML
    private void sendclaimbymail(ActionEvent event) {
        Mailing mailing = new Mailing();
        UserService us = new UserService();
        User u = us.findById2(UserService.idlogger);

        mailpass.getText();
        
        System.out.println(u.getEmail()+" "+claimclient.getText()+" "+ mailpass.getText());
        mailing.SendMailGmail(u.getEmail(), claimclient.getText(), mailpass.getText());
    }

    @FXML
    private void retfromfeedscln(MouseEvent event) {
        
        try {
            String role = new UserService().getRoleById2(UserService.idlogger);
            Parent root;
            if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            } else if (role.equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            }
        } catch (IOException ex) {
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
