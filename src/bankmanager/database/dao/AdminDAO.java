package bankmanager.database.dao;

import bankmanager.database.ConnectorDB;
import bankmanager.database.dto.AdminDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 * Last edited 29-10-2017
 *   id integer NOT NULL DEFAULT nextval('admins_id_seq'::regclass),
  email character varying(50) NOT NULL,
  pass character varying(100) NOT NULL,
 */

public class AdminDAO implements ObligationDAO<AdminDTO>{
    private static final ConnectorDB connector = ConnectorDB.getInstance();
    private static final String SQL_INSERT = "INSERT INTO admins VALUES(?,?,"
            + "?);";
    private static final String SQL_UPDATE = "UPDATE admins SET email = ?,"
            + " pass = ? WHERE id = ?;";
    private static final String SQL_DELETE = "DELETE FROM admins WHERE id = ?;";
    private static final String SQL_READ_BY_ID = "SELECT * FROM admins WHERE id "
            + "= ?;";
    private static final String SQL_READ_ALL = "SELECT * FROM admins;";
    private static final String SQL_CHECK_ACCOUNT = "SELECT * FROM admins WHERE"
            + " email = ? AND pass = ?;";

    @Override
    public boolean insert(AdminDTO obj) {
        PreparedStatement ps;
        try {
            ps = connector.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getPassword());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, 
                    ex);
        } finally {
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(AdminDTO obj) {
        PreparedStatement ps;
        try {
            ps = connector.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, obj.getEmail());
            ps.setString(2, obj.getPassword());
            ps.setInt(3, obj.getId());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE,
                                                           null, ex);
        } finally {
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = connector.getConnection().prepareStatement(SQL_DELETE);
            ps.setInt(1, (int)key);
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null,
                                                           ex);
        }  finally {
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public AdminDTO readById(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        AdminDTO admin = null;
        try {
            ps = connector.getConnection().prepareStatement(SQL_READ_BY_ID);
            ps.setInt(1, (int)key);
            rs = ps.executeQuery();
            while(rs.next()){
                admin = retrieveAdminFromResultSet(rs);
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null,
                                                           ex);
        } finally {
            connector.closeConnection();
        }
        return admin;
    }

    @Override
    public List<AdminDTO> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        List<AdminDTO> admins = new ArrayList<>();
        try {
            ps = connector.getConnection().prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            while(rs.next()){
                admins.add(retrieveAdminFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null,
                                                           ex);
        } finally {
            connector.closeConnection();
        }
        return admins;
    }

    @Override
    public List<AdminDTO> search(String filter, String regExp) {
        PreparedStatement ps;
        ResultSet rs;
        List<AdminDTO> admins = new ArrayList<>();
        try {
            String cmd = "SELECT * FROM admins WHERE " + filter + " LIKE '%" + 
                         regExp + "%';";
            ps = connector.getConnection().prepareStatement(cmd);
            rs = ps.executeQuery();
            while(rs.next()){
                admins.add(retrieveAdminFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connector.closeConnection();
        }

        return admins;
    }
    
    public int checkIfAccountExists(String email, String password){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connector.getConnection().prepareStatement(SQL_CHECK_ACCOUNT);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                AdminDTO admin = retrieveAdminFromResultSet(rs);
                return admin.getId();
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connector.closeConnection();
        }
        return -1;
    }
     
    private AdminDTO retrieveAdminFromResultSet(ResultSet rs) throws SQLException{
        AdminDTO admin = new AdminDTO(rs.getInt(1), rs.getString(2),
                                      rs.getString(3));
        return admin;       
    }
    
    
    /*public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        //AdminDTO admin = new AdminDTO(5,"watain666@mail.ru", "333");
       // dao.insert(admin);
        //dao.delete(5);
        boolean xmonad1319Exists = dao.checkIfAccountExists("x8monad@ukr.net",
                                                              "1319");
        if(xmonad1319Exists){
            System.out.println("Account exists");
        } else {
            System.out.println("Account doesn't exist");
        }
        List<AdminDTO> listado = dao.readAll();
        listado.forEach( e -> {
            System.out.println(e);
        });
    }*/
    
}
