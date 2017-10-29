package bankmanager.database;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 * Last edited 28-10-2017
 */

public class ConnectorDB {    
    private static ConnectorDB instance = null;
    private String driver;
    private String server;
    private int port;
    private String databaseName;
    private String url;
    private String username;
    private String password;
    private Connection connection;
    public static synchronized ConnectorDB getInstance(){
        if(instance == null){
            instance = new ConnectorDB();
        }
        return instance;
    }

    private ConnectorDB() {
        try {
            this.driver = "org.postgresql.Driver";
            this.server = "localhost";
            this.port = 5432;
            this.databaseName = "db_bank";
            this.url = "jdbc:postgresql://" + server + ":" + 
                       String.valueOf(port) + "/" + "db_bank";
            this.username = "igorternyuk";
            this.password = "1319";
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username, 
                                                          password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectorDB.class.getName()).log(Level.SEVERE,
                                                           null, ex);
        }
    }
    
     private ConnectorDB(DatabaseEngineType dbEngineType, String driver, 
                         String server, int port, String databaseName,
                         String usename, String password) {
        try {
            this.driver = driver;
            this.server = server;
            this.port = port;
            this.databaseName = databaseName;
            this.url = dbEngineType.getUrlPattern() + server + ":" + 
                       String.valueOf(dbEngineType.getPort()) + "/" +
                       this.databaseName;
            this.username = usename;
            this.password = password;
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username,
                                                          password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectorDB.class.getName()).log(Level.SEVERE,
                                                              null, ex);
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection(){
        ConnectorDB.instance = null;
    }
    
    public static void main(String[] args) {
        try {
            Connection con = ConnectorDB.getInstance().getConnection();
            Statement st = con.createStatement();
            String cmd = "SELECT * FROM clients";
            ResultSet rs = st.executeQuery(cmd);
            while(rs.next()){
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String dob = rs.getDate("dob").toString();
                System.out.println("Name: " + name +
                            " surname: " + surname + " dob = " + dob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectorDB.class.getName()).log(Level.SEVERE, 
                                                              null, ex);
        }
    }
}


/*String url =  "jdbc:postgresql://localhost:5432/testPostgre";
        String usuario = "igor";
        String contraseña = "1319";
        
        try{
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(url, usuario, contraseña); 
                 Statement st = con.createStatement()) {
                String cmd = "SELECT * FROM clientes WHERE apellido LIKE '%Ma%' OR nombre LIKE '%J%';";
                ResultSet rs = st.executeQuery(cmd);
                while(rs.next()){
                    String id = rs.getString("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    System.out.println("ID: " + id + " Nombre: " + nombre +
                            " apellido: " + apellido);
                }
                
                rs.close();
            }
            
        }
        catch(ClassNotFoundException | SQLException ex){
            System.err.println("ERROR" + ex.getMessage());
        }*/