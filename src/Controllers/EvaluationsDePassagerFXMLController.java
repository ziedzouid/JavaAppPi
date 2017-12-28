/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.Evaluation;
import utils.VariablesGlobale;

/**
 * FXML Controller class
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class EvaluationsDePassagerFXMLController implements Initializable {

    @FXML
    private ListView<Evaluation> ListeDesEvaluationsParTrajet;
    @FXML
    private JFXButton Bouton_evaluer_trajet;
    @FXML
    private JFXButton bouton_retour;
    @FXML
    private Label Message_Evaluer_Trajet;

    List<Evaluation> es1 = VariablesGlobale.iEvaluationService.getByUser(VariablesGlobale.PassagerCourant.getId());  // var statique

    public EvaluationsDePassagerFXMLController() {
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Evaluation> items = FXCollections.observableArrayList(es1);

        ListeDesEvaluationsParTrajet.setCellFactory((ListView<Evaluation> arg0) -> {
            ListCell<Evaluation> cell = new ListCell<Evaluation>() {
                @Override
                protected void updateItem(Evaluation e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("C:\\Users\\ASUS\\Desktop\\Integration11MedShil\\src\\Photos\\logo.png");
                        file.getParentFile().mkdirs();
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

                        setText(VariablesGlobale.iTrajetService.findById(e.getTrajet().getId()).getVille_depart() + "  à " + VariablesGlobale.iTrajetService.findById(e.getTrajet().getId()).getVille_arrive() + "\n" + "   " + e.getCommentaire() + "\n" + "   Note : " + e.getNote() + "/5");

                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        ListeDesEvaluationsParTrajet.setItems(items);

    }
    // TODO

    @FXML
    private void evaluer_trajet(ActionEvent event) throws IOException {

        Evaluation e = ListeDesEvaluationsParTrajet.getSelectionModel().getSelectedItem();
        VariablesGlobale.evaluationCourante= ListeDesEvaluationsParTrajet.getSelectionModel().getSelectedItem();
        
        if (e == null) {
            Message_Evaluer_Trajet.setText("Select an Evaluation please !");
        } else {
            VariablesGlobale.TrajetSelectionne = VariablesGlobale.iTrajetService.findById(e.getTrajet().getId());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationFXML.fxml"));

            Parent root = loader.load();

            EvaluationFXMLController personneController = loader.getController();
            RadioButton button = new JFXRadioButton();
            button.setSelected(true);
            switch (e.getNote()) {
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
        Evaluation e = ListeDesEvaluationsParTrajet.getSelectionModel().getSelectedItem();

        if (e == null) {
            Message_Evaluer_Trajet.setText("Select an Evaluation please !");
        } else {
            VariablesGlobale.iEvaluationService.remove(e.getId());
            Message_Evaluer_Trajet.setText("Evaluation Deleted ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationsDePassagerFXML.fxml"));

            Parent root = loader.load();

            bouton_retour.getScene().setRoot(root);

        }

    }

}
