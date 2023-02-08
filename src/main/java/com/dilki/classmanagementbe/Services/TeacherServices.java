package com.dilki.classmanagementbe.Services;

import com.dilki.classmanagementbe.DB.ConnectionDb;
import com.dilki.classmanagementbe.models.Response;
import com.dilki.classmanagementbe.models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherServices {

    public static Response setTeacher(Teacher teacher) throws SQLException {

        ConnectionDb connectDb = new ConnectionDb();
        Connection con = connectDb.Connect();
        Response res = new Response();

        PreparedStatement setTeacherStmt = con.prepareStatement("insert into teachers values(?,?,?,?)");
        setTeacherStmt.setInt(1,teacher.getTeacherId());
        setTeacherStmt.setString(2,teacher.getTeacherName());
        setTeacherStmt.setString(3,teacher.getTeacherSubject());
        setTeacherStmt.setString(4,teacher.getTeacherGender());

        if(!setTeacherStmt.execute() && setTeacherStmt.getUpdateCount()>0){
            res.setStatus("Success");
        }else{
            res.setStatus("Failed");
        }
        return res;
    }

    public static Response getTeachers() throws SQLException {
        ConnectionDb connectDb = new ConnectionDb();
        Connection con = connectDb.Connect();
        Response response = new Response();
        ArrayList<Teacher> teachers = new ArrayList<>();

        PreparedStatement getTeachersStmt = con.prepareStatement("select * from teachers");
        ResultSet teachersRecords = getTeachersStmt.executeQuery();

        if(teachersRecords.next()){
            Teacher t1 = new Teacher(teachersRecords.getInt(1), teachersRecords.getString(2), teachersRecords.getString(3), teachersRecords.getString(4));
            teachers.add(t1);
            while (teachersRecords.next()) {
                Teacher t2 = new Teacher(teachersRecords.getInt(1), teachersRecords.getString(2), teachersRecords.getString(3), teachersRecords.getString(4));
                teachers.add(t2);
            }
            response.setStatus("Success");
            response.setResult(teachers);
        }else{
            response.setStatus("Failed");
            response.setReason("Cannot find Teachers");
        }

        return response;
    }

    public static Response getTeacherById(int teacherId) throws SQLException {
        ConnectionDb connectDb = new ConnectionDb();
        Connection con = connectDb.Connect();
        Response res = new Response();

        PreparedStatement getTeacherStmt = con.prepareStatement("select * from teachers where teacherId=?");
        getTeacherStmt.setInt(1,teacherId);
        ResultSet teacherResultSet = getTeacherStmt.executeQuery();

        if(teacherResultSet.next()){
            Teacher t1 = new Teacher(teacherResultSet.getInt(1),teacherResultSet.getString(2),teacherResultSet.getString(3),teacherResultSet.getString(4));
            res.setStatus("Success");
            res.setResult(t1);
        }else{
            res.setStatus("Failed");
            res.setReason("Incorrect Teacher ID");
        }

    return res;
    }

    public static Response deleteTeacherById(int teacherId) throws SQLException {
        ConnectionDb connectDb = new ConnectionDb();
        Connection con = connectDb.Connect();
        Response res = new Response();

        PreparedStatement dltTeacherStmt = con.prepareStatement("delete from teachers where teacherId=?");
        dltTeacherStmt.setInt(1,teacherId);

        if(!dltTeacherStmt.execute() && dltTeacherStmt.getUpdateCount()>0){
            res.setStatus("success");
        }else{
            res.setStatus("Failed");
        }
        return res;
    }

    public static Response updateTeacherById(Teacher teacher) throws SQLException {
        ConnectionDb connectDb = new ConnectionDb();
        Connection con = connectDb.Connect();
        Response res = new Response();

        PreparedStatement updateTeacherStmt = con.prepareStatement("update teachers set teacherName=?,teacherSubject=?,teacherGender=? where teacherId=?");
        updateTeacherStmt.setInt(4,teacher.getTeacherId());
        updateTeacherStmt.setString(1,teacher.getTeacherName());
        updateTeacherStmt.setString(2,teacher.getTeacherSubject());
        updateTeacherStmt.setString(3,teacher.getTeacherGender());

        if(!updateTeacherStmt.execute() && updateTeacherStmt.getUpdateCount()>0){
            res.setStatus("success");
        }else{
            res.setStatus("Failed");
            res.setReason(String.valueOf(updateTeacherStmt.getUpdateCount()));
        }
        return res;

    }
}
