package com.dilki.classmanagementbe.models;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private String teacherSubject;
    private String teacherGender;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    public Teacher(int teacherId, String teacherName, String teacherSubject, String teacherGender) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherSubject = teacherSubject;
        this.teacherGender = teacherGender;
    }

    public Teacher() {
    }
}
