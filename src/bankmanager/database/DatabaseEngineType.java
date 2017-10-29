package bankmanager.database;

/**
 *
 * @author igor
 */
public enum DatabaseEngineType {
    mysql("jdbc:mysql://", 3306),
    postgresql("jdbc:postgresql://", 5432);
    
    private final String urlPattern;
    private final int port;
    
    private DatabaseEngineType(String urlPattern, int port){
        this.urlPattern = urlPattern;
        this.port = port;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public int getPort() {
        return port;
    }
}
