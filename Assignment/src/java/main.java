
import DB.NoticesDAO;
import DB.SettingsDAO;
import DB.UserDAO;
import java.sql.Time;
import java.util.List;
import model.Notice;
import model.Settings;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author iamap
 */
public class main {
    public static void main(String[] args) {
        
        
        User u = new User();
        u.setId(1);
        UserDAO ud  = new UserDAO();
//        u.setId(2);
//        u.setName("NC");
        u.setName("a@a.a");
        u.setPass("a");
//        
//        ud.insertUser(u);
        List<User> list = ud.getUsers();
        User n = new User();
        for (User a :list) {
            if((a.getName().equals(u.getName()) || a.getEmail().equals(u.getName())) && a.getPass().equals(u.getPass())) n = a;
        }    
        System.out.println(n.getEmail());
        

//        SettingsDAO sd = new SettingsDAO();
//        Settings s = sd.getSettings(u);
//        try{
//            s.setQ_Notice(Time.valueOf("00:07:00"));
//            System.out.println(s.getQ_Notice());
//            u.setSetting(s);
//        }catch(IllegalArgumentException ex){}
//        sd.updateSettings(u);
//        System.out.println(s.getQ_Notice());

//        NoticesDAO nd = new NoticesDAO();
//        Notice n = new Notice();
//        n.setNid(3);
//        n.setId(1);
//        n.setTitle("AA");
//        nd.insertNotice(n);
//        nd.deleteNotice(n);
//        List<Notice> list = nd.getNotices(u);
//        for(Notice a :list){
//            System.out.println("Alert: " + a.getA_Time() + " Title: " + a.getTitle());
//        }
    }
    
}
