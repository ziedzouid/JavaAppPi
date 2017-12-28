/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import models.Evenement;
import services.EvenementServices;
import tray.notification.TrayNotification;
import utils.Validation;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EventconduController implements Initializable {

    @FXML
    private TableView<Evenement> Treeview1;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TextField typeinput;
    @FXML
    private JFXButton AjouterEvent;
    @FXML
    private JFXButton Modifier;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXTextField descriptioninput;
    @FXML
    private JFXButton retour_menu;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Treeview1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fillTable();
    }

    public void fillTable() {
        EvenementServices E = new EvenementServices();
        ObservableList<Evenement> events = FXCollections.observableArrayList(E.getAll());
        type.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("contenu"));

        Treeview1.setItems(events);
        Treeview1.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Evenement> observable,
                        Evenement oldValue,
                        Evenement newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    geteventInfo(newValue.getType(), newValue.getContenu());
                });
        geteventvide("", "");
    }

    public void geteventInfo(String type, String contenu) {
        typeinput.setText(type);
        descriptioninput.setText(contenu);

    }

    public void geteventvide(String type, String contenu) {
        type = "";
        contenu = "";
        typeinput.setText(type);
        descriptioninput.setText(contenu);

    }

    @FXML
    private void AjouterEvent(ActionEvent event) {
        EvenementServices e = new EvenementServices();
        Evenement m = new Evenement();
        m.setType(typeinput.getText());
        m.setContenu(descriptioninput.getText());

        if (descriptioninput.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("description empty!");
            alert.showAndWait();
            return;
        }
        if (typeinput.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("type empty!");
            alert.showAndWait();
            return;
        }

        if (Validation.containsAlphabetOnly(typeinput.getText()) == false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("num in type !");
            alert.showAndWait();
            return;
        }
        
        if (Validation.containsAlphabetOnly(descriptioninput.getText()) == false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("num in description !!");
            alert.showAndWait();
            return;
        }

        e.add(m);
        fillTable();
        File file = new File("src/ressources/check.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("event Aded");
        tray.setMessage("bravo ");
        tray.setImage(img);
        tray.showAndWait();
    }

    @FXML
    private void Modifierevent(ActionEvent event) {
        EvenementServices m = new EvenementServices();
        Evenement ev = new Evenement();
        ev = Treeview1.getSelectionModel().getSelectedItem();
        ev.setType(typeinput.getText());
        ev.setContenu(descriptioninput.getText());
        m.update(ev);
        fillTable();
        ev.setType(typeinput.getText());
        ev.setContenu(descriptioninput.getText());
        /**
         * ****************Notification modof****************
         */
        File file = new File("src/ressources/modifier.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Event edited");
        tray.setMessage("Bravo !!!!");
        tray.setImage(img);
        tray.showAndWait();
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) {
        Evenement e = new Evenement();
        e = Treeview1.getSelectionModel().getSelectedItem();

        EvenementServices ev = new EvenementServices();
        ev.remove(e.getId());
        fillTable();
        /**
         * ****************Notification supp****************
         */
        File file = new File("src/ressources/Deleted.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Event deleted");
        tray.setMessage("Bravo!!!!!");
        tray.setImage(img);
        tray.showAndWait();
        tray.dismiss();
    }

    @FXML
    private void retour_menuCond(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
        root.getChildren().setAll(pane);
    }

}
