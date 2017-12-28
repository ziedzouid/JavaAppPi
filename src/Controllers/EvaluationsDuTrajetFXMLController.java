/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import interfaces.IPassagerService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Evaluation;
import models.Passager;
import models.User;
import services.EvaluationService;
import services.PassagerService;
import services.UserService;
import utils.VariablesGlobale;

/**
 * FXML Controller class
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class EvaluationsDuTrajetFXMLController implements Initializable {

    @FXML
    private ListView<Evaluation> ListeDesEvaluationsParTrajet;


    @FXML
    private JFXButton Bouton_evaluer_trajet;
    @FXML
    private JFXButton bouton_retour;
    @FXML
    private Label Message_Evaluer_Trajet;

    //  Evaluation e = es.findById(13);
    //IPassagerService passagerService = new PassagerService();//unused
    // Passager p1 = passagerService.findById(3);//unused
    //   List<Evaluation> es1 = es.getByTrajet(e.getTrajet().getId()); 
    List<Evaluation> es1 = VariablesGlobale.iEvaluationService.getByTrajet(VariablesGlobale.TrajetSelectionne.getId());

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Evaluation> items = FXCollections.observableArrayList(es1);
      
        ////////
        Evaluation e1 = VariablesGlobale.iEvaluationService.SearchByTrajetAndUserId(VariablesGlobale.TrajetSelectionne.getId(), VariablesGlobale.PassagerCourant.getId());
         if (e1 != null) {
            Bouton_evaluer_trajet.setText("Edit Evaluation !");
             System.out.println(VariablesGlobale.PassagerCourant);
             System.out.println(UserService.idlogger);

        }
        ////////
        ListeDesEvaluationsParTrajet.setCellFactory((ListView<Evaluation> arg0) -> {
            ListCell<Evaluation> cell = new ListCell<Evaluation>() {
                @Override
                protected void updateItem(Evaluation e, boolean btl) {
                    super.updateItem(e, btl);
                    
                    if (e != null) {
                        File file = new File(VariablesGlobale.ipassagerService.findById(e.getPassager().getId()).getAvatar());
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());
                        
                        ImageView imgview = new ImageView(IMAGE_RUBY);
                        
                        setGraphic(imgview);
                        
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
                        
                        setText(VariablesGlobale.ipassagerService.findById(e.getPassager().getId()).getNom() + " " + e.getPassager().getPrenom() + "\n" + "   " + e.getCommentaire() + "\n" + "   Note : " + e.getNote() + "/5");
                        
                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));
                        
                        setAlignment(Pos.CENTER);
                    }
                    
                    
                }
                
            };
            return cell;
        }  );
        ListeDesEvaluationsParTrajet.setItems(items);

    }// TODO

    @FXML
    private void evaluer_trajet(ActionEvent event) throws IOException {
        Evaluation e1 = VariablesGlobale.iEvaluationService.SearchByTrajetAndUserId(VariablesGlobale.TrajetSelectionne.getId(), VariablesGlobale.PassagerCourant.getId());
     
        if (e1 != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationFXML.fxml"));

            Parent root = loader.load();

            EvaluationFXMLController personneController = loader.getController();
            RadioButton button = new JFXRadioButton();
            button.setSelected(true);
            switch (e1.getNote()) {
                case 5:
                    personneController.setButton5(button);
                    break;

                case 4:
                    personneController.setButton4(button);
                    break;
                case 3:
                    personneController.setButton3(button);
                    break;
                case 2:
                    personneController.setButton2(button);
                    break;
                case 1:
                    personneController.setButton1(button);
                    break;
                default:
                    break;
            }
           
            bouton_retour.getScene().setRoot(root);

        } else {
   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationFXML.fxml"));

            Parent root = loader.load();

            bouton_retour.getScene().setRoot(root);
            //

        }

    }

   
    @FXML
    private void retour_de_liste_evaluations_par_trajet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MenuPassager.fxml"));

            Parent root = loader.load();

            bouton_retour.getScene().setRoot(root);
    }

    @FXML
    private void Supprimer_evaluation_button(ActionEvent event) throws IOException {
        Evaluation e1 = VariablesGlobale.iEvaluationService.SearchByTrajetAndUserId(VariablesGlobale.TrajetSelectionne.getId(), VariablesGlobale.PassagerCourant.getId());
        if (e1 != null) {
            VariablesGlobale.iEvaluationService.remove(e1.getId());
            Message_Evaluer_Trajet.setText("Evaluation Deleted ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationsDuTrajetFXML.fxml"));

            Parent root = loader.load();

            bouton_retour.getScene().setRoot(root);

        } else {
            Message_Evaluer_Trajet.setText("You have no evaluations for this ride");
        }

    }

}
