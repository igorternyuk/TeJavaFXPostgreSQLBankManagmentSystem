package bankmanager.admins;

import bankmanager.database.dao.AdminSearchType;
import bankmanager.database.dao.AdminDAO;
import bankmanager.database.dto.AccountType;
import bankmanager.database.dto.AdminDTO;
import bankmanager.database.dto.Gender;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
 * this.comboClientAdmin.setItems(
FXCollections.observableArrayList(LoginOption.values()));
LocalDate localDate = datePicker.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);
System.out.println(localDate + "\n" + instant + "\n" + date);
 */

public class AdminsController implements Initializable{
    private int loggedInAdminID;
    private AdminDAO dao = new AdminDAO();
    private ObservableList<AdminDTO> data;
    
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
    
    @FXML
    public void onBtnInsertAdminClicked(ActionEvent e){
        Alert alert;
        if(isAdminsFormFilled()){
            if(isEmailFieldCorrect(this.inputAdminEmail.getText())){
                if(dao.insert(readDataFromAdminsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION, "New admin "
                                      + "was successfully inserted to the "
                                      + "database", ButtonType.OK);
                    loadFullList();
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
                if(dao.update(readDataFromAdminsForm())){
                    alert = new Alert(Alert.AlertType.INFORMATION, "Admin's data"
                                      + " was successfully updated",
                                      ButtonType.OK);
                    loadFullList();
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
                if(dao.delete(idToDelete)){
                    alert = new Alert(Alert.AlertType.INFORMATION, "Register with"
                                            + " id = " + String.valueOf(idToDelete)
                                            + " was successfullu deleted from the "
                                            + "database", ButtonType.OK);
                    alert.showAndWait();
                    loadFullList();
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
        loadFullList();
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
        List<AdminDTO> searchResults = this.dao.search(filter, regExp);
        Alert alert;
        switch(searchResults.size()){
            case 0:
                alert = new Alert(Alert.AlertType.WARNING, "No registers found",
                                  ButtonType.OK);
                break;
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION, "One register was"
                                  + " found", ButtonType.OK);
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION, searchResults.size()
                                  + " registers were found", ButtonType.OK);
                break;
        }
        loadList(searchResults);
        alert.showAndWait();
        
    }
  /*Person person = taview.getSelectionModel().getSelectedItem();
System.out.println(person.getName());   */
        
        
        /*
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(tableview.getSelectionModel().getSelectedItem() != null) 
        {    
           TableViewSelectionModel selectionModel = tableview.getSelectionModel();
           ObservableList selectedCells = selectionModel.getSelectedCells();
           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
           Object val = tablePosition.getTableColumn().getCellData(newValue);
           System.out.println("Selected Value" + val);
         }
         }
     });
        
     ///Set date picker value
        
        public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}

try {
        datePicker.setValue(LOCAL_DATE("2016-05-01");
    } catch (NullPointerException e) {
    }
        
        //Get Value from date picker
        
        LocalDate localDate = datePicker.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);
System.out.println(localDate + "\n" + instant + "\n" + date);

        */
    @FXML
    public void onAdminsTableClicked(MouseEvent e){
        
        //System.out.println("Mouse click event");
    }
    
    //Clients
    
    @FXML
    private DatePicker datepicker;

    
    @FXML
    private ComboBox<Gender> comboGender;
    
    @FXML
    private ComboBox<AccountType> comboAccount;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //======================Admins==========================
        
        this.colAdminsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colAdminsEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colAdminsPasswword.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        this.comboAdminSearch.setItems(
                FXCollections.observableArrayList(AdminSearchType.values()));
        this.comboAdminSearch.getSelectionModel().selectFirst();
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
        
        this.comboGender.setItems(
                FXCollections.observableArrayList(Gender.values()));
        this.comboGender.getSelectionModel().selectFirst();
        this.comboAccount.setItems(
                FXCollections.observableArrayList(AccountType.values()));
        this.comboAccount.getSelectionModel().selectFirst();
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
    }
    
    private boolean isAdminsFormFilled(){
        return !this.inputAminID.getText().isEmpty() &&
               !this.inputAdminEmail.getText().isEmpty() &&
               !this.inputAdminPassword.getText().isEmpty();
    }
    
    private boolean isEmailFieldCorrect(String email){
        //String email = this.inputAdminEmail.getText();
        int numOfAtSigns = 0;
        for(int i = 0; i < email.length(); ++i){
            if(email.charAt(i) == '@'){
                ++numOfAtSigns;
            }
        }
        return numOfAtSigns == 1;
    }
    
    private void loadFullList(){
        this.data = FXCollections.observableArrayList(this.dao.readAll());        
        this.tableAdmins.setItems(null);
        this.tableAdmins.setItems(this.data);
    }
    
    private void loadList(List<AdminDTO> list){
        this.data = FXCollections.observableArrayList(list);        
        this.tableAdmins.setItems(null);
        this.tableAdmins.setItems(this.data);
    }

    public int getLoggedInAdminID() {
        return loggedInAdminID;
    }

    public void setLoggedInAdminID(int loggedInAdminID) {
        this.loggedInAdminID = loggedInAdminID;
    }    
}
