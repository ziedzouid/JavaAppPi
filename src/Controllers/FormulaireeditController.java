/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import utils.ConditionSaisie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import static javax.swing.Spring.height;
import models.Circle;
import models.Conducteur;
import models.Passager;
import models.Trajet;
import models.User;
import services.CircleService;
import services.ConducteurService;
import services.PassagerService;
import services.TrajetService;
import services.UserService;
import static services.UserService.idlogger;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FormulaireeditController implements Initializable {

    private ImageView image;
    @FXML
    private JFXButton telechargerIm;
    @FXML
    private DatePicker date;
    @FXML
    private JFXCheckBox con;
    @FXML
    private JFXCheckBox pass;
    @FXML
    private ToggleGroup gender;
    //private JFXComboBox<Circle> id;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXPasswordField Mdp;
    @FXML
    private JFXRadioButton fem;
    @FXML
    private JFXRadioButton mas;
    private Label phath;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private ImageView imageview;
    @FXML
    private Label way;
    @FXML
    private JFXComboBox<Circle> combobox;
    @FXML
    private JFXTextField centre;
    ObservableList<Circle> listCercle;
    final Integer max_Lengh = 8;
    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpFabs();

        CircleService cercles = new CircleService();
        listCercle = FXCollections.observableArrayList(cercles.getAll());
        combobox.setItems(listCercle);
        User u = null;
        Passager p = null;
        Conducteur c = null;
        Circle cercle = null;
        UserService us = new UserService();
        ConducteurService cs = new ConducteurService();
        PassagerService ps = new PassagerService();
        u = us.findById(idlogger);
        System.out.println(u);
        try {
            if (us.returnRole(u.getEmail(), u.getMdp()).equals("a:1:{i:0;s:13:\"ROLE_PASSAGER\";}")) {
                pass.setSelected(true);
                con.setSelected(false);
                p = ps.findById(idlogger);
                cercle = new Circle(p.getCircle().getId(), p.getCircle().getNom(), p.getCircle().getType(), p.getCircle().getDescription());
                centre.setText(cercle.getNom());

            } else if (us.returnRole(u.getEmail(), u.getMdp()).equals("a:1:{i:0;s:15:\"ROLE_CONDUCTEUR\";}")) {
                pass.setSelected(false);
                con.setSelected(true);
                c = cs.findById(idlogger);
                cercle = new Circle(c.getCircle().getId(), c.getCircle().getNom(), c.getCircle().getType(), c.getCircle().getDescription());
                centre.setText(cercle.getNom());

            }
        } catch (SQLException ex) {
            Logger.getLogger(FormulaireeditController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (u.getSexe().equals("Female")) {
            fem.setSelected(true);
            mas.setSelected(false);
        } else if (u.getSexe().equals("Male")) {
            mas.setSelected(true);
            fem.setSelected(false);
        }
        Date java = new Date();
        java = new Date(u.getDate_naissance().getTime());
        LocalDate d = java.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        date.setValue(d);
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        String strI = Integer.toString(u.getTel());
        tel.setText(strI);
        mail.setText(u.getEmail());

        /*     File file = new File("file:///C:/wamp/www/mutiny/src/Photos/"+u.getAvatar());
            Image img = new Image(file.toURI().toString());
                    //way.setText(file.getPath());
                    imageview.setImage(img);
                    imageview.setFitHeight(150);
                    imageview.setFitWidth(150);
        
    //Image img = new Image("file:///C:/wamp/www/mutiny/src/Photos/"+u.getAvatar());
        
           // imageview.setImage(img);*/
        File file = new File(u.getAvatar());
        Image IMAGE_RUBY = new Image(file.toURI().toString());
        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

        ImageView imgview = new ImageView(IMAGE_RUBY);

//                            setGraphic(imgview);
        imgview.setFitHeight(100);
        imgview.setFitWidth(100);
        Rectangle clip = new Rectangle(
                imgview.getFitWidth(), imgview.getFitHeight()
        );

        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imgview.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imgview.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imgview.setClip(null);

        // apply a shadow effect.
        imgview.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        imgview.setImage(image);

        /**
         * ****************ControleSaisieBeforeUpdate*********************************************
         */
        tel.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!ConditionSaisie.checkNumeric(character)) {
                    event.consume();
                }
            }
        });
        mail.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            boolean matches = ConditionSaisie.validate(mail.getText());
            if (!(matches)) {
                mail.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
            } else {
                mail.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        });

        nom.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (nom.getText().length() >= max_Lengh) {
                    event.consume();
                }
                if (event.getCharacter().matches("[A-Za-z]")) {
                    nom.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                } else {
                    nom.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                }

            }
        });
        prenom.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (prenom.getText().length() >= max_Lengh) {
                    event.consume();
                }
                if (event.getCharacter().matches("[A-Za-z]")) {
                    prenom.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                } else {
                    prenom.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                }

            }
        });
        Mdp.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (Mdp.getText().length() >= max_Lengh) {
                    event.consume();
                    //label.setText("password can't be more than 8 characters ");
                }
                if (Mdp.getText().matches(pattern)) {
                    Mdp.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                    System.out.println("true");
                } else {
                    Mdp.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                    System.out.println("false");
                }

            }
        });
        /**
         * *************************************************************************************
         */
    }

    @FXML
    private void deasactivate(ActionEvent event) throws IOException, SQLException {
        User u = null;
        Trajet trajet = null;
        UserService us = new UserService();
        u = us.findById(idlogger);
        TrajetService ts = new TrajetService();
        trajet = ts.UserInTarget(u);
        if (trajet != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("you are already subscribed in target you can't desactivate your account!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("will you desactivate your account ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                us.remove(u.getId());

            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/login_1.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            }

        }
    }

    @FXML
    private boolean conducteur(ActionEvent event) {
        if (con.isSelected()) {
            pass.setSelected(false);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private boolean passager(ActionEvent event) {
        if (pass.isSelected()) {
            con.setSelected(false);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private boolean female(ActionEvent event) {
        if (fem.isSelected()) {
            mas.setSelected(false);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private boolean male(ActionEvent event) {
        if (mas.isSelected()) {
            fem.setSelected(false);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void telechargerIm(ActionEvent event) {
        String path;
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        Image img = new Image(file.toURI().toString());
        way.setText(file.getPath());
        imageview.setImage(img);
        imageview.setFitHeight(150);
        imageview.setFitWidth(150);
    }

    private void setUpFabs() {
        //Setting up icons for button
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CIRCLE_ALT_NOTCH);
        icon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

        FontAwesomeIconView closeicon = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        closeicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");
        FontAwesomeIconView logicon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK_ALT);
        logicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

        JFXButton button1 = new JFXButton();
        Label label1 = new Label("G1");
        button1.setGraphic(icon);
        label1.setStyle("-fx-text-fill:WHITE");
        button1.setButtonType(JFXButton.ButtonType.RAISED);
        button1.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");

        JFXButton button2 = new JFXButton();
        button2.setTooltip(new Tooltip("Log off"));
        button2.setButtonType(JFXButton.ButtonType.RAISED);
        button2.setGraphic(logicon);
        button2.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");
        button2.setOnAction((ActionEvent event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/login_1.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(FormulaireeditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXButton button3 = new JFXButton();
        button3.setButtonType(JFXButton.ButtonType.RAISED);
        button3.setTooltip(new Tooltip("Exit"));
        button3.setGraphic(closeicon);
        button3.setStyle("-fx-pref-width:30px;-fx-background-color:#F87951;"
                + "-fx-background-radius:30px;-fx-pref-height:30px;");
        button3.setOnAction((ActionEvent event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/MenuPassager.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(FormulaireeditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button1, (expanded) -> {
            return new ArrayList<KeyValue>() {
                {
                    //  add(new KeyValue(button1.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                }
            };
        });
        nodesList.addAnimatedNode(button2);
        nodesList.addAnimatedNode(button3);

        fabsContainer.getChildren().add(nodesList);

    }

    @FXML
    private void misajouruser(ActionEvent event) throws IOException {
        int x = Integer.parseInt(tel.getText());
        //String k;
        UserService us = new UserService();
        User u = null;
        u = us.findById(idlogger);

        Date d = new Date(date.getValue().toEpochDay());
        CircleService cs = new CircleService();

        ConducteurService ac = new ConducteurService();
        PassagerService ps = new PassagerService();
        Conducteur c;
        Passager p;
        Circle circle;
        if (con.isSelected()) {
            if (fem.isSelected()) {
                circle = new Circle(combobox.getSelectionModel().getSelectedItem().getId(), combobox.getSelectionModel().getSelectedItem().getNom(), combobox.getSelectionModel().getSelectedItem().getType(), combobox.getSelectionModel().getSelectedItem().getDescription());
                c = new Conducteur(u.getId(), nom.getText(), prenom.getText(), fem.getText(), d, x, mail.getText(), Mdp.getText(), way.getText(), circle);

                ac.update(c);

            } else {
                circle = new Circle(combobox.getSelectionModel().getSelectedItem().getId(), combobox.getSelectionModel().getSelectedItem().getNom(), combobox.getSelectionModel().getSelectedItem().getType(), combobox.getSelectionModel().getSelectedItem().getDescription());

                c = new Conducteur(u.getId(), nom.getText(), prenom.getText(), mas.getText(), d, x, mail.getText(), Mdp.getText(), way.getText(), circle);
                ac.update(c);
            }
        } else if (pass.isSelected()) {
            if (fem.isSelected()) {
                circle = new Circle(combobox.getSelectionModel().getSelectedItem().getId(), combobox.getSelectionModel().getSelectedItem().getNom(), combobox.getSelectionModel().getSelectedItem().getType(), combobox.getSelectionModel().getSelectedItem().getDescription());

                p = new Passager(u.getId(), nom.getText(), prenom.getText(), fem.getText(), d, x, mail.getText(), Mdp.getText(), way.getText(), circle);
                ps.update(p);
            } else {
                circle = new Circle(combobox.getSelectionModel().getSelectedItem().getId(), combobox.getSelectionModel().getSelectedItem().getNom(), combobox.getSelectionModel().getSelectedItem().getType(), combobox.getSelectionModel().getSelectedItem().getDescription());

                p = new Passager(u.getId(), nom.getText(), prenom.getText(), mas.getText(), d, x, mail.getText(), Mdp.getText(), way.getText(), circle);
                ps.update(p);
            }
        }

        /* Parent root = FXMLLoader.load(getClass().getResource("/Views/menu.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node) event.getSource()).getScene().getWindow(); 
        s.setScene(scene);
        s.show();*/
    }

    @FXML
    private void returntocond(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/MenuConducteur.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(FormulaireeditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
