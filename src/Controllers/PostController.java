/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextArea;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Passager;
import models.Poste;
import models.User;
import services.PassagerService;
import services.PosteService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class PostController extends Application {

    @FXML
    private JFXTextArea text;
    @FXML
    private Label passenger;
    @FXML
    private Button retfrompassmenu;
    @FXML
    private ImageView fbicon;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void poster(ActionEvent event) throws SQLException {
        String pub = text.getText();
        Poste p = null;
        User u = null;
        Passager passager = null;
        UserService us = new UserService();
        PassagerService ps = new PassagerService();
        u = us.findById(UserService.idlogger);
        String role = us.returnRole(u.getEmail(), u.getMdp());
        if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
            Timestamp date = Timestamp.valueOf(LocalDateTime.now());
            passager = ps.findById(UserService.idlogger);
            p = new Poste(pub, date, passager);
            PosteService sp = new PosteService();
            sp.add(p);
        } else {
            passenger.setVisible(true);
            passenger.setText("you are not a Passenger");
        }

    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/Post.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void returntomenupass(ActionEvent event) throws IOException {
      
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node) event.getSource()).getScene().getWindow(); 
        s.setScene(scene);
        s.show();
        
        
    }
    

    @FXML
    private void returnfrompostpass(MouseEvent event) {
    }

    @FXML
    private void facebookshare(MouseEvent event) {
        String accessToken = "EAACEdEose0cBAKwBR9Fu3yyepiCk2eTLbEqNYlGJNGdwKK7fYe4U6Y74DklXKCkd5xLYKKBcEWPeM7ZBO0ZCSP5LjQ2L4j3TGxgcH7v9xzx0Bdep0rXazQ9BmNe2W8de4nWH9bAS7SrX3dvUgoE9Tl12cBJIbZC9MLt1lauMaOsuz7wdMMAIJQFgZAaHGAvQw1ZCDgULe0AZDZD";
      FacebookClient fbClien = new DefaultFacebookClient(accessToken);
      FacebookType response = fbClien.publish("me/feed",FacebookType.class,Parameter.with("message", text.getText()));
        
    }


    /**
     * @param args the command line arguments
     */

}
