package bankmanager.database.dto;

import java.util.Objects;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author igor
 * CREATE TABLE public.clients
(
  id integer NOT NULL DEFAULT nextval('clientes_id_seq'::regclass),
  firstname character varying(40) NOT NULL,
  surname character varying(60),
  dob date NOT NULL,
  gender sex DEFAULT 'male'::sex,
  address character varying(60) NOT NULL,
  phone character varying(20),
  email character varying(50),
  account account_type DEFAULT 'regular'::account_type,
  balance double precision,
  pass character varying(100),
  CONSTRAINT clients_pkey PRIMARY KEY (id)
)
 */
public class ClientDTO {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty firstname = new SimpleStringProperty();
    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty dob = new SimpleStringProperty();
    private final SimpleStringProperty gender = new SimpleStringProperty(); //Change to enum
    private final SimpleStringProperty address = new SimpleStringProperty();
    private final SimpleStringProperty phone = new SimpleStringProperty();
    private final SimpleStringProperty email = new SimpleStringProperty();
    private final SimpleStringProperty account = new SimpleStringProperty();//Change to enum
    private final SimpleDoubleProperty balance = new SimpleDoubleProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();

    public ClientDTO() {
    }

    public ClientDTO(int id, String firstname, String surname, String dob,
                     String gender, String address, String phone, String email,
                     String account, double balance, String password) {
        this.id.set(id);
        this.firstname.set(firstname);
        this.surname.set(surname);
        this.dob.set(dob);
        this.gender.set(gender);
        this.address.set(address);
        this.phone.set(phone);
        this.email.set(email);
        this.account.set(account);
        this.balance.set(balance);
        this.password.set(password);
    }

    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getDob() {
        return dob.get();
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAccount() {
        return account.get();
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public double getBalance() {
        return balance.get();
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstname);
        hash = 67 * hash + Objects.hashCode(this.surname);
        hash = 67 * hash + Objects.hashCode(this.dob);
        hash = 67 * hash + Objects.hashCode(this.gender);
        hash = 67 * hash + Objects.hashCode(this.address);
        hash = 67 * hash + Objects.hashCode(this.phone);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.account);
        hash = 67 * hash + Objects.hashCode(this.balance);
        hash = 67 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientDTO other = (ClientDTO) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        if (!Objects.equals(this.balance, other.balance)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "ClientDTO{" + "id=" + id.get() + ", firstname=" + firstname.get() + 
                ", surname=" + surname.get() + ", dob=" + dob.get() + ", gender=" + 
                gender.get() + ", address=" + address.get() + ", phone=" + phone.get() +
                ", email=" + email.get() + ", account=" + account.get() + ", balance=" + 
                balance.get() + ", password=" + password.get() + '}';
    }
}
