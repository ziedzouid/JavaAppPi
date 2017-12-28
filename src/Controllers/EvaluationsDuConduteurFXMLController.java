/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class EvaluationsDuConduteurFXMLController implements Initializable {

    @FXML
    private JFXListView<Evaluation> List_de_evaluationsCon;
    @FXML
    private JFXButton retour_de_evaluationsCon;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hi");
        List<Evaluation> es = VariablesGlobale.iEvaluationService.getByConduteur();
        
       ObservableList<Evaluation> items = FXCollections.observableArrayList(es);
    
        List_de_evaluationsCon.setCellFactory((ListView<Evaluation> arg0) -> {
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
        List_de_evaluationsCon.setItems(items);
        
        // TODO
    }

    
  
    @FXML
    private void retour_de_evaluationsCon(ActionEvent event) throws IOException {
        
        
            FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/Views/MenuConducteur.fxml")); 

            Parent root = loader.load();

            List_de_evaluationsCon.getScene().setRoot(root);
        
       
    }

}
