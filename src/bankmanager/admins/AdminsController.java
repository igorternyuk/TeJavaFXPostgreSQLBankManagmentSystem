package bankmanager.admins;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 */
public class AdminsController implements Initializable{
    private int id;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
