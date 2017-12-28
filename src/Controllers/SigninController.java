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
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.teknikindustries.bulksms.SMS;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Circle;
import models.Conducteur;
import models.Passager;
import services.CircleService;
import services.ConducteurService;
import services.PassagerService;
import services.UserService;
import sun.util.resources.cldr.ak.CalendarData_ak_GH;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class SigninController implements Initializable {

    @FXML
    private JFXRadioButton fem;
    @FXML
    private JFXRadioButton mas;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXCheckBox con;
    @FXML
    private JFXCheckBox pass;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXPasswordField Mdp;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXComboBox<Circle> id;
    @FXML
    private Label phath;
    @FXML
    private ImageView image;
    @FXML
    private StackPane fabsContainer;
    ObservableList<Circle> listCercle;
    boolean valide = true;
    boolean exist = false;
    boolean warage = true;

    final Integer max_Lengh = 15;
    final Integer max_Lengh1 = 8;
    @FXML
    private Label verifdate;
    @FXML
    private Label verifpass;
    @FXML
    private Label veriftel;
    @FXML
    private Label verifnom;
    @FXML
    private Label verifprenom;
    @FXML
    private Label verifmail;
    @FXML
    private JFXTextField username;
    @FXML
    private Label namesign;
    @FXML
    private ImageView imgsign;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        ScaleTransition transition = new ScaleTransition(Duration.seconds(4), imgsign);
        transition.setToX(2);
        transition.setToY(2);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        ScaleTransition transition2 = new ScaleTransition(Duration.seconds(4), namesign);
        transition2.setToX(2);
        transition2.setToY(2);
        transition2.setCycleCount(Timeline.INDEFINITE);
        transition2.setAutoReverse(true);
        transition2.play();
        setUpFabs();
        String pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

        /*        setUpRipples();*/
        //CircleService cs = new CircleService();
        //listCercle = FXCollections.observableArrayList(cs.getAll());
        // id.setItems(listCercle);
        tel.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String character = event.getCharacter();
                if (!ConditionSaisie.checkNumeric(character)) {
                    event.consume();
                    veriftel.setVisible(true);
                    veriftel.setText("field can't contains characters");
                }
                if (tel.getText().length() >= max_Lengh1) {
                    veriftel.setVisible(true);
                    veriftel.setText("just 8 digit");
                    event.consume();
                } else {

                    veriftel.setVisible(false);

                }
            }
        });
        mail.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            boolean matches = ConditionSaisie.validate(mail.getText());
            if (!(matches)) {
                mail.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                verifmail.setVisible(true);
                verifmail.setText("invalide");
            } else {
                mail.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                verifmail.setVisible(false);
            }
        });

        nom.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                /* if (nom.getText().length() >= max_Lengh) {                    
                event.consume();
            }*/
                if (event.getCharacter().matches("[A-Za-z]")) {
                    nom.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                    verifnom.setVisible(false);
                } else {
                    nom.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                    verifnom.setVisible(true);
                    verifnom.setText("field accept just characters");
                }

            }
        });
        prenom.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                /*if (prenom.getText().length() >= max_Lengh) {                    
                event.consume();
            }*/
                if (event.getCharacter().matches("[A-Za-z]")) {
                    prenom.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                    verifprenom.setVisible(false);
                } else {
                    prenom.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                    verifprenom.setVisible(true);
                    verifprenom.setText("field accept just characters");
                }

            }
        });
        Mdp.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                /*if (Mdp.getText().length() >= max_Lengh1) {                    
                event.consume();
                //label.setText("password can't be more than 8 characters ");
            }*/
                if (Mdp.getText().matches(pattern)) {
                    Mdp.setStyle("-jfx-focus-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                    System.out.println("true");
                    verifpass.setVisible(false);
                } else {
                    Mdp.setStyle("-jfx-focus-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                    verifpass.setVisible(true);
                    verifpass.setText("invalid password!!");
                }

            }
        });
        date.setValue(LocalDate.now());
        /**
         * ********************USERCHART*********************************
         */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("User Chart");
        alert.setHeaderText("Please read the agreement carefully");
