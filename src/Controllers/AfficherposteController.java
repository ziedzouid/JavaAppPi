/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import models.Passager;
import models.Poste;
import models.User;
import services.PosteService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AfficherposteController implements Initializable {

    @FXML
    private TableView<Poste> tableposte;
    @FXML
    private TableColumn<Poste,  Timestamp> date;
    @FXML
    private TableColumn<Poste, String> content;
    @FXML
    private TableColumn<Poste, Passager> passager;
     ObservableList<Poste> listposte;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    PosteService ps=new PosteService();
        ps.getAll();
          listposte = FXCollections.observableArrayList(ps.getAll());
        date.setCellValueFactory(new PropertyValueFactory<>("date_poste"));
        content.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        passager.setCellValueFactory(new PropertyValueFactory<>("passager"));
        System.out.println("hello");
        System.out.println(tableposte.getSelectionModel().getSelectedItem());

        tableposte.setItems(listposte);    }    

    @FXML
    private void returnmenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node) event.getSource()).getScene().getWindow(); 
        s.setScene(scene);
        s.show();
    }
    
}
