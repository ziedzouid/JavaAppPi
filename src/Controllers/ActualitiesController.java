/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import models.*;
import services.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ActualitiesController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextFlow description;
    @FXML
    private JFXListView<Label> listacts;
    @FXML
    private JFXButton viewmyact;
    @FXML
    private JFXButton addact;
    @FXML
    private JFXButton retacts;

    @FXML
    private JFXButton msgBox;
    @FXML
    private ImageView imageact;

    CircleService cs = new CircleService();

    public int idclr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idclr = cs.findCircleIdByUser(UserService.idlogger);
        ActualiteService as = new ActualiteService();
        System.out.println(idclr);
        List<String> act = as.getActnameByCircle(idclr);
        System.out.println(act);
        // ObservableList<String> names = FXCollections.observableArrayList(act);   
        // actualitylist.setItems(names);
        for (String s : act) {
            try {
                Label l = new Label(s);
                l.setFont(Font.font(null, FontWeight.BOLD, 14));
                /*a modifier path*/ l.setGraphic(new ImageView(new Image(new FileInputStream("D:\\Esprit17_18\\pi_dev\\SprintJAVA\\gitTest\\Integration_finale_pidev\\src\\Views\\emoticon.png"))));
                listacts.getItems().add(l);
            } catch (Exception e) {

            }
        }

        /* listacts.setExpanded(true);
        listacts.depthProperty().set(1);*/
    }

    @FXML
    private void displayfeed(MouseEvent event) {
        int select = listacts.getSelectionModel().getSelectedIndex();
        System.out.println(select);
        ActualiteService as = new ActualiteService();
        List<Actualite> act = as.getActualitiesByCircle(idclr);

        String contenue = act.get(select).getContenu();
        Text text = new Text(contenue);
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        description.getChildren().clear();
        description.getChildren().add(text);
        String path = act.get(select).getType();
        path = "C:/wamp64/www/mutinywebappfinal2/mutinywebapp/web/"+path;
        Image image = null;
        if (path != null) {

            try {
                
                image = new Image(new FileInputStream(path));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ActualitiesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageact.setImage(image);

        } else {
            try {
                image = new Image(new FileInputStream("D:\\Esprit17_18\\pi_dev\\SprintJAVA\\gitTest\\Integration_finale_pidev\\src\\icons\\lgnCar.png"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ActualitiesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        imageact.setImage(image);

    }

    @FXML
    private void viewmyactuality(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/myactualities.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(scene);
        s.show();

    }

    @FXML
    private void addactuality(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/addact.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void returnfromacts(MouseEvent event) {
        try {
            String role = new UserService().getRoleById2(UserService.idlogger);
            Parent root;
            if (role.equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            } else if (role.equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            }

        } catch (IOException ex) {
            //Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotomsgbox(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Messaging.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();

        } catch (IOException ex) {
            Logger.getLogger(ActualitiesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