//alert.setContentText("Do not accept until you have read the user agreement");

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String useragreement = "coolvoiturage ";

        pw.write(useragreement);
        String exceptionText = sw.toString();

        Label label = new Label("User agreement:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        Hyperlink link = new Hyperlink("Download our agreement");
        link.setOnAction((ActionEvent t) -> {
            Document document = new Document();
            //com.lowagie.text.Document document =new com.lowagie.text.Document();
            PdfWriter writer;
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream("./Licence1.pdf"));

                document.open();

                PdfContentByte cb = writer.getDirectContent();
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

                cb.beginText();
                cb.setFontAndSize(bf, 9);
                cb.setRGBColorFill(0000, 0000, 0000);
                ColumnText ct = new ColumnText(cb);
                Phrase myText = new Phrase(useragreement);
                ct.setSimpleColumn(myText, 20, 750, 580, 20, 10, Element.ALIGN_LEFT);
                ct.go();

                cb.endText();
                document.close();
            } catch (Exception ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 2);
        expContent.add(link, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("FILL THE FIELD TEXT BELLOW");
            alert2.setHeaderText("GLAD THAT YOU JOININ US");
            alert2.showAndWait();
        }
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        valide = true;
        warage = true;
        exist = false;

        UserService us = new UserService();
        if (nom.getText().equals("")) {
            valide = false;
        }
        if (prenom.getText().equals("")) {
            valide = false;
        }
        if (tel.getText().equals("")) {
            valide = false;
        }
        if (Mdp.getText().equals("")) {
            valide = false;
        }
        if (phath.getText().equals("")) {
            valide = false;
        }
        if (mail.getText().equals("")) {
            valide = false;
        }
        if (username.getText().equals("")) {
            valide = false;
        }
        if ((us.exist(mail.getText()))) {
            exist = true;
        }
        LocalDate d2 = LocalDate.now();
        LocalDate localId = date.getValue();
        java.sql.Date d = java.sql.Date.valueOf(localId);       
        // LocalDate birthdate = new LocalDate (1970, 1, 20);
        System.out.println("test from date " + d);
        long age = ChronoUnit.YEARS.between(date.getValue(), d2);
        if (age < 18) {
            System.out.println("your age must be more than 18");
            warage = false;
        }
        if ((valide == true) && (exist == false) && (warage == true)) {

            int x = Integer.parseInt(tel.getText());
            String k;

            CircleService cs = new CircleService();
            ConducteurService ac = new ConducteurService();
            PassagerService ps = new PassagerService();
            Conducteur c;
            Passager p;
            Circle circle;
            if (con.isSelected()) {
                if (fem.isSelected()) {
                    circle = new Circle(1, "test", "test", "test");
                    System.out.println("test from date 2 " + d);
                    c = new Conducteur(nom.getText(), prenom.getText(), fem.getText(), d, x, mail.getText(), Mdp.getText(), phath.getText(), circle);
                    c.setUsername(username.getText());
                    ac.add(c);

                } else {
                    circle = new Circle(1, "test", "test", "test");

                    c = new Conducteur(nom.getText(), prenom.getText(), mas.getText(), d, x, mail.getText(), Mdp.getText(), phath.getText(), circle);
                    c.setUsername(username.getText());
                    ac.add(c);

                }
            } else if (pass.isSelected()) {
                if (fem.isSelected()) {
                    circle = new Circle(1, "test", "test", "test");

                    p = new Passager(nom.getText(), prenom.getText(), fem.getText(), d, x, mail.getText(), Mdp.getText(), phath.getText(), circle);
                    p.setUsername(username.getText());
                    ps.add(p);
                } else {
                    circle = new Circle(1, "test", "test", "test");

                    p = new Passager(nom.getText(), prenom.getText(), mas.getText(), d, x, mail.getText(), Mdp.getText(), phath.getText(), circle);
                    p.setUsername(username.getText());
                    ps.add(p);
                }
            }

            //SMS sms=new SMS();
            //sms.SendSMS("Insaf123", "blackrose","Wellcome to coolvoiturage App your password is"+Mdp.getText(),"+216"+tel.getText(),"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0" ); 
            Parent root = FXMLLoader.load(getClass().getResource("/Views/login_1.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(scene);
            s.show();
        } else if ((exist == true)) {
            System.out.println("cet utilisateur est existant");
            Alert existant = new Alert(AlertType.WARNING);
            existant.setTitle("Warning Dialog");
            existant.setHeaderText("Look, a Warning Dialog");
            existant.setContentText("you are already subscribed");

            existant.showAndWait();

        } else if ((valide == false)) {
            Alert empty = new Alert(AlertType.WARNING);
            empty.setTitle("Warning Dialog");
            empty.setHeaderText("Look, a Warning Dialog");
            empty.setContentText("fileds can't be empty ");

            empty.showAndWait();
        } else if ((warage == false)) {
            Alert warge = new Alert(AlertType.WARNING);
            warge.setTitle("Warning Dialog");
            warge.setHeaderText("Look, a Warning Dialog");
            warge.setContentText("your age is less than 18 ");

            warge.showAndWait();
        }
    }

    @FXML
    private void upload(ActionEvent event) {
        String path;
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        Image img = new Image(file.toURI().toString());
        //phath.setText(file.getPath());
        image.setImage(img);
        image.setFitHeight(150);
        image.setFitWidth(150);
        phath.setText("file:///C:/wamp/www/mutiny/src/Photos/" + file.getName());
        phath.setText(file.getName());

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
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
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
            //Platform.exit();

            try {
                //  paneDrivers.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/Views/login_1.fxml"));
                Scene scene = new Scene(root);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.setScene(scene);
                s.show();

            } catch (IOException ex) {
                //    Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button1, (expanded) -> {
            return new ArrayList<KeyValue>() {
                {
                    //   add(new KeyValue(button1.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                }
            };
        });
        //nodesList.addAnimatedNode(button2);
        nodesList.addAnimatedNode(button3);

        fabsContainer.getChildren().add(nodesList);

    }

}
