package bankmanager.database.dao;

import bankmanager.database.ConnectorDB;
import bankmanager.database.dto.AccountType;
import bankmanager.database.dto.ClientDTO;
import bankmanager.database.dto.Gender;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 * Last edited 29-10-2017
 * 
 */

public class ClientDAO implements ObligationDAO<ClientDTO>{
    private static final ConnectorDB connector = ConnectorDB.getInstance();
    private static final String SQL_INSERT = "INSERT INTO clients(" +
            "id, firstname, surname, dob, gender, address, phone, email,"+ 
            "account, balance, pass) VALUES(?,?,?,?,?::sex,?,?,?,?::account_type,"
            + "?,?);";
    private static final String SQL_UPDATE = "UPDATE clients SET firstname = ?,"
            + "surname = ?, dob = ?::date, gender = ?::sex, address = ?,"
            + " phone = ?, email = ?, account = ?::account_type, balance = ?,"
            + " pass = ? WHERE id = ?;";
    private static final String SQL_DELETE = "DELETE FROM clients WHERE id = ?;";
    private static final String SQL_READ_BY_ID = "SELECT * FROM clients WHERE"
            + " id = ?;";
    private static final String SQL_READ_ALL = "SELECT * FROM clients;";
    
    @Override
    public boolean insert(ClientDTO obj) {
        PreparedStatement ps;
        try {
            ps = connector.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getFirstname());
            ps.setString(3, obj.getSurname());
            String target = obj.getDob();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = df.parse(target);
            ps.setDate(4, new java.sql.Date(date.getTime()));
            ps.setString(5, obj.getGender().toString());
            ps.setString(6, obj.getAddress());
            ps.setString(7, obj.getPhone());
            ps.setString(8, obj.getEmail());
            ps.setString(9, obj.getAccount().toString());
            ps.setDouble(10, obj.getBalance());
            ps.setString(11, obj.getPassword());
            System.out.println("SQL = " + ps.toString());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(ClientDTO obj) {
        PreparedStatement ps;
        try {
            ps = connector.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, obj.getFirstname());
            ps.setString(2, obj.getSurname());
            ps.setString(3, obj.getDob());
            ps.setString(4, obj.getGender().toString());
            ps.setString(5, obj.getAddress());
            ps.setString(6, obj.getPhone());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getAccount().toString());
            ps.setDouble(9, obj.getBalance());
            ps.setString(10, obj.getPassword());
            ps.setInt(11, obj.getId());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
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
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public ClientDTO readById(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        ClientDTO client = null;
        try {
            ps = connector.getConnection().prepareStatement(SQL_READ_BY_ID);
            ps.setInt(1, (int)key);
            rs = ps.executeQuery();
            while(rs.next()){
                client = retrieveClientFromResulSet(rs);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        return client;        
    }

    @Override
    public List<ClientDTO> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        List<ClientDTO> clients = new ArrayList<>();
        try {
            ps = connector.getConnection().prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            while(rs.next()){
                clients.add(retrieveClientFromResulSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        
        return clients;
    }

    @Override
    public List<ClientDTO> search(String filter, String regExp) {
        PreparedStatement ps;
        ResultSet rs;
        List<ClientDTO> clients = new ArrayList<>();
        String cmd = "SELECT * FROM clients WHERE " + filter + " LIKE '%" +
                regExp + "%';";
        System.out.println("SQL = " + cmd);
        try {
            ps = connector.getConnection().prepareStatement(cmd);
            rs = ps.executeQuery();
            while(rs.next()){
                clients.add(retrieveClientFromResulSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        return clients;
    }
    
    public int checkIfClientExists(String email, String password){
        PreparedStatement ps;
        ResultSet rs;
        String cmd = "SELECT * FROM clients WHERE email = ? AND pass = ?;";
        try {
            ps = connector.getConnection().prepareStatement(cmd);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                ClientDTO client = retrieveClientFromResulSet(rs);
                return client.getId();
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null,
                                                            ex);
        } finally {
            connector.closeConnection();
        }
        return -1;
    }
    
    private ClientDTO retrieveClientFromResulSet(ResultSet rs) 
            throws SQLException{
        int id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String dob = rs.getString("dob");
        Gender gender = Gender.valueOf(rs.getString("gender"));
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        AccountType account = AccountType.valueOf(rs.getString("account"));
        double balance = rs.getDouble("balance");
        String password = rs.getString("pass");
        ClientDTO client = new ClientDTO(id, firstname, surname, dob, gender,
                                         address, phone, email, account,
                                         balance, password);
        return client;        
    }

    /*public static void main(String[] args) {
        ClientDAO dao = new ClientDAO();
        //Alwara Höfels
        ClientDTO client = new ClientDTO(18, "Alwara2", "Höfels", "1982-03-06",
                                         Gender.female, "Berlin", "+388797662941",
                                         "superalwarahoeffels82@gmail.com", 
                                         AccountType.premium, 750000000, "123");
        
        dao.insert(client);
        //dao.update(client);
        //dao.delete(18);
        List<ClientDTO> clients = dao.readAll();
        clients.forEach( e -> {
            System.out.println(e);
        });
    }*/
}
