/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import models.*;
import services.*;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FeedbacksAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Feedback> feedbacksview;
    @FXML
    private TableColumn<Feedback, Date> datefeedback;
    @FXML
    private TableColumn<Feedback, String> statusfeed;
    @FXML
    private TextFlow claimview;
    @FXML
    private JFXButton deletefeedback;
    @FXML
    private JFXButton deleteallfeeds;
    @FXML
    private TableView<tempnom> nametabfeed;
    @FXML
    private TableColumn<tempnom, String> namefeedsadmin;

    public static int idfeedtemp;
    int iduser = UserService.idlogger;
    @FXML
    private JFXButton retfeedad;
    @FXML
    private ImageView retfeed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshfeeds();
    }

    @FXML
    private void deletefeedadmin(ActionEvent event) {
        FeedbackService fs = new FeedbackService();
        fs.remove(idfeedtemp);
        refreshfeeds();
        claimview.getChildren().clear();

    }

    @FXML
    private void deleteallfeeds(ActionEvent event) {
        FeedbackService fs = new FeedbackService();
        fs.removeall();
        refreshfeeds();
        claimview.getChildren().clear();
    }

    @FXML
    private void displayclaim(MouseEvent event) {
        int select = feedbacksview.getSelectionModel().getSelectedIndex();
        FeedbackService fs = new FeedbackService();
        List<Feedback> feedbacks = fs.getAll();

        String claim = feedbacks.get(select).getContenu();

        System.out.println(claim);

        idfeedtemp = feedbacks.get(select).getId();
        Text text = new Text(claim);

        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        claimview.getChildren().clear();
        claimview.getChildren().add(text);

    }

    @FXML
    private void returnfromfeedad(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuVersionEssai.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshfeeds() {
        FeedbackService fs = new FeedbackService();
        List<Feedback> feedbacks = new ArrayList<>();

        feedbacks = fs.getAll();

        List<tempnom> l = new ArrayList<>();
        for (Feedback s : feedbacks) {
            //System.out.println(1);
            System.out.println(s.getUser().getId());
            User user = new UserService().findById2(s.getUser().getId());
            // System.out.println(user);
            tempnom tp = new tempnom();
            tp.setNom(user.getNom());
            l.add(tp);
        }

        // System.out.println(fs);
        ObservableList<Feedback> data = FXCollections.observableArrayList(feedbacks);

        ObservableList<tempnom> data2 = FXCollections.observableArrayList(l);

        datefeedback.setCellValueFactory(
                new PropertyValueFactory<>("dateFeedback")
        );
        statusfeed.setCellValueFactory(
                new PropertyValueFactory<>("titre")
        );

        namefeedsadmin.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        nametabfeed.setItems(data2);

        feedbacksview.setItems(data);
    }

    @FXML
    private void returnfromfeedsadmin(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
