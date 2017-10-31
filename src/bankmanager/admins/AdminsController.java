package bankmanager.admins;

import bankmanager.database.dao.AdminSearchType;
import bankmanager.database.dao.AdminDAO;
import bankmanager.database.dao.ClientDAO;
import bankmanager.database.dao.ClientSearchType;
import bankmanager.database.dto.AccountType;
import bankmanager.database.dto.AdminDTO;
import bankmanager.database.dto.ClientDTO;
import bankmanager.database.dto.Gender;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 * 1.Всем нужным елементам GUI присвоить ID
 * 2.Прописать обработчики событий для кнопок
 * 
 * 
LocalDate localDate = datePicker.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);
System.out.println(localDate + "\n" + instant + "\n" + date);
 */

public class AdminsController implements Initializable{
    private int loggedInAdminID;
    private AdminDAO daoAdmin = new AdminDAO();
    private ObservableList<AdminDTO> adminsData;
    private ClientDAO daoClient = new ClientDAO();
    private ObservableList<ClientDTO> clientsData;
    //Admins
    @FXML
    private TextField inputAminID;
    
    @FXML
    private TextField inputAdminEmail;
    
    @FXML
    private TextField inputAdminPassword;
    
    @FXML
    private Button btnInsertAdmin;
    
    @FXML
    private Button btnUpdateAdmin;
    
    @FXML
    private Button btnDeleteAdmin;
    
    @FXML
    private Button btnReloadAdminTable;
    
    @FXML
    private Button btnClearAdminForm;
    
    @FXML
    private TextField inputAdminSearchField;
    
    @FXML
    private Button btnSearchAdmin;
    
    @FXML
    private ComboBox<AdminSearchType> comboAdminSearch;
    
    @FXML
    private TableView<AdminDTO> tableAdmins;
    
    @FXML
    private TableColumn<AdminDTO, Integer> colAdminsID;
    
    @FXML
    private TableColumn<AdminDTO, String> colAdminsEmail;
        
    @FXML
    private TableColumn<AdminDTO, String> colAdminsPasswword;
    
    //======================Clients==========================
    
    @FXML
    private TextField inputClientsID;
    
    @FXML
    private TextField inputClientsFirstname;
    
    @FXML
    private TextField inputClientsSurname;
    
    @FXML
    private DatePicker dobClient;
    
    @FXML
    private ComboBox<Gender> comboGender;
    
    @FXML
    private TextField inputClientsAddress;
    
    @FXML
    private TextField inputClientsPhone;
    
    @FXML
    private TextField inputClientsEmail;
    
    @FXML
    private ComboBox<AccountType> comboAccount;
    
    @FXML
    private TextField inputClientsBalance;
    
    @FXML
    private TextField inputClientsPassword;
    
    @FXML
    private Button btnClientsInsert;
    
    @FXML
    private Button btnClientsUpdate;
    
    @FXML
    private Button btnClientsDelete;
    
    @FXML
    private Button btnClientsClearForm;
    
    @FXML
    private TextField inputClientsSearchField;
    
    @FXML
    private Button btnSearchClient;
    
    @FXML
    private ComboBox<ClientSearchType> comboClientSearch;
    
    @FXML
    private Button btnLoadClientsList;
    
    @FXML
    private TableColumn<ClientDTO, Integer> colClientsID;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsName;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsSurname;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsDOB;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsGender;
        
    @FXML
    private TableColumn<ClientDTO, String> colClientsAddress;
            
    @FXML
    private TableColumn<ClientDTO, String> colClientsPhone;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsEmail;
    
    @FXML
    private TableColumn<ClientDTO, String> colClientsAccount;
        
    @FXML
    private TableColumn<ClientDTO, Double> colClientsBalance;
            
    @FXML
    private TableColumn<ClientDTO, String> colClientsPassword;
    
    @FXML
    private TableView<ClientDTO> tableClients;
    
    @FXML
    private BarChart barChart;
    
