package org.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Employee implements Serializable {
    private int empId;

    private int deptId;

    private String empName;

    private String empNo;

    public Employee() {
    }

    public Employee(int empId, int deptId, String empName, String empNo, Date hireDate, byte[] image, String job, BigDecimal mngId, float salary) {
        this.empId = empId;
        this.deptId = deptId;
        this.empName = empName;
        this.empNo = empNo;
        this.hireDate = hireDate;
        this.image = image;
        this.job = job;
        this.mngId = mngId;
        this.salary = salary;
    }

    private Date hireDate;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigDecimal getMngId() {
        return mngId;
    }

    public void setMngId(BigDecimal mngId) {
        this.mngId = mngId;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    private byte[] image;

    private String job;

    private BigDecimal mngId;

    private float salary;
}
