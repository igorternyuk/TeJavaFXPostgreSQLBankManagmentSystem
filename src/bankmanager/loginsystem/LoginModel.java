package bankmanager.loginsystem;

/**
 *
 * @author igor
 * Last edited 28-10-2017
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bankmanager.database.ConnectorDB;

public class LoginModel {
    
    private static final String SQL_CHECK_IF_LOGGED_IN = "SELECT * FROM login "
            + "WHERE username = ? and password = ? and option = ?; ";
    public LoginModel(){

    }
    
    public void printList(){
        /*PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement("SELECT * FROM login;");
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String opt = rs.getString(4);
                System.out.println("id = " + id + " username = " + username +
                        " password = " + password + " opt = " + opt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public boolean isDatabaseConnected(){
        return this.con != null;
    }
    
    public boolean isLogin(String user, String password, String opt){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.prepareStatement(SQL_CHECK_IF_LOGGED_IN);
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setString(3, opt);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;*/
    }
    
    
    /*public static void main(String[] args) {
        LoginModel model = new LoginModel();
        model.printList();
    }*/
}
