package bankmanager.loginsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author igor
 * Last edited 28-10-2017
 */
public class LoginController implements Initializable{
    
    private LoginModel model = new LoginModel();

    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField inputLogin;
    
    @FXML
    private PasswordField inputPassword;
    
    @FXML
    private ComboBox<LoginOption> comboStudentAdmin;
    
    @FXML
    private Button btnLogin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*if(model.isDatabaseConnected()){
            lblStatus.setText("Connected");
        } else {
            lblStatus.setText("Not connected");
        }
        this.comboStudentAdmin.setItems(
                FXCollections.observableArrayList(LoginOption.values()));*/
    }
    
    @FXML
    public void login(ActionEvent e){
        
    }
    
    public void clientLogin() {
        
    }
    
    public void adminLogin() {
        
    }
}
