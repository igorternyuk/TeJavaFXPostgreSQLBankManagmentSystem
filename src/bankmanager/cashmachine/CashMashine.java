package bankmanager.cashmachine;

import bankmanager.database.dao.ClientDAO;
import bankmanager.database.dto.AccountType;
import bankmanager.database.dto.ClientDTO;

/**
 *
 * @author igor
 * Last edited 27-10-2017
 */

public class CashMashine {
    private static final double WIDTHDRAW_LIMIT = 9999.99;
    private static final int PREMIUM_DEBT_LIMIT = 500;
    
    private ClientDTO client = null;
    private ClientDAO dao = new ClientDAO();
    
    public TransactionResult deposit(double money){
        if(client != null){
            double balance = client.getBalance();
            double newBalance = balance + money;
            client.setBalance(newBalance);
            dao.update(client);
            return TransactionResult.SUCCESS;           
        } else {
            return TransactionResult.NO_CLIENT;
        }        
    }
    
    public TransactionResult withdraw(double money){
        if(client != null){
            if(money > WIDTHDRAW_LIMIT){
                return TransactionResult.TOO_BIG_SUMM;
            } else {
                double balance = client.getBalance();
                AccountType accountType = client.getAccount();
                switch (accountType) {
                    case regular:
                        if(balance < money){
                            return TransactionResult.FAILURE;
                        } else {
                            double newBalance = balance - money;
                            client.setBalance(newBalance);
                            dao.update(client);
                            return TransactionResult.SUCCESS;
                        }
                    case premium:
                        if(balance < money && money > PREMIUM_DEBT_LIMIT){
                            return TransactionResult.FAILURE;
                        } else {
                            double newBalance = balance - money;
                            client.setBalance(newBalance);
                            dao.update(client);
                            return TransactionResult.SUCCESS;
                        }
                    default:
                        throw new AssertionError(accountType.name());
                }
                
            }
                      
        } else {
            return TransactionResult.NO_CLIENT;
        }
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
