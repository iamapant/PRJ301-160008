package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SugNotice;

public class SugNoticeDAO extends DBContext {
    
    public ArrayList<SugNotice> getSugNotices(){
        ArrayList<SugNotice> sl = new ArrayList<>();
        try{
            String sql = "select SugID,Title,Descr,Alert_Date from Sugg_Notice";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SugNotice s = new SugNotice();
                s.setSID(rs.getInt("SugID"));
                s.setTitle(rs.getString("Title"));
                s.setDescr(rs.getString("Descr"));
                s.setAlert_Date(rs.getDate("Alert_Date"));
                sl.add(s);
            }
            return sl;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;
    }
    
    public void insertSugNotice(SugNotice s){
        try{
            String sql = "insert into Sugg_Notice (SugID,Title,Descr,Alert_Date) values"
                    + "(?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, s.getSID());
            ps.setString(2, s.getTitle());
            ps.setString(3, s.getDescr());
            ps.setDate(4, s.getAlert_Date());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public void updateNotice(SugNotice n){
        try{
            String sql = "update [Sugg_Notice] set"
                    + "[Title] = ?, "
                    + "[Descr] = ?, "
                    + "[Alert_Date] = ?, "
                    + "where SugID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getDescr());
            ps.setDate(3, n.getAlert_Date());
            ps.setInt(5, n.getSID());
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public void deleteNotice(SugNotice n){
        try{
            String sql = "delete from Sugg_Notice where SugID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, n.getSID());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public ArrayList<SugNotice> searchNotice(String s){
        try{
            ArrayList<SugNotice> sl = new ArrayList<>();
            String sql = "select [SugID],[Title],[Descr],[Alert_Date],[rep] from Notices where [Title] like '%?%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SugNotice n = new SugNotice();
                n.setSID(rs.getInt("SugID"));
                n.setTitle(rs.getString("Title"));
                n.setDescr(rs.getString("Descr"));
                n.setAlert_Date(rs.getDate("Alert_Date"));
                sl.add(n);
            }
            return sl;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;
    }
    
}
