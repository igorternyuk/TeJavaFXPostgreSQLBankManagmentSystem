package bankmanager.loginsystem;

import bankmanager.admins.AdminsController;
import bankmanager.clients.ClientsController;
import bankmanager.database.ConnectorDB;
import bankmanager.database.dao.AdminDAO;
import bankmanager.database.dao.ClientDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 */

public class LoginController implements Initializable{
    
    private AdminDAO adminDAO = new AdminDAO();
    private ClientDAO clientDAO = new ClientDAO();
    
    @FXML
    private TextArea txtAreaClientInfo;

    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField inputLogin;
    
    @FXML
    private PasswordField inputPassword;
    
    @FXML
    private ComboBox<LoginOption> comboClientAdmin;
    
    @FXML
    private Button btnLogin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ConnectorDB.isConnected()){
            lblStatus.setText("Connected");
        } else {
            lblStatus.setText("Not connected");
        }
        this.comboClientAdmin.setItems(
                FXCollections.observableArrayList(LoginOption.values()));
    }
    
    @FXML
    public void login(ActionEvent e){
        String login = inputLogin.getText();
        String password = inputPassword.getText();
        LoginOption option = comboClientAdmin.getSelectionModel()
                .getSelectedItem();
        int id;
        switch(option){
            case admin:
                if((id = adminDAO.checkIfAccountExists(login, password)) > 0){
                    adminLogin(id);
                    closeWindow();
                } else {
                    showWrongCredentialsErrorMessage();
                }
                break;
            case client:
                if ((id = clientDAO.checkIfClientExists(login, password)) > 0) {
                    clientLogin(id);
                    closeWindow();
                } else {
                    showWrongCredentialsErrorMessage();
                }
                break;
        }
    }
    
    public void clientLogin(int id) {
        try { 
            URL resource = this.getClass().getClassLoader().getResource(
                     "bankmanager/clients/Clients.fxml");
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(resource.openStream());
            ClientsController controller = (ClientsController)loader
                    .getController();
            controller.setId(id);
            Scene scene = new Scene(root);
            Stage clientStage = new Stage();
            clientStage.setScene(scene);
            clientStage.setTitle("Clients dashboard");
            clientStage.setResizable(false);
            clientStage.show();
            controller.loadClientDetails();
            controller.disableFieldEditing();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
                                                                  null, ex);
        }
        
    }
   
    public void adminLogin(int id) {
        try {
            URL resource = this.getClass().getClassLoader().getResource(
                    "bankmanager/admins/Admins.fxml");
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(resource.openStream());
            AdminsController controller = (AdminsController)loader
                    .getController();
            controller.setId(id);
            Scene scene = new Scene(root);
            Stage adminStage = new Stage();
            adminStage.setScene(scene);
            adminStage.setTitle("Admins dashboard");
            adminStage.setResizable(false);
            adminStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
                                                                  null, ex);
        }
    }
    
    private void closeWindow(){
        Stage stage = (Stage)this.btnLogin.getScene().getWindow();
        stage.close();
    }
    
    private void showWrongCredentialsErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR, 
                                "You credentials are incorrect", ButtonType.OK);
        alert.showAndWait();
    } 
}
