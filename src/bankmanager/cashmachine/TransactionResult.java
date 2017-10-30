package bankmanager.cashmachine;

/**
 *
 * @author igor
 * Last edited 30-10-2017
 */
public enum TransactionResult {
    SUCCESS("Transaction was finished successfully!"), 
    FAILURE("You have not enough money on you account!"),
    TOO_BIG_SUMM("Requested sum is to big!"),
    NO_CLIENT("Cash machine has no clients");
    private final String description;
    private TransactionResult(String description){
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
