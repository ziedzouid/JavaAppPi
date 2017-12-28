/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import utils.ConditionSaisie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import interfaces.ICircleService;
import interfaces.IPassagerService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.util.Duration;
import models.Passager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.CircleService;
import services.PassagerService;
import services.UserService;
import static services.UserService.idlogger;
import utils.VariablesGlobale;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class LoginController_1 implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton sign;
    @FXML
    private ImageView imgLogin;
    @FXML
    private JFXTextField mail;
    @FXML
    private PasswordField pass;
    @FXML
    private Label message;
    @FXML
    private Label namelogin;
    @FXML
    private JFXButton fb;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ScaleTransition transition = new ScaleTransition(Duration.seconds(4), imgLogin);
        transition.setToX(2);
        transition.setToY(2);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        ScaleTransition transition2 = new ScaleTransition(Duration.seconds(4), namelogin);
        transition2.setToX(2);
        transition2.setToY(2);
        transition2.setCycleCount(Timeline.INDEFINITE);
        transition2.setAutoReverse(true);
        transition2.play();

    }

    @FXML
    private void doLogin(ActionEvent event) throws IOException, SQLException {
        try {
            UserService us = new UserService();

            String email = mail.getText();
            String password = pass.getText();

            //   System.out.println(UserService.idlogger);
            //User u=new User();
            //idlogger=u.getId();
            if (1 == us.verif(email, password)) {
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } else if (2 == us.verif(email, password)) {
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } else if (3 == us.verif(email, password)) {
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } else {
                message.setVisible(true);
                message.setText("you are not a Member!");

            }
        } catch (IOException ex) {
            //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/signin.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(scene);
        s.show();

    }

    @FXML
    private void loginfb(ActionEvent event) throws IOException {
        String domain = "https://www.google.fr/";
        String appId = "283011245552244";
////      String authUrl ="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
////             + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
////             + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
////             + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
////             + "manage_pages,publish_actions,read_insights,user_friends,read_page_mailboxes,rsvp_event,pages_show_list,email";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain;

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String fblog = "facebook.com";
        CharSequence cs = fblog.subSequence(0, 11);
        while (true) {
            if (!driver.getCurrentUrl().contains(cs)) {
                String url = driver.getCurrentUrl();
                String accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", "id,last_name,name,first_name,gender,email,birthday"));

                //  System.out.println(user.getId() + user.getName() + user.getLastName() + user.getEmail() + user.getGender() + user.getLastName() + user);
                //  label.setText(user.getName());
                driver.quit();
                ICircleService circleService = new CircleService();
                String parts = user.getId().substring(10);
                System.out.println(user.getId());
                System.out.println(parts);
                System.out.println(Integer.parseInt(parts));
                System.out.println(user.getLastName());
                System.out.println(user.getFirstName());
                System.out.println(user.getGender());
                System.out.println(user.getBirthday());
                System.out.println(user.getEmail());
                System.out.println(circleService.findById(2).getId());
                System.out.println(Date.valueOf("1970-01-01"));
               /* 
                String string = user.getBirthday();
                String[] parts2 = string.split("/");
                String part1 = parts2[0];
                String part2 = parts2[1];
                String part3 = parts2[2];
                String f=(part3 + "-" + part2 + "-" + part1);
                System.out.println(f);
                //Date date = Date.valueOf(f);
                /*System.out.println(part3 + "-" + part2 + "-" + part1);
                //String mail;
                // d = user.getBirthday();
*/
                if (user.getEmail() == null) {
                    user.setEmail("email@email.com");
                } else {

                }
                 /*  String string = user.getBirthday();
                String[] parts2 = string.split("/");
                String part1 = parts2[0];
                String part2 = parts2[1];
                String part3 = parts2[2];
                user.setBirthday(part3 + "-" + part2 + "-" + part1);
                if (user.getBirthday() == null) {
                    user.setBirthday("1972/12/12");
                   
                   
                }*/
             
               /* Date date = Date.valueOf(part3 + "-" + part2 + "-" + part1);
                System.out.println(part3 + "-" + part2 + "-" + part1);*/
                

                Passager p = new Passager(Integer.parseInt(parts), user.getLastName(), user.getFirstName(), user.getGender(), Date.valueOf("1970-02-02"), 1, user.getEmail(), "none", "Avatar", circleService.findById(2));

                System.out.println(p);
                IPassagerService ips = new PassagerService();
                if (ips.findById(p.getId()) == null) {

                    ips.add(p);
                    idlogger = p.getId();
                    VariablesGlobale.ipassagerService.findById(idlogger);

                    //////
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                    Scene scene = new Scene(root);
                    Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    s.setScene(scene);
                    s.show();

                } else {
                    idlogger = p.getId();
                    VariablesGlobale.ipassagerService.findById(idlogger);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController_1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    s.setScene(scene);
                    s.show();
                }

                return;


           }

        }
    }

}
