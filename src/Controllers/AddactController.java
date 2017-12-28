/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import services.*;
import models.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddactController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private ImageView paneaddact;
    @FXML
    private JFXTextField theme;
    @FXML
    private JFXTextArea contenu;
    @FXML
    private ImageView checked;
    @FXML
    private JFXButton retpub;
    @FXML
    private JFXButton actaddbt;
    @FXML
    private JFXButton imaup;
    @FXML
    private ImageView imageviewup;

    String pathphoto;
    @FXML
    private ImageView retactadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Addactpane(MouseEvent event) {
    }


    @FXML
    private void addactbutton(ActionEvent event) {
        String th = theme.getText();
        String cn = contenu.getText();

        System.out.println(th);
        System.out.println(cn);

        
        User user = new UserService().findById2(UserService.idlogger);
        System.out.println(user);
       
        Actualite a = new Actualite(this.pathphoto, th, cn, user);

        ActualiteService actualiteService = new ActualiteService();

        actualiteService.add(a);

        checked.setVisible(true);
    }

    @FXML
    private void imageupload(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "."));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageviewup.setImage(image);
            this.pathphoto = selectedFile.toURI().toString().substring(56);
            System.out.println(pathphoto);
            /* URI tst = selectedFile.toURI();
                System.out.println(tst);*/

        } else {
            System.out.println("file invalid ");
        }
    }

    @FXML
    private void returnfromactadd(MouseEvent event) {
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
