package bankmanager.database.dto;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author igor
 * 
 * CREATE TABLE public.admins
(
  id integer NOT NULL DEFAULT nextval('admins_id_seq'::regclass),
  email character varying(50) NOT NULL,
  pass character varying(100) NOT NULL,
  CONSTRAINT admins_pkey PRIMARY KEY (id)
)
 */
public class AdminDTO {
    private SimpleIntegerProperty id;
    private SimpleStringProperty email;
    SimpleStringProperty password;

    public AdminDTO() {
    }
    
    public AdminDTO(String email, String password) {
        this.email.set(email);
        this.password.setValue(password);
    }

    public AdminDTO(int id, String email, String password) {
        this.id.set(id);
        this.email.set(email);
        this.password.setValue(password);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        if(email.contains("@")){
            this.email.set(email);
        }
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.email);
        hash = 43 * hash + Objects.hashCode(this.password);
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
        final AdminDTO other = (AdminDTO) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "id=" + this.id.get() + ", email=" + 
                this.email.get() + ", password=" + this.password.get() + '}';
    }
    
    
}
