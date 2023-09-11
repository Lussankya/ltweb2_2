package org.example.model;

import java.io.Serializable;

public class SalaryGrade implements Serializable {
    private int grade;

    private float highSalary;

    private float lowSalary;

    public SalaryGrade(int grade, float highSalary, float lowSalary) {
        this.grade = grade;
        this.highSalary = highSalary;
        this.lowSalary = lowSalary;
    }

    public SalaryGrade() {
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getHighSalary() {
        return highSalary;
    }

    public void setHighSalary(float highSalary) {
        this.highSalary = highSalary;
    }

    public float getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(float lowSalary) {
        this.lowSalary = lowSalary;
    }
}
