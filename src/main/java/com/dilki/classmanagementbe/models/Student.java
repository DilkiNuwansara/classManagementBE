package com.dilki.classmanagementbe.models;

public class Student {
    private int stId;
    private String stName;
    private String stGender;
    private String stBday;
    private String stClass;

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStGender() {
        return stGender;
    }

    public void setStGender(String stGender) {
        this.stGender = stGender;
    }

    public String getStBday() {
        return stBday;
    }

    public void setStBday(String stBday) {
        this.stBday = stBday;
    }

    public String getStClass() {
        return stClass;
    }

    public void setStClass(String stClass) {
        this.stClass = stClass;
    }

    public Student() {
    }

    public Student(int stId, String stName, String stGender, String stBday, String stClass) {
        this.stId = stId;
        this.stName = stName;
        this.stGender = stGender;
        this.stBday = stBday;
        this.stClass = stClass;
    }
}
