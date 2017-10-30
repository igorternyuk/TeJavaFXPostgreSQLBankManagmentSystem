package bankmanager.clients;

import bankmanager.cashmachine.CashMashine;
import bankmanager.cashmachine.TransactionResult;
import bankmanager.database.dao.ClientDAO;
import bankmanager.database.dto.ClientDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 */

public class ClientsController implements Initializable {
    private int id;
    private final ClientDAO dao = new ClientDAO();
    private CashMashine cashMachine = new CashMashine();
    
    @FXML
    Button btnLoadDetails;
    
    @FXML
    Button btnDeposit;
    
    @FXML
    Button btnWithdraw;
    
    @FXML
    Button btnClientWindowClose;
    
    @FXML
    TextArea txtAreaClientInfo;
    
    @FXML
    TextField txtClientBalance;
    
    @FXML
    TextField txtClientSum;
    
    @FXML
    public void onBtnLoadDetailsClicked(){
        loadClientDetails();
    }
    
    @FXML
    public void onBtnDepositClicked(){
        try{
            double sum = Double.parseDouble(txtClientSum.getText());            
            ClientDTO client = dao.readById(id);
            cashMachine.setClient(client);
            TransactionResult result = cashMachine.deposit(sum);
            if(result == TransactionResult.SUCCESS){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, 
                                "Success", ButtonType.OK);
                loadClientDetails();
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, 
                                result.getDescription(), ButtonType.OK);
                alert.showAndWait();
            }
            
        } catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, 
                                "Wrong input", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
    @FXML
    public void onBtnWithdrawClicked(){
        try{
            double sum = Double.parseDouble(txtClientSum.getText());            
            ClientDTO client = dao.readById(id);
            cashMachine.setClient(client);
            TransactionResult result = cashMachine.withdraw(sum);
            if(result == TransactionResult.SUCCESS){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, 
                                "Success", ButtonType.OK);
                loadClientDetails();
                alert.showAndWait();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, 
                                "Failure", ButtonType.OK);
                alert.showAndWait();
            }
            
        } catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, 
                                "Wrong input", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
    @FXML
    public void onBtnExit(){
        Stage stage = (Stage)this.btnDeposit.getScene().getWindow();
        stage.close();
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void loadClientDetails(){
        txtAreaClientInfo.clear();
        txtAreaClientInfo.appendText("************Client details*************\n");
        ClientDTO client = dao.readById(id);
        txtAreaClientInfo.appendText("----------------------------------------------\n");
        txtAreaClientInfo.appendText("ID:\t" + client.getId() + "\n");
        txtAreaClientInfo.appendText("Name:\t" + client.getFirstname() + "\n");
        txtAreaClientInfo.appendText("Surname:\t" + client.getSurname() + "\n");
        txtAreaClientInfo.appendText("Date of birth:\t" + client.getDob()+ "\n");
        txtAreaClientInfo.appendText("Gender:\t" + client.getGender() + "\n");
        txtAreaClientInfo.appendText("Address:\t" + client.getAddress() + "\n");
        txtAreaClientInfo.appendText("Phone:\t" + client.getPhone() + "\n");
        txtAreaClientInfo.appendText("E-mail:\t" + client.getEmail() + "\n");
        txtAreaClientInfo.appendText("Acount type:\t" + client.getAccount() + "\n");
        txtAreaClientInfo.appendText("Money balance:\t" + client.getBalance() + "\n");
        txtAreaClientInfo.appendText("You password:\t" + client.getPassword() + "\n");
        txtAreaClientInfo.appendText("----------------------------------------------");
        
        
        txtClientBalance.setText("$" + String.valueOf(client.getBalance()));
    }
    
    public void disableFieldEditing(){
        txtAreaClientInfo.setEditable(false);
        txtClientBalance.setEditable(false);
    }
    

}
