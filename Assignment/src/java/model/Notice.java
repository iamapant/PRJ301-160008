package model;
import java.sql.*;

public class Notice {
    int nid;
    int id;
    String title;
    String descr;
    Timestamp A_Time;
    int repeatable;
    
    public Notice() {
    }

    public Notice(int nid, int id, String title, String descr, Timestamp A_Time, int repeatable) {
        this.nid = nid;
        this.id = id;
        this.title = title;
        this.descr = descr;
        this.A_Time = A_Time;
        this.repeatable = repeatable;
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

    public int getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(int repeatable) {
        this.repeatable = repeatable;
    }

}
