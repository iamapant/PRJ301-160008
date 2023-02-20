package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDAO<T> extends DBContext{
    public Connection connection;
    
    
    
    public BaseDAO()
    {
//        connection = getConnection();
        try {
            //Change the username password and url to connect your own database
            String username = "sa";
            String password = "1";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment;integratedSecurity=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    
}