    @FXML
    public void onBtnInsertAdminClicked(ActionEvent e){
        Alert alert;
        if(isAdminsFormFilled()){
            if(isEmailFieldCorrect(this.inputAdminEmail.getText())){
                if(daoAdmin.insert(readDataFromAdminsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION, "New admin "
                                      + "was successfully inserted to the "
                                      + "database", ButtonType.OK);
                    loadAdminsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Insertion failed",
                                      ButtonType.OK);
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect E-mail "
                                 + "format", ButtonType.OK);                
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the from"
                              + " before insertion", ButtonType.OK);
        }        
        alert.showAndWait();
    }
    
    @FXML
    public void onBtnUpdateAdminClicked(ActionEvent e){
        Alert alert;
        if(isAdminsFormFilled()){
            if(isEmailFieldCorrect(this.inputAdminEmail.getText())){
                if(daoAdmin.update(readDataFromAdminsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION,
                                     "Admin's data was successfully updated",
                                      ButtonType.OK);
                    loadAdminsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Update failed",
                                      ButtonType.OK);
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect E-mail"
                                  + " format", ButtonType.OK);                
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the from"
                              + " before insertion", ButtonType.OK);
        }        
        alert.showAndWait();
    }
    
    @FXML
    public void onBtnDeleteAdminClicked(ActionEvent e){
        Alert alert;
        if(!this.inputAminID.getText().isEmpty()){
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION,
                                            "Do you really want to delete "
                                            + "selected rigister?",
                                            ButtonType.YES, ButtonType.NO);
            confirmDialog.showAndWait();
            if(confirmDialog.getResult() == ButtonType.YES){
                int idToDelete = Integer.valueOf(this.inputAminID.getText());
                if(daoAdmin.delete(idToDelete)){
                    alert = new Alert(Alert.AlertType.INFORMATION,
                                     "Register with id = " + 
                                     String.valueOf(idToDelete)
                                     + " was successfullu deleted from the "
                                     + "database", ButtonType.OK);
                    alert.showAndWait();
                    loadAdminsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Could not delete"
                                      + " register with such id", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the "
                              + "from before insertion", ButtonType.OK);
            alert.showAndWait();            
        }        
    }    
        
    @FXML
    public void onBtnReloadAdminTable(ActionEvent e){
        loadAdminsFullList();
    }
    
    @FXML
    public void onBtnClearAdminsForm(ActionEvent e){
        clearAdminsForm();
    }
    
    @FXML
    public void onBtnSearchForAdmin(ActionEvent e){
        AdminSearchType filter = this.comboAdminSearch.getSelectionModel()
                                 .getSelectedItem();
        String regExp = this.inputAdminSearchField.getText();
        List<AdminDTO> searchResults = this.daoAdmin.search(filter, regExp);
        Alert alert;
        switch(searchResults.size()){
            case 0:
                alert = new Alert(Alert.AlertType.WARNING, "No admins found",
                                  ButtonType.OK);
                break;
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION, "One admin was"
                                  + " found", ButtonType.OK);
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION,
                                  searchResults.size()
                                  + " admins were found", ButtonType.OK);
                break;
        }
        loadAdminsList(searchResults);
        alert.showAndWait();        
    }
    
    @FXML
    public void onBtnInsertClientClicked(ActionEvent event){
        Alert alert;
        if(isClientsFormFilled()){
            if(isEmailFieldCorrect(this.inputClientsEmail.getText())){
                if(daoClient.insert(readDataFromClientsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION, "New client "
                                      + "was successfully inserted to the "
                                      + "database", ButtonType.OK);
                    loadClientsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Insertion failed",
                                      ButtonType.OK);
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect E-mail "
                                 + "format", ButtonType.OK);                
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the from"
                              + " before insertion", ButtonType.OK);
        }        
        alert.showAndWait();
    }
    
    @FXML
    public void onBtnUpdateClientClicked(ActionEvent event){
        Alert alert;
        if(isClientsFormFilled()){
            if(isEmailFieldCorrect(this.inputClientsEmail.getText())){
                if(daoClient.update(readDataFromClientsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION,
                                     "Client's data was successfully updated",
                                      ButtonType.OK);
                    loadClientsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Update failed",
                                      ButtonType.OK);
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect E-mail"
                                  + " format", ButtonType.OK);                
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the from"
                              + " before insertion", ButtonType.OK);
        }        
        alert.showAndWait();
    }
    
    @FXML
    public void onBtnDeleteClientClicked(ActionEvent event){
        Alert alert;
        if(!this.inputClientsID.getText().isEmpty()){
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION,
                                            "Do you really want to delete "
                                            + "selected rigister?",
                                            ButtonType.YES, ButtonType.NO);
            confirmDialog.showAndWait();
            if(confirmDialog.getResult() == ButtonType.YES){
                int idToDelete = Integer.valueOf(this.inputClientsID.getText());
                if(daoClient.delete(idToDelete)){
                    alert = new Alert(Alert.AlertType.INFORMATION, "Client with"
                                      + " id = " + String.valueOf(idToDelete)
                                      + " was successfullu deleted from the "
                                      + "database", ButtonType.OK);
                    alert.showAndWait();
                    loadClientsFullList();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Could not delete"
                                      + " client with such id", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "You must fill the "
                              + "from before insertion", ButtonType.OK);
            alert.showAndWait();            
        } 
    }
    
    @FXML
    public void onBtnReloadClientsTable(ActionEvent e){
        loadClientsFullList();
    }
    
    @FXML
    public void onBtnClearClientsFormClicked(ActionEvent event){
        clearClientsForm();
    }
    
    @FXML
    public void onBtnSearchForClientClicked(ActionEvent event){
        ClientSearchType filter = this.comboClientSearch.getSelectionModel()
                                 .getSelectedItem();
        String regExp = this.inputClientsSearchField.getText();
        List<ClientDTO> searchResults = this.daoClient.search(filter, regExp);
        Alert alert;
        switch(searchResults.size()){
            case 0:
                alert = new Alert(Alert.AlertType.WARNING, "No clients found",
                                  ButtonType.OK);
                break;
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION, "One client was"
                                  + " found", ButtonType.OK);
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION,
                                  searchResults.size()
                                  + " clients were found", ButtonType.OK);
                break;
        }
        loadClientsList(searchResults);
        alert.showAndWait();
    }
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //======================Admins==========================
        
        this.colAdminsID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        this.colAdminsEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        this.colAdminsPasswword.setCellValueFactory(
                new PropertyValueFactory<>("password"));
        
        this.comboAdminSearch.setItems(
                FXCollections.observableArrayList(AdminSearchType.values()));
        this.comboAdminSearch.getSelectionModel().select(AdminSearchType.email);
        this.tableAdmins.setRowFactory( tv -> {
            TableRow<AdminDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                AdminDTO rowData = row.getItem();
                fillAdminsFormFromData(rowData);
                System.out.println(rowData);
            }
            });
            return row;
        } );        
        
        //=====================Clients=============================
        this.colClientsID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        this.colClientsName.setCellValueFactory(
                new PropertyValueFactory<>("firstname"));
        this.colClientsSurname.setCellValueFactory(
                new PropertyValueFactory<>("surname"));
        this.colClientsDOB.setCellValueFactory(
                new PropertyValueFactory<>("dob"));
        this.colClientsGender.setCellValueFactory(
                new PropertyValueFactory<>("gender"));
        this.colClientsAddress.setCellValueFactory(
                new PropertyValueFactory<>("address"));
        this.colClientsPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        this.colClientsEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        this.colClientsAccount.setCellValueFactory(
                new PropertyValueFactory<>("account"));
        this.colClientsBalance.setCellValueFactory(
                new PropertyValueFactory<>("balance"));
        this.colClientsPassword.setCellValueFactory(
                new PropertyValueFactory<>("password"));
        
        this.comboGender.setItems(
                FXCollections.observableArrayList(Gender.values()));
        this.comboGender.getSelectionModel().selectFirst();
        this.comboAccount.setItems(
                FXCollections.observableArrayList(AccountType.values()));
        this.comboAccount.getSelectionModel().selectFirst();
        this.comboClientSearch.setItems(
                FXCollections.observableArrayList(ClientSearchType.values())
        );
        this.comboClientSearch.getSelectionModel().select(
                ClientSearchType.surname);
        
        this.tableClients.setRowFactory( tv -> {
            TableRow<ClientDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                ClientDTO rowData = row.getItem();
                fillClientsFormFromData(rowData);
                System.out.println(rowData);
            }
            });
            return row;
        } );
    }
    
    private ClientDTO readDataFromClientsForm(){
        int ID = Integer.valueOf(this.inputClientsID.getText());
        String firstname = this.inputClientsFirstname.getText();
        String lastname = this.inputClientsSurname.getText();
        LocalDate localDate = this.dobClient.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(
                ZoneId.systemDefault()));
        Date date = Date.from(instant);
        System.out.println("Date = " + date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String dob = sqlDate.toString();
        Gender gender = comboGender.getSelectionModel().getSelectedItem();
        String address = this.inputClientsAddress.getText();
        String phone = this.inputClientsPhone.getText();
        String email = this.inputClientsEmail.getText();
        AccountType account = this.comboAccount.getSelectionModel()
                             .getSelectedItem();
        double balance = Double.valueOf(this.inputClientsBalance.getText());
        String pass = this.inputClientsPassword.getText();
        ClientDTO client = new ClientDTO(ID, firstname, lastname, dob,
                                         gender, address, phone, email,
                                         account, balance, pass);
        return client;
    }
    
    private void fillClientsFormFromData(ClientDTO client){
        this.inputClientsID.setText(String.valueOf(client.getId()));
        this.inputClientsFirstname.setText(client.getFirstname());
        this.inputClientsSurname.setText(client.getSurname());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(client.getDob(), formatter);
        this.dobClient.setValue(localDate);
        this.comboGender.getSelectionModel().select(client.getGender());
        this.inputClientsAddress.setText(client.getAddress());
        this.inputClientsPhone.setText(client.getPhone());
        this.inputClientsEmail.setText(client.getEmail());
        this.comboAccount.getSelectionModel().select(client.getAccount());
        this.inputClientsBalance.setText(String.valueOf(client.getBalance()));
        this.inputClientsPassword.setText(client.getPassword());
    }
    
    private void clearClientsForm(){
        this.inputClientsID.setText("");
        this.inputClientsFirstname.setText("");
        this.inputClientsSurname.setText("");
        this.dobClient.setValue(LocalDate.now());
        this.comboGender.getSelectionModel().selectFirst();
        this.inputClientsAddress.setText("");
        this.inputClientsPhone.setText("");
        this.inputClientsEmail.setText("");
        this.comboAccount.getSelectionModel().selectFirst();
        this.inputClientsBalance.setText("");
        this.inputClientsPassword.setText("");
        this.inputClientsSearchField.setText("");
        loadClientsFullList();
        this.tableClients.getSelectionModel().clearSelection();        
    }
    
    private boolean isClientsFormFilled(){
        return !this.inputClientsID.getText().isEmpty() &&
               !this.inputClientsFirstname.getText().isEmpty() &&
               !this.inputClientsSurname.getText().isEmpty() &&
               !this.inputClientsAddress.getText().isEmpty() &&
               !this.inputClientsPhone.getText().isEmpty() &&
               !this.inputClientsEmail.getText().isEmpty() &&
               !this.inputClientsBalance.getText().isEmpty() &&
               !this.inputClientsPassword.getText().isEmpty();
    }
    
    private AdminDTO readDataFromAdminsForm(){
        int ID = Integer.valueOf(this.inputAminID.getText());
        String email = this.inputAdminEmail.getText();
        String password = this.inputAdminPassword.getText();
        AdminDTO admin = new AdminDTO(ID, email, password);
        return admin;
    }
    
    private void fillAdminsFormFromData(AdminDTO admin){
        this.inputAminID.setText(String.valueOf(admin.getId()));
        this.inputAdminEmail.setText(admin.getEmail());
        this.inputAdminPassword.setText(admin.getPassword());
    }
    
    private void clearAdminsForm(){
        this.inputAminID.setText("");
        this.inputAdminEmail.setText("");
        this.inputAdminPassword.setText("");
        this.inputAdminSearchField.setText("");
        loadAdminsFullList();
        this.tableAdmins.getSelectionModel().clearSelection();
    }
    
    private boolean isAdminsFormFilled(){
        return !this.inputAminID.getText().isEmpty() &&
               !this.inputAdminEmail.getText().isEmpty() &&
               !this.inputAdminPassword.getText().isEmpty();
    }
    
    private boolean isEmailFieldCorrect(String email){
        int numOfAtSigns = 0;
        for(int i = 0; i < email.length(); ++i){
            if(email.charAt(i) == '@'){
                ++numOfAtSigns;
            }
        }
        return numOfAtSigns == 1;
    }
    
    private void loadClientsFullList(){
        this.clientsData = FXCollections.observableArrayList(this.daoClient
                .readAll());
        this.tableClients.setItems(null);
        this.tableClients.setItems(clientsData);
    }
    
    private void loadClientsList(List<ClientDTO> list){
        this.clientsData = FXCollections.observableArrayList(list);
        this.tableClients.setItems(null);
        this.tableClients.setItems(this.clientsData);
    }
    
    private void loadAdminsFullList(){
        this.adminsData = FXCollections.observableArrayList(this.daoAdmin
                .readAll());        
        this.tableAdmins.setItems(null);
        this.tableAdmins.setItems(this.adminsData);
    }
    
    private void loadAdminsList(List<AdminDTO> list){
        this.adminsData = FXCollections.observableArrayList(list);        
        this.tableAdmins.setItems(null);
        this.tableAdmins.setItems(this.adminsData);
    }

    public int getLoggedInAdminID() {
        return loggedInAdminID;
    }

    public void setLoggedInAdminID(int loggedInAdminID) {
        this.loggedInAdminID = loggedInAdminID;
    }    
}
