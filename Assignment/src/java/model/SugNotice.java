package model;

import java.sql.Date;

public class SugNotice {
    int SID;
    String Title;
    String Descr;
    Date Alert_Date;

    public SugNotice() {
    }

    public SugNotice(int SID, String Title, String Descr, Date Alert_Date) {
        this.SID = SID;
        this.Title = Title;
        this.Descr = Descr;
        this.Alert_Date = Alert_Date;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String Descr) {
        this.Descr = Descr;
    }

    public Date getAlert_Date() {
        return Alert_Date;
    }

    public void setAlert_Date(Date Alert_Date) {
        this.Alert_Date = Alert_Date;
    }
    
}
