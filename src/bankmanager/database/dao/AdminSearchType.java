package bankmanager.database.dao;

/**
 *
 * @author igor
 */
public enum AdminSearchType {
    id("id","By ID"),
    email("email","By E-mail address"),
    pass("pass","By password");
    private String value;
    private String description;
    private AdminSearchType(String value, String description){
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
