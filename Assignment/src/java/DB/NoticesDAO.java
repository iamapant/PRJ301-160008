/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Notice;
import model.User;

/**
 *
 * @author iamap
 */
public class NoticesDAO extends DBContext{
    
    public ArrayList<Notice> getNotices(User u){
        ArrayList<Notice> nl = new ArrayList<>();
        try{
            String sql = "select NoticeID,id,Title,Descr,Alert_Time,rep from Notices where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notice n = new Notice();
                n.setNid(rs.getInt("NoticeID")); 
                n.setId(rs.getInt("ID")); 
                n.setTitle(rs.getString("Title")); 
                n.setDescr(rs.getString("Descr")); 
                n.setA_Time(rs.getTimestamp("Alert_Time")); 
                n.setRepeatable(rs.getInt("rep"));
                nl.add(n);
            }
            return nl;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;
    }
    
    public void insertNotice(Notice n){
        try{
            String sql = "insert into Notices(id,Title,Descr,Alert_Time,rep) values"
                    + "(?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, n.getId());
            ps.setString(2, n.getTitle());
            ps.setString(3, n.getDescr());
            ps.setTimestamp(4, n.getA_Time());
            ps.setInt(5, n.getRepeatable());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public void updateNotice(Notice n){
        try{
            String sql = "update Notices set Title = ?, Descr = ?, Alert_Time = ?, rep = ? where NoticeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getDescr());
            ps.setTimestamp(3, n.getA_Time());
            ps.setInt(4, n.getRepeatable());
            ps.setInt(5, n.getNid());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public void updateRepeatableNotices(int id){
        ArrayList<Notice> nl = new ArrayList<>();
        try{
            String sql = "select NoticeID,id,Title,Descr,Alert_Time,rep from Notices where rep != 0 and id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notice n = new Notice();
                n.setNid(rs.getInt("NoticeID")); 
                n.setId(rs.getInt("ID")); 
                n.setTitle(rs.getString("Title")); 
                n.setDescr(rs.getString("Descr")); 
                n.setA_Time(rs.getTimestamp("Alert_Time")); 
                n.setRepeatable(rs.getInt("rep"));
                nl.add(n);
            }
            long day = 1000*60*60*24;
            long[] rep = {0,day,day*7,day*7*2,day*7*4,day*7*4*12};
            for(Notice n : nl){
                long t = n.getA_Time().getTime();
                while(n.getA_Time().before(new Date(System.currentTimeMillis()))){
                    t = t+rep[n.getRepeatable()];
                    n.setA_Time(new Timestamp(t));
                }
                updateNotice(n);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }      
    }
    
    public ArrayList<Notice> getUpcomingNotices(User u){
        ArrayList<Notice> nl = new ArrayList<>();
        try{
            String sql = "select NoticeID,id,Title,Descr,Alert_Time,rep from Notices where id = ? and Alert_Time >= ? order by Alert_Time";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notice n = new Notice();
                n.setNid(rs.getInt("NoticeID")); 
                n.setId(rs.getInt("ID")); 
                n.setTitle(rs.getString("Title")); 
                n.setDescr(rs.getString("Descr")); 
                n.setA_Time(rs.getTimestamp("Alert_Time")); 
                n.setRepeatable(rs.getInt("rep"));
                nl.add(n);
            }
            return nl;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;
    }
    
    public void deleteNotice(Notice n){
        try{
            String sql = "delete from Notices where NoticeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, n.getNid());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
    }
    
    public ArrayList<Notice> searchNotice(User u, String s){
        try{
            ArrayList<Notice> nl = new ArrayList<>();
            String sql = "select [NoticeID],[id],[Title],[Descr],[Alert_Time],[rep] from Notices where [id] = ? and [Title] like '%?%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.setString(2, s);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notice n = new Notice();
                n.setNid(rs.getInt("NoticeID"));
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("Title"));
                n.setDescr(rs.getString("Descr"));
                n.setA_Time(rs.getTimestamp("Alert_Time"));
                n.setRepeatable(rs.getInt("rep"));
                nl.add(n);
            }
            return nl;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;
    }

    public Notice getNoticeByID(int id) {
        try{
            String sql = "select NoticeID,id,Title,Descr,Alert_Time,rep from Notices where NoticeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Notice n = new Notice();
            if(rs.next()){
                n.setNid(rs.getInt("NoticeID")); 
                n.setId(rs.getInt("ID")); 
                n.setTitle(rs.getString("Title")); 
                n.setDescr(rs.getString("Descr")); 
                n.setA_Time(rs.getTimestamp("Alert_Time")); 
                n.setRepeatable(rs.getInt("rep"));
            }
            return n;
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);     
        }
        return null;        
    }
}
