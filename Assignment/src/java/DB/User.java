package DB;
import java.util.Date;

public class User {
    String id;
    String name;
    String email;
    String pass;
    Date dob;

    public User() {
    }

    public User(String id, String name, String email, String pass, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
        
}
