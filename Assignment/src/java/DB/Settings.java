package DB;
import java.sql.*;

public class Settings {
    int id;
    Time Q_Notice;
    Time A_Time1;
    Time A_Time2;
    Time A_Time3;

    public Settings() {
    }

    public Settings(int id, Time Q_Notice, Time A_Time1, Time A_Time2, Time A_Time3) {
        this.id = id;
        this.Q_Notice = Q_Notice;
        this.A_Time1 = A_Time1;
        this.A_Time2 = A_Time2;
        this.A_Time3 = A_Time3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getQ_Notice() {
        return Q_Notice;
    }

    public void setQ_Notice(Time Q_Notice) {
        this.Q_Notice = Q_Notice;
    }

    public Time getA_Time1() {
        return A_Time1;
    }

    public void setA_Time1(Time A_Time1) {
        this.A_Time1 = A_Time1;
    }

    public Time getA_Time2() {
        return A_Time2;
    }

    public void setA_Time2(Time A_Time2) {
        this.A_Time2 = A_Time2;
    }

    public Time getA_Time3() {
        return A_Time3;
    }

    public void setA_Time3(Time A_Time3) {
        this.A_Time3 = A_Time3;
    }
    
}
