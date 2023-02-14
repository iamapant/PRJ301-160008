package DB;

import java.sql.Date;

public class SugNotice {
    int SID;
    String title;
    String descr;
    Date A_Date;

    public SugNotice() {
    }

    public SugNotice(int SID, String title, String descr, Date A_Date) {
        this.SID = SID;
        this.title = title;
        this.descr = descr;
        this.A_Date = A_Date;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
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

    public Date getA_Date() {
        return A_Date;
    }

    public void setA_Date(Date A_Date) {
        this.A_Date = A_Date;
    }
    
}
