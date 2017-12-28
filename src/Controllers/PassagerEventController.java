/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Evenement;
import services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PassagerEventController implements Initializable {

    @FXML
    private JFXButton ListeEvent;
    @FXML
    private AnchorPane liste;
    @FXML
    private JFXButton favourite;
    @FXML
    private JFXButton HelpEvent;
    @FXML
    private AnchorPane scenefavourite;
     @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement,String> description;
    @FXML
    private TableView<Evenement> TableView2;
    @FXML
    private AnchorPane heplanchore;
    @FXML
    private JFXButton retour_menu;
    @FXML
    private AnchorPane rootPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        TableView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fillTable();
    }   
     public void fillTable() {
        EvenementServices E = new EvenementServices();
        ObservableList<Evenement> events = FXCollections.observableArrayList(E.getAll());
        type.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("contenu"));

        TableView2.setItems(events);
        
    }    

    @FXML
    private void AffichageListeEvent(ActionEvent event) throws IOException {
        if (event.getSource()== ListeEvent )
{
             liste.setVisible(true);
             heplanchore.setVisible(false);

              scenefavourite.setVisible(false);
}
       
       
        
    }

    @FXML
    private void favourite(ActionEvent event) {
        if (event.getSource()== favourite)
{
             liste.setVisible(false);
             heplanchore.setVisible(false);

              scenefavourite.setVisible(true);
}
         
    }

    @FXML
    private void HelpEvent(ActionEvent event) {
        if (event.getSource()== HelpEvent )
{
             liste.setVisible(false);
             scenefavourite.setVisible(false);

              heplanchore.setVisible(true);
}
        
    }

    @FXML
    private void retour_menu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
        rootPass.getChildren().setAll(pane);
        
    }
    
}
