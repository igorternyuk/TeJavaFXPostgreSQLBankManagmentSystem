package bankmanager.loginsystem;

/**
 *
 * @author igor
 */
public enum LoginOption {
    admin, client;
    
    public static LoginOption option(String v){
        return valueOf(v);
    }
}
