/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class TestEvaluation extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        //  primaryStage.setMaximized(true);
          // Parent root = FXMLLoader.load(getClass().getResource("EvaluationsDuTrajetFXML.fxml"));
           Parent root = FXMLLoader.load(getClass().getResource("EvaluationsDePassagerFXML.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("EvaluationsDuConduteurFXML.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
