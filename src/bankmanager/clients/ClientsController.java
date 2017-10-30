package bankmanager.clients;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 */

public class ClientsController implements Initializable {
    private String login;
    private String password;
    /*               <Button fx:id="btnDeposit" mnemonicParsing="false" text="Deposit" />
                  <Button fx:id="btnWithdraw" mnemonicParsing="false" text="Withdraw" />
                  <Button fx:id="btnClientWindowClose" mnemonicParsing="false" text="Exit" />*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
