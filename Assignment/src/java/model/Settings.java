package model;
import java.sql.*;

public class Settings {
    int id;
    int Q_Notice;
    String QN_period;
    Time A_Time1;
    Time A_Time2;
    Time A_Time3;

    public Settings() {
    }

    public int getQ_Notice() {
        return Q_Notice;
    }

    public void setQ_Notice(int Q_Notice) {
        this.Q_Notice = Q_Notice;
    }

    public String getQN_period() {
        return QN_period;
    }

    public void setQN_period(String QN_period) {
        this.QN_period = QN_period;
    }

    public Settings(int id, int Q_Notice, String QN_period, Time A_Time1, Time A_Time2, Time A_Time3) {
        this.id = id;
        this.Q_Notice = Q_Notice;
        this.QN_period = QN_period;
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
