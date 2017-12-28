/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import interfaces.IEvaluationService;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import models.Evaluation;
import services.EvaluationService;
import utils.VariablesGlobale;

/**
 * FXML Controller class
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class EvaluationFXMLController implements Initializable {

    @FXML
    private RadioButton button1;
    @FXML
    private RadioButton button2;
    @FXML
    private RadioButton button3;
    @FXML
    private Button b1;
    ToggleGroup group = new ToggleGroup();
    @FXML
    private TextField text1;
    @FXML
    private RadioButton button4;
    @FXML
    private RadioButton button5;
    @FXML
    private JFXButton bouton_retour;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        button1.setToggleGroup(group);
        button1.setUserData(1);
        button2.setToggleGroup(group);
        button2.setUserData(2);
        button3.setToggleGroup(group);
        button3.setUserData(3);
        button4.setToggleGroup(group);
        button4.setUserData(4);
        button5.setToggleGroup(group);
        button5.setUserData(5);
        button1.setSelected(true);

        // TODO
    }

    public RadioButton getButton1() {
        return button1;
    }

    public void setButton1(RadioButton button1) {
        this.button1 = button1;
    }

    public RadioButton getButton2() {
        return button2;
    }

    public void setButton2(RadioButton button2) {
        this.button2 = button2;
    }

    public RadioButton getButton3() {
        return button3;
    }

    public void setButton3(RadioButton button3) {
        this.button3 = button3;
    }

    public TextField getText1() {
        return text1;
    }

    public void setText1(TextField text1) {
        this.text1 = text1;
    }

    public RadioButton getButton4() {
        return button4;
    }

    public void setButton4(RadioButton button4) {
        this.button4 = button4;
    }

    public RadioButton getButton5() {
        return button5;
    }

    public void setButton5(RadioButton button5) {
        this.button5 = button5;
    }

    @FXML
    private void doB(ActionEvent event) throws ParseException, IOException {

        Evaluation e1 = VariablesGlobale.iEvaluationService.SearchByTrajetAndUserId(VariablesGlobale.TrajetSelectionne.getId(), VariablesGlobale.PassagerCourant.getId());

        Evaluation e = new Evaluation();
        e.setPassager(VariablesGlobale.PassagerCourant);
        e.setTrajet(VariablesGlobale.TrajetSelectionne);

        
        if (e1 == null) {

            e.setNote(Byte.valueOf(group.getSelectedToggle().getUserData().toString()));
            e.setCommentaire(text1.getText());
            

            VariablesGlobale.iEvaluationService.add(e);
            VariablesGlobale.evaluationCourante = null;
        } else {
            
            e1.setNote(Byte.valueOf(group.getSelectedToggle().getUserData().toString()));
            e1.setCommentaire(text1.getText());
            IEvaluationService evaluationService = new EvaluationService();
            
            evaluationService.update(e1);
            

        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationsDuTrajetFXML.fxml"));

        Parent root = loader.load();

        text1.getScene().setRoot(root);
    }

    @FXML
    private void retour_au_liste_evaluations_par_trajet(ActionEvent event) throws IOException {
        if (VariablesGlobale.evaluationCourante == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationsDuTrajetFXML.fxml"));

            Parent root = loader.load();

            text1.getScene().setRoot(root);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EvaluationsDePassagerFXML.fxml"));

            Parent root = loader.load();

            text1.getScene().setRoot(root);
        }
    }
}
