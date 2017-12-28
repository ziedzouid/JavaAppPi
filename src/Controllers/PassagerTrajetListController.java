/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import java.awt.event.KeyEvent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Session;
import models.Trajet;
import services.TrajetService;
import services.UserService;
import utils.VariablesGlobale;

/**
 * FXML Controller class
 *
 * @author achraf
 */
public class PassagerTrajetListController implements Initializable, MapComponentInitializedListener {

    private IntegerProperty index = new SimpleIntegerProperty();
    @FXML
    private ImageView imgBack;
    @FXML
    private TableColumn<Trajet, String> colVilleDepart, colVilleArrive, colDateDepart,
            colNbrePersonne, colPrix, colDateAnnonce;
    @FXML
    private TableView<Trajet> tableviewTrajet;
    private ObservableList<Trajet> listTrajet;

    GoogleMap map;
    @FXML
    GoogleMapView mapView;

    Trajet trajet = new Trajet();
    TrajetService serviceTrajet = new TrajetService();

    @FXML
    TextField search;

    /**
     *
     * Initializes the controller class.
     */
    private void columnTrajet() {
        colVilleDepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        colVilleArrive.setCellValueFactory(new PropertyValueFactory<>("ville_arrive"));
        colDateDepart.setCellValueFactory(new PropertyValueFactory<>("date_trajet"));
        colNbrePersonne.setCellValueFactory(new PropertyValueFactory<>("nombre_place"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        colDateAnnonce.setCellValueFactory(new PropertyValueFactory<>("date_annonce"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mapView.addMapInializedListener(this);
        columnTrajet();
        listTrajet = FXCollections.observableArrayList(serviceTrajet.getAll());
        tableviewTrajet.setItems(listTrajet);

        tableviewTrajet.setEditable(true);
        colVilleDepart.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void search(KeyEvent ke) {
        FilteredList<Trajet> filterData = new FilteredList<>(listTrajet, p -> true);

        search.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(trajet -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }

                String typedText = newvalue.toLowerCase();
                if (trajet.getVille_depart().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (trajet.getVille_arrive().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                /*
                if (trajet.getNombre_place().indexOf(typedNumber) != -1) {
                    return true;
                }*/

                return false;
            });
            SortedList<Trajet> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableviewTrajet.comparatorProperty());
            tableviewTrajet.setItems(sortedList);

        });

    }

    @FXML
    private void boutonRefresh(ActionEvent event) {
        listTrajet = FXCollections.observableArrayList(serviceTrajet.getAll());
        tableviewTrajet.setItems(listTrajet);
        tableviewTrajet.refresh();
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void changeVilleDepartCellEvent(CellEditEvent edittedCell) {
        /*Trajet trajetSelected = tableviewTrajet.getSelectionModel().getSelectedItem();
    trajetSelected.setVille_depart(edittedCell.getNewValue().toString());*/

    }

    @Override
    public void mapInitialized() {
        // -------------------------- Debut Affichage -----------------//
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.8991156, 10.1901885))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(true)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(10);

        map = mapView.createMap(mapOptions);

        //Add markers to the map
        // Creation d'un objet qui contient les coordonnees 
        //To change body of generated methods, choose Tools | Templates.
    }

    public void addMarker(float lat, float lng) {
        LatLong joeSmithLocation = new LatLong(lat, lng);
        // Creation d'un objet qui contient les options/proprietes/config d'un marqueur de position
        MarkerOptions markerOptions = new MarkerOptions();
        // Modification des parametres du marqueur (Affectation de la position
        markerOptions.position(joeSmithLocation);

        // Creation du marquer avec la configuration defini dans l'objet du type MarkerOptions
        Marker markerTrajet = new Marker(markerOptions);

        // L'ajout du marqueur à la map
        map.addMarker(markerTrajet);
    }

    @FXML
    private void afficher_evaluations_du_trajet(MouseEvent event) throws IOException {
         if ( event.getClickCount() == 2) {
            System.out.println(tableviewTrajet.getSelectionModel().getSelectedItem());  
             System.out.println(VariablesGlobale.PassagerCourant);
             System.out.println(UserService.idlogger);
             VariablesGlobale.TrajetSelectionne=tableviewTrajet.getSelectionModel().getSelectedItem();
              Parent root = FXMLLoader.load(getClass().getResource("/Views/EvaluationsDuTrajetFXML.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
             
             
             
        }

    }
}
