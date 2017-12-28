/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import java.awt.event.KeyEvent;

import utils.ConditionSaisie;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
//import models.NavigationTrajet;
import models.Trajet;
import services.TrajetService;
import services.UserService;
import techniques.DataSource;

/**
 * FXML Controller class
 *
 * @author achraf
 */
public class TrajetListController implements Initializable {

    final Integer max_Lengh1 = 8;
    private IntegerProperty index = new SimpleIntegerProperty();
    @FXML
    private Button btn_map;
    @FXML
    private ImageView imgBack;
    @FXML
    private TableColumn<Trajet, String> colVilleDepart, colVilleArrive, colDateDepart,
            colNbrePersonne, colPrix, colDateAnnonce;
    @FXML
    private TableView<Trajet> tableviewTrajet;
    @FXML
    private ObservableList<Trajet> listTrajet;

    Trajet trajet = new Trajet();
    TrajetService serviceTrajet = new TrajetService();
//NavigationTrajet nav = new NavigationTrajet();
    @FXML
    TextField search;
    @FXML
    private JFXComboBox ville_depart;
    @FXML
    private JFXComboBox ville_arrive;
    @FXML
    private Label verifprix;
    @FXML
    private DatePicker date_trajet;

    @FXML
    private JFXComboBox nombre_place;
    @FXML
    private JFXTextField prix;
    @FXML
    private Button AjouterTrajet;
    public int idclr;
    TrajetService tr = new TrajetService();

    Connection c = DataSource.getInsatance().getConnection();
    PreparedStatement pst;
    ResultSet res;

    private ObservableList<Trajet> data = FXCollections.observableArrayList();

    /**
     *
     * Initializes the controller class.
     */
    private void columnTrajet() throws SQLException {
        /*
     data=FXCollections.observableArrayList();
         //   String sql="select * from produit where valid_prd=1";
            
            String sql="select * from trajet where user_id=1"; //"+idUser
            
            res=c.createStatement().executeQuery(sql);
            while (res.next()){
                // lahné f res.getX("Attribut eli mwjoud fl base de données selon type eli 7atetou enty")
                data.add(new Trajet(res.getString("ville_depart"),res.getString("ville_arrive"),res.getDate("date_trajet"),res.getTimestamp("date_annonce"),res.getInt("nombre_place"),res.getDouble("prix"),res.getInt("evenement_id"),res.getInt("user_id")));
            }
         */
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
        populateCombos();
        try {
            columnTrajet();
        } catch (SQLException ex) {
            Logger.getLogger(TrajetListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(UserService.idlogger);
        listTrajet = FXCollections.observableArrayList(serviceTrajet.getByUser(UserService.idlogger));
        tableviewTrajet.setItems(listTrajet);

        tableviewTrajet.setEditable(true);
        colVilleDepart.setCellFactory(TextFieldTableCell.forTableColumn());
        prix.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!ConditionSaisie.checkNumeric(character)) {
                    event.consume();
                    verifprix.setVisible(true);
                    verifprix.setText("erreur");
                }
                if (prix.getText().length() >= max_Lengh1) {
                    verifprix.setVisible(true);
                    verifprix.setText("just 8 digit");
                    event.consume();
                } else {

                    verifprix.setVisible(false);

                }
            }
        });
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
    private void boutonDelete(ActionEvent event) {
        //TrajetService us=new UserService();
        int x = tableviewTrajet.getSelectionModel().getSelectedItem().getId();
        serviceTrajet.remove(x);
        serviceTrajet.getAll();
        listTrajet = FXCollections.observableArrayList(serviceTrajet.getAll());
        colVilleDepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        colVilleArrive.setCellValueFactory(new PropertyValueFactory<>("ville_arrive"));
        colDateDepart.setCellValueFactory(new PropertyValueFactory<>("date_trajet"));
        colNbrePersonne.setCellValueFactory(new PropertyValueFactory<>("nombre_place"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        colDateAnnonce.setCellValueFactory(new PropertyValueFactory<>("date_annonce"));

        tableviewTrajet.setItems(listTrajet);
    }

    @FXML
    private void boutonRefresh(ActionEvent event) {
        listTrajet = FXCollections.observableArrayList(serviceTrajet.getByUser(UserService.idlogger));
        tableviewTrajet.setItems(listTrajet);
        tableviewTrajet.refresh();
    }

    public void setStageAddTrajet(Node node) {
        //loadStage.getChildren().setAll(node);
    }

    @FXML
    private void boutonModifier(ActionEvent event) throws IOException {
        Trajet trajet = tableviewTrajet.getSelectionModel().getSelectedItems().get(0);
        //menuTrajet.setEffect(new GaussianBlur(10));
        //new FadeInRightTransition(AddTrajet).play();
//        EditTrajetController.id = trajet.getId();
//        EditTrajetController.villeDepart = trajet.getVille_depart();
//       EditTrajetController.villeArrive = trajet.getVille_depart();
        // EditTrajetController.prix = trajet.getPrix();
        //EditTrajetController.dateTrajet = trajet.getDate_trajet();
        //EditTrajetController.dateAnnonce = trajet.getDate_annonce();
        //EditTrajetController.nombrePersonne = trajet.getNombre_place();

//        NavigationTrajet.loadStageAddTrajet(nav.getEditTrajet());
    }

    /*
  private void list() {
        listTrajet = trajet.findById(trajet);
        tableviewTrajet.setItems(listTrajet);
       // trajet.setStatusTrajet("T");
    }

     */
    @FXML
    private void goBack(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenuConducteurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeVilleDepartCellEvent(CellEditEvent edittedCell) {
        Trajet trajetSelected = tableviewTrajet.getSelectionModel().getSelectedItem();
        trajetSelected.setVille_depart(edittedCell.getNewValue().toString());

    }

    @FXML
    private void submit(Event event) {
        //System.err.println("clicked");
        Trajet trajet = new Trajet();
        DatePicker datePicker = new DatePicker();
        //java.sql.Date dateTrajet = new java.sql.Date();
        LocalDate localId = date_trajet.getValue();
        Date date = Date.valueOf(localId);
        String villeDepart = (String) ville_depart.getValue();
        trajet.setVille_depart(villeDepart);
        trajet.setVille_arrive((String) ville_arrive.getValue());
        //System.out.println(Double.parseDouble(prix.getText()));
        trajet.setPrix(Double.parseDouble(prix.getText()));
        trajet.setDate_trajet(date);
        trajet.setNombre_place(Integer.parseInt((String) nombre_place.getValue()));
        trajet.setId_user(UserService.idlogger);
        trajet.setId_evenement(2);

        //System.out.println(prix.getText()); 
        // ajouet à la base si tt est valide
        TrajetService serviceTrajet = new TrajetService();
        System.out.println(trajet);
        serviceTrajet.add(trajet);
        //System.out.println("villedepart " + villeDepart+ "villearrive "++);
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Careful with the next step!");

        alert.showAndWait();
    }

    private void populateCombos() {

        ville_depart.getItems().addAll("Ariana", "Beja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan",
                "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Médenine",
                "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
                "Tatouine", "Tozeur", "Tunis", "Zaghouan");

        ville_arrive.getItems().addAll("Ariana", "Beja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan",
                "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Médenine",
                "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
                "Tatouine", "Tozeur", "Tunis", "Zaghouan");

        nombre_place.getItems().addAll("1", "2", "3", "4");
    }

    @FXML
    private void openMap(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/Map.fxml"));
        Scene scene = new Scene(root);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();

    }
}
