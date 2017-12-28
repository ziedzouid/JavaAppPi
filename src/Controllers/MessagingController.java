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
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
public class MessagingController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXListView<Label> Boxlist;
    @FXML
    private JFXButton msgsend;
    @FXML
    private JFXListView<Label> listfriends;
    @FXML
    private TextFlow msgview;
    @FXML
    private JFXTextArea msgwrite;

    List<String> pseudos = new ArrayList<>();
    @FXML
    private ImageView retmsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MessageService messageService = new MessageService();
        List<String> messages = new ArrayList<>();
        messages = messageService.getObjectMsgbyuser(UserService.idlogger);

        CircleService circleService = new CircleService();
        int idcrl = circleService.findCircleIdByUser(UserService.idlogger);
        List<User> users = new ArrayList<>();
        users = circleService.findUsersByCircle(idcrl);
        List<String> emails = new ArrayList<>();
        for (User u : users) {
            emails.add(u.getEmail());
            String tmp = u.getNom() + " " + u.getPrenom();
            pseudos.add(tmp);
        }

        System.out.println(emails);
        //System.out.println(messages);

        for (String s : messages) {
            try {
                Label l = new Label(s);
                l.setFont(Font.font(null, FontWeight.BOLD, 12));
                //  l.setGraphic(new ImageView(new Image(new FileInputStream("D:\\Esprit17_18\\Java\\Projects\\CoolVoituarge\\src\\view\\emoticon.png"))));
                Boxlist.getItems().add(l);
            } catch (Exception e) {

            }
        }

        for (String s : pseudos) {
            try {
                Label l = new Label(s);
                l.setFont(Font.font(null, FontWeight.BOLD, 12));
                //  l.setGraphic(new ImageView(new Image(new FileInputStream("D:\\Esprit17_18\\Java\\Projects\\CoolVoituarge\\src\\view\\emoticon.png"))));
                listfriends.getItems().add(l);
            } catch (Exception e) {

            }
        }
        // TODO
    }

    @FXML
    private void msgselected(MouseEvent event) {
        int select = Boxlist.getSelectionModel().getSelectedIndex();
        System.out.println(select);
        MessageService ms = new MessageService();
        List<Message> messages = ms.getMsgByuser(UserService.idlogger);

        String contenue = messages.get(select).getContenue();
        Text text = new Text(contenue);
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        msgview.getChildren().clear();
        msgview.getChildren().add(text);

    }

    @FXML
    private void sendmessage(ActionEvent event) {
        int select = listfriends.getSelectionModel().getSelectedIndex();
        System.out.println(select);
        CircleService circleService = new CircleService();
        int idcrl = circleService.findCircleIdByUser(UserService.idlogger);
        List<User> users = new ArrayList<>();
        users = circleService.findUsersByCircle(idcrl);

        List<Integer> ids = new ArrayList<>();
        for (User u : users) {
            ids.add(u.getId());
        }

        UserService userService = new UserService();
        User u = userService.findById2(UserService.idlogger);

        // List<String> names = new ArrayList<>();
        /* for (User u : users) {
            names.add(u.getPrenom() +" "+u.getNom());
        }*/
        String contenue = msgwrite.getText();
        int toid = ids.get(select);
        //String name = names.get(select);
        String name = u.getPrenom() + " " + u.getNom();
        Message message = new Message(contenue, name, UserService.idlogger, toid);
        MessageService messageService = new MessageService();
        messageService.add(message);
        //System.out.println(message);

    }

    @FXML
    private void Selectfriend(MouseEvent event) {

    }

    @FXML
    private void returnfrommsg(MouseEvent event) {

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
            // Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
