/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Settings;
import model.User;

/**
 *
 * @author iamap
 */
public class SettingsDAO extends DBContext {
    
    
    public Settings getSettings(User u) {
        Settings s = new Settings();
        try{
            String sql = "select id, QN_num, QN_period, Alert_Time1, Alert_Time2, Alert_Time3 from Settings where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                s.setId(rs.getInt("id"));
                s.setQ_Notice(rs.getInt("Quick_Notice"));
                s.setQN_period(rs.getString("QN_period"));
                s.setA_Time1(rs.getTime("Alert_Time1"));
                s.setA_Time2(rs.getTime("Alert_Time2"));
                s.setA_Time3(rs.getTime("Alert_Time3"));
    //            u.setSetting(s);
                return s;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);            
        }
        return null;
    }
    
    public void setSettings(User u) {
        Settings s = new Settings();
        try{
            String sql = "select id, QN_num, QN_period, Alert_Time1, Alert_Time2, Alert_Time3 from Settings where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                s.setId(rs.getInt("id"));
                s.setQ_Notice(rs.getInt("QN_num"));
                s.setQN_period(rs.getString("QN_period"));
                s.setA_Time1(rs.getTime("Alert_Time1"));
                s.setA_Time2(rs.getTime("Alert_Time2"));
                s.setA_Time3(rs.getTime("Alert_Time3"));
                u.setSetting(s);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);            
        }
        
    }
    public void updateSettings(User u){
        try{
            String sql = "update [Settings] set "
                    + "[QN_num] = ?, "
                    + "[QN_period] = ?, "
                    + "[Alert_Time1] = ?, "
                    + "[Alert_Time2] = ?, "
                    + "[Alert_Time3] = ? "
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getSetting().getQ_Notice());
            ps.setString(2, u.getSetting().getQN_period());
            ps.setTime(3, u.getSetting().getA_Time1());
            ps.setTime(4, u.getSetting().getA_Time2());
            ps.setTime(5, u.getSetting().getA_Time3());
            ps.setInt(6, u.getId());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);              
        }
    }
    
}
