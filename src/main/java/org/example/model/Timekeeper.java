package org.example.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Timekeeper implements Serializable {
    private String timekeeper_Id;
    private Date date_Time;
    private BigInteger empId; // Use BigDecimal for empId
    private String in_Out;

    public Timekeeper() {
    }

    public Timekeeper(String timekeeper_Id, Date date_Time, BigInteger empId, String in_Out) {
        this.timekeeper_Id = timekeeper_Id;
        this.date_Time = date_Time;
        this.empId = empId;
        this.in_Out = in_Out;
    }

    public String getTimekeeper_Id() {
        return timekeeper_Id;
    }

    public void setTimekeeper_Id(String timekeeper_Id) {
        this.timekeeper_Id = timekeeper_Id;
    }

    public Date getDate_Time() {
        return date_Time;
    }

    public void setDate_Time(Date date_Time) {
        this.date_Time = date_Time;
    }

    public BigInteger getEmpId() {
        return empId;
    }

    public void setEmpId(BigInteger empId) {
        this.empId = empId;
    }

    public String getIn_Out() {
        return in_Out;
    }

    public void setIn_Out(String in_Out) {
        this.in_Out = in_Out;
    }
}
