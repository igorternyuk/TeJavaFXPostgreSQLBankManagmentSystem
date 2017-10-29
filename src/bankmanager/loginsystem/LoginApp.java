package bankmanager.loginsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author igor
 * Last edited 28-10-2017
 */

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Bank managment system");
        stage.show();
    }
    
    /*public static void main(String[] args) {
        launch(args);
    }*/
}
