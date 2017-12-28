/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.MyactualitiesController.idTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Actualite;
import models.Circle;
import models.Message;
import models.Produit;
import models.User;
import services.ActualiteService;
import services.CircleService;
import services.MessageService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class CircleController implements Initializable {
    
    @FXML
    private JFXButton editcircle;
    @FXML
    private JFXButton addcircle;
    @FXML
    private JFXButton removecircle;
    @FXML
    private JFXTextArea namecircle;
    @FXML
    private JFXTextArea typecircle;
    @FXML
    private JFXTextArea descriptioncircle;
    
    CircleService cs = new CircleService();
    @FXML
    private JFXListView<Label> listcircle;
    int select;
    
    List<Circle> circles;
    public int id;
    @FXML
    private Button retcircle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<String> cirList = new ArrayList<>();
        List<Circle> circles = new ArrayList<>();
        circles = cs.getAll();
        
        for (Circle c : circles) {
            cirList.add("Nom : " + " " + c.getNom() + " " + "description :" + c.getDescription() + "Type: " + " " + c.getType());
        }
        
        System.out.println(cirList);
        
        for (String s : cirList) {
            try {
                Label l = new Label(s);
                l.setFont(Font.font(null, FontWeight.BOLD, 12));
                listcircle.getItems().add(l);
            } catch (Exception e) {
                
            }
        }
        
    }
    
    @FXML
    private void selectedcircle(MouseEvent event) {
        
        int select = listcircle.getSelectionModel().getSelectedIndex();
        List<Circle> circles = new ArrayList<>();
        System.out.println(select);
        circles = cs.getAll();
        
        id = circles.get(select).getId();
        String Nom = circles.get(select).getNom();
        String desc = circles.get(select).getDescription();
        String type = circles.get(select).getType();
        
        namecircle.setText(Nom);
        typecircle.setText(type);
        descriptioncircle.setText(type);
        
    }
    
    @FXML
    private void editcircle(ActionEvent event) {
        
        String name = namecircle.getText();
        String type = typecircle.getText();
        String desc = descriptioncircle.getText();
        
        Circle c = new Circle(id, name, type, desc);
        cs.update(c);
        
    }
    
    @FXML
    private void addcircle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/AddCircle.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenuConducteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void removecircle(ActionEvent event) {
        cs.remove(id);
        
    }
    
    @FXML
    private void returnfromcircle(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/MenuAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            // Logger.getLogger(MenuConducteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
