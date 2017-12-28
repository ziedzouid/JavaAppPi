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
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AdminspaceController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> sex;
    @FXML
    private TableColumn<User, Date> datenaissance;
    @FXML
    private TableColumn<User, Integer> tel;
    @FXML
    private TableColumn<User, Integer> mail;
 ObservableList<User> listuser;
    @FXML
    private JFXButton close;
    @FXML
    private StackPane fabsContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UserService us=new UserService();
        us.getAll();
          listuser = FXCollections.observableArrayList(us.getAll());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(listuser);
         setUpFabs();
        // TODO   // TODO
    }    

    @FXML
    private void remove(ActionEvent event) {
                UserService us=new UserService();
        int x = table.getSelectionModel().getSelectedItem().getId();
        us.remove(x);
        us.getAll();
          listuser = FXCollections.observableArrayList(us.getAll());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(listuser); }

    private void gomenu(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Menus.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node) event.getSource()).getScene().getWindow(); 
        s.setScene(scene);
        s.show();
    }

    private void logout(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/login_1.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node) event.getSource()).getScene().getWindow(); 
        s.setScene(scene);
        s.show();
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
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("/Views/MenuAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        });

        JFXButton button3 = new JFXButton();
        button3.setButtonType(JFXButton.ButtonType.RAISED);
        button3.setTooltip(new Tooltip("Exit"));
        button3.setGraphic(closeicon);
        button3.setStyle("-fx-pref-width:30px;-fx-background-color:#F87951;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");
        button3.setOnAction((ActionEvent event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/Login_1.fxml"));
           
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
            } 
            catch (IOException ex) {
                Logger.getLogger(FormulaireeditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button1, (expanded) -> {
            return new ArrayList<KeyValue>() {
                {
                   // add(new KeyValue(button1.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                }
            };
        });
        nodesList.addAnimatedNode(button2);
        nodesList.addAnimatedNode(button3);

        fabsContainer.getChildren().add(nodesList);

    }

    
}
