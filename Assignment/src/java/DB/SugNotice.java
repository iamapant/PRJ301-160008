package DB;

import java.sql.Timestamp;

public class SugNotice {
    int SID;
    String Title;
    String Descr;
    Timestamp Alert_Time;

    public SugNotice() {
    }

    public SugNotice(int SID, String Title, String Descr, Timestamp Alert_Time) {
        this.SID = SID;
        this.Title = Title;
        this.Descr = Descr;
        this.Alert_Time = Alert_Time;
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

    public Timestamp getAlert_Time() {
        return Alert_Time;
    }

    public void setAlert_Time(Timestamp Alert_Time) {
        this.Alert_Time = Alert_Time;
    }
    
}
