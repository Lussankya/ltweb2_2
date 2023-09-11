package org.example.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int deptId;

    public Department(int deptId, String deptName, String deptNo, String location) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptNo = deptNo;
        this.location = location;
    }

    public Department() {
    }

    private String deptName;

    private String deptNo;

    private String location;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
