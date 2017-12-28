/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import models.*;
import services.*;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MyactualitiesController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private TableView<Actualite> myacts;
    @FXML
    private TableColumn<Actualite, Date> dateacts;
    @FXML
    private TableColumn<Actualite, String> aboutacts;
    @FXML
    private TableColumn<Actualite, String> postacts;
    @FXML
    private JFXTextField aboutmodify;
    @FXML
    private JFXTextArea postmodify;

    static int idTemp;
    @FXML
    private JFXButton updateact;
    @FXML
    private JFXButton deleteact;
    @FXML
    private JFXButton retmypost;
    @FXML
    private ImageView retacts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshacts();

    }

    @FXML
    private void displayfeed(MouseEvent event) {
        //panemodify.setVisible(true);
        int select = myacts.getSelectionModel().getSelectedIndex();
        ActualiteService as = new ActualiteService();
        List<Actualite> act = as.getActualitiesByUser(UserService.idlogger);

        String contenue = act.get(select).getContenu();
        String rubrique = act.get(select).getRubrique();
        idTemp = act.get(select).getId();

        postmodify.setText(contenue);
        aboutmodify.setText(rubrique);
    }

    @FXML
    private void updatefeed(ActionEvent event) {
        String post = postmodify.getText();
        String about = aboutmodify.getText();
        User user = new UserService().findById2(UserService.idlogger);
        Actualite a = new Actualite(idTemp, "default", about, post, user);
        ActualiteService actualiteService = new ActualiteService();
        actualiteService.update(a);
        refreshacts();

    }

    @FXML
    private void deletefeed(ActionEvent event) {
        ActualiteService actualiteService = new ActualiteService();
        actualiteService.remove(idTemp);
        refreshacts();
    }

    public void refreshacts() {
        ActualiteService actualiteService = new ActualiteService();
        List<Actualite> actualites = new ArrayList<>();

        actualites = actualiteService.getActualitiesByUser(UserService.idlogger);/*changer 6 par la variable static*/

        System.out.println(actualites);

        ObservableList<Actualite> data = FXCollections.observableArrayList(actualites);
        dateacts.setCellValueFactory(
                new PropertyValueFactory<>("dateActualite")
        );
        aboutacts.setCellValueFactory(
                new PropertyValueFactory<>("rubrique")
        );
        postacts.setCellValueFactory(
                new PropertyValueFactory<>("contenu")
        );

        myacts.setItems(data);

    }



    @FXML
    private void returnfromact(MouseEvent event) {
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
