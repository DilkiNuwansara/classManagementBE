package com.dilki.classmanagementbe.DB;

import com.dilki.classmanagementbe.models.Response;
import com.dilki.classmanagementbe.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentData {
    public static ArrayList<Student> getStudentDetails() throws SQLException {
        ConnectionDb c1 = new ConnectionDb();
        Connection con = c1.Connect();
        ArrayList<Student> stList = new ArrayList<>();


        PreparedStatement stDetailsStatement = con.prepareStatement("SELECT * FROM students");
        ResultSet stDetails = stDetailsStatement.executeQuery();
        while (stDetails.next()) {
            int stID = stDetails.getInt("stID");
            String name = stDetails.getString("stName");
            String gender = stDetails.getString("stGender");
            String bday = stDetails.getString("stBDay");
            String stClass = stDetails.getString("stClass");

//            Map<String,Object> stRecord = new HashMap<>();
//            stRecord.put("Id",stID);
//            stRecord.put("Name",name);
//            stRecord.put("Gender",gender);
//            stRecord.put("Bday",bday);
//            stRecord.put("Class",stClass);
//

            Student st = new Student();
            st.setStId(stID);
            st.setStName(name);
            st.setStBday(bday);
            st.setStClass(stClass);
            st.setStGender(gender);


            stList.add(st);

        }
        return stList;
    }

    public static Response setStudentDetails(Student student)  {
        Response res = new Response();
        Boolean status = false;
    try {
        ConnectionDb conn = new ConnectionDb();
        Connection con = conn.Connect();


        PreparedStatement setStatement = con.prepareStatement("insert into students values(?,?,?,?,?)");
        setStatement.setInt(1, student.getStId());
        setStatement.setString(2, student.getStName());
        setStatement.setString(3, student.getStGender());
        setStatement.setString(4, student.getStBday());
        setStatement.setString(5, student.getStClass());

        if ((!setStatement.execute()) && (setStatement.getUpdateCount() > 0)) {
            System.out.println("Inserted count : " + setStatement.getUpdateCount());
            status = true;
        } else {
            status = false;
        }
        if(status){
            res.setStatus("Success");

        }else{
            res.setStatus("Failed");

        }

    }catch (Exception error){
        res.setStatus("Failed");
        res.setReason(error.getMessage().toString());

    }
     return res;
    }

    public static Response getStudentById(int studentId) throws SQLException {
        ConnectionDb condb = new ConnectionDb();
        Connection conn = condb.Connect();
        Response response = new Response();

        PreparedStatement getStudentByIdStmt = conn.prepareStatement("select * from students where stId = ?");
        getStudentByIdStmt.setInt(1, studentId);
        ResultSet studentRecord = getStudentByIdStmt.executeQuery();

//        Map<String,Object> response = new HashMap<>();


        if (studentRecord.next()) {
            Student s1 = new Student(studentRecord.getInt(1), studentRecord.getString(2), studentRecord.getString(3), studentRecord.getString(4), studentRecord.getString(5));
            Response res = new Response("Success");
            res.setResult(s1);
            response = res;
//            response.put("Status","Success");
//            response.put("Result",s1);
        } else {
            Response res = new Response("Failed");
            res.setReason("No data found");
            response = res;
//            response.put("Status","Failed");
//            response.put("Reason","No data found");
        }
        return response;

    }

    public static Response deleteStudentRecord(int studentId) throws SQLException {
        ConnectionDb conectDB = new ConnectionDb();
        Connection conn = conectDB.Connect();
        Response response = new Response();

        PreparedStatement deleteStmt = conn.prepareStatement("delete from students where stID = ?");
        deleteStmt.setInt(1,studentId);
        if(!deleteStmt.execute() && deleteStmt.getUpdateCount()==1){
            Response res = new Response("success");
            response = res;

        }else{
            Response res = new Response("Failed");
            res.setReason("No Record Deleted");
            response = res;

        }
        return response;
    }

    public static Response updateStudentById(Student student) throws SQLException {
        ConnectionDb conectDB = new ConnectionDb();
        Connection conn = conectDB.Connect();
        Response response = new Response();

        PreparedStatement updateStmt = conn.prepareStatement("update students set stName=?,stGender=?,stBDay=?,stClass=? where stID = ?");

        updateStmt.setInt(5,student.getStId());
        updateStmt.setString(1,student.getStName());
        updateStmt.setString(2,student.getStGender());
        updateStmt.setString(3,student.getStBday());
        updateStmt.setString(4,student.getStClass());

        if(!updateStmt.execute() && updateStmt.getUpdateCount()>0){
            Response res = new Response("Success");
            response = res;

        }else{
            Response res = new Response("Failed");
            res.setReason("update failed");
            response = res;
        }
        return response;
    }
}


