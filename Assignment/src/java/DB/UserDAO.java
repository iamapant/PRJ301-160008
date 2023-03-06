    package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends SettingsDAO {
    

    public ArrayList<User> getUsers() {
        ArrayList<User> al = new ArrayList<>();
        try{
            String sql = "select [id],[uName],[Email],[Pass],[DOB] from [Users]";
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("uName"));
                u.setEmail(rs.getString("Email"));
                u.setPass(rs.getString("Pass"));
                u.setDob(rs.getDate("DOB"));
                setSettings(u);
                al.add(u);
            }
            return al;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public User getUser(int id){
        try{
            User v = new User();
            String sql = "select id,uName,Email,Pass, DOB from Users where id = ?";
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                v.setId(rs.getInt("id"));
                v.setName(rs.getString("uName"));
                v.setEmail(rs.getString("Email"));
                v.setPass(rs.getString("Pass"));
                v.setDob(rs.getDate("DOB"));
                setSettings(v);
                return v;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public User getUserByLogin(String username, String password){
        try{
            String sql = "select [id],[uName],[Email],[Pass],[DOB] from Users where (uName like ? or Email like ?) and Pass like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            
            User u = new User();
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("uName"));
                u.setEmail(rs.getString("Email"));
                u.setPass(rs.getString("Pass"));
                u.setDob(rs.getDate("DOB"));
                setSettings(u);
            }
            return u;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public void insertUser(User u){
        try{
            String sql = "insert into Users(uName,Email,Pass,DOB) values"
                    + "(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            if(u.getName() != null)ps.setString(1, u.getName()); else ps.setString(1, "N'"+u.getName()+"'"); 
            if(u.getEmail() != null)ps.setString(2, u.getEmail());else ps.setString(2, "N'"+u.getEmail()+"'");
            if(u.getPass() != null)ps.setString(3, u.getPass());else ps.setString(3, "N'"+u.getPass()+"'");
            if(u.getDob()!= null)ps.setDate(4, u.getDob());else ps.setDate(4, u.getDob());
            updateSettings(u);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void updateUser(User u){
        try{
            String sql = "update [Users] set "
                    + "[uName] = ?, "
                    + "[Email] = ?, "
                    + "[Pass] = ?, "
                    + "[DOB] = ? "
                    + "where [id] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPass());
            ps.setDate(4, u.getDob());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void deleteUser(User u){
        try{
            String sql = "delete from Users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public User getUserByName(String name) {
        try{
            String sql = "select [id],[uName],[Email],[Pass],[DOB] from Users where uName like ? or Email like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            
            User u = new User();
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("uName"));
                u.setEmail(rs.getString("Email"));
                u.setPass(rs.getString("Pass"));
                u.setDob(rs.getDate("DOB"));
                setSettings(u);
            }
            return u;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;        
    }
}
