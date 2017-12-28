/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox boxMenus;
    @FXML
    private AnchorPane paneUsers;
    @FXML
    private AnchorPane paneFeeds;
    @FXML
    private AnchorPane trajetadmin;
    @FXML
    private AnchorPane CirclesAdmin;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private Label namelogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpFabs();
        ScaleTransition transition2 = new ScaleTransition(Duration.seconds(4), namelogin);
        transition2.setToX(2);
        transition2.setToY(2);
        transition2.setCycleCount(Timeline.INDEFINITE);
        transition2.setAutoReverse(true);
        transition2.play();
        // TODO
    }

    @FXML
    private void switchToEspaceAdmin(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/Adminspace.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToFeeds(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/feedbacksAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            //  Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void manageTrajets(MouseEvent event) {

    }

    private void manageCircle(MouseEvent event) {

    }

    private void setUpFabs() {
        //Setting up icons for button
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CIRCLE_ALT_NOTCH);
        icon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

        FontAwesomeIconView closeicon = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        closeicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");
        FontAwesomeIconView logicon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK_ALT);
        logicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

        JFXButton button1 = new JFXButton();
        Label label1 = new Label("G1");
        button1.setGraphic(icon);
        label1.setStyle("-fx-text-fill:WHITE");
        button1.setButtonType(JFXButton.ButtonType.RAISED);
        button1.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");

        JFXButton button2 = new JFXButton();
        button2.setTooltip(new Tooltip("Log off"));
        button2.setButtonType(JFXButton.ButtonType.RAISED);
        button2.setGraphic(logicon);
        button2.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");
        button2.setOnAction((ActionEvent event) -> {
            try {
                paneUsers.getScene().getWindow().hide();
                Parent rood = FXMLLoader.load(getClass().getResource("/Views/Login_1.fxml"));
                Scene scene = new Scene(rood);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } catch (IOException ex) {
                //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXButton button3 = new JFXButton();
        button3.setButtonType(JFXButton.ButtonType.RAISED);
        button3.setTooltip(new Tooltip("Exit"));
        button3.setGraphic(closeicon);
        button3.setStyle("-fx-pref-width:30px;-fx-background-color:#F87951;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");
        button3.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button1, (expanded) -> {
            return new ArrayList<KeyValue>() {
                {
                    //add(new KeyValue(button1.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                }
            };
        });
        nodesList.addAnimatedNode(button2);
        nodesList.addAnimatedNode(button3);

        fabsContainer.getChildren().add(nodesList);

    }

    @FXML
    private void manageCircleadmin(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/Circle.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            //  Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
