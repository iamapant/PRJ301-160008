package DB;
import java.sql.*;

public class Notices {
    int nid;
    int id;
    String title;
    String descr;
    Timestamp A_Time;

    public Notices() {
    }

    public Notices(int nid, int id, String title, String descr, Timestamp A_Time) {
        this.nid = nid;
        this.id = id;
        this.title = title;
        this.descr = descr;
        this.A_Time = A_Time;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Timestamp getA_Time() {
        return A_Time;
    }

    public void setA_Time(Timestamp A_Time) {
        this.A_Time = A_Time;
    }

}
