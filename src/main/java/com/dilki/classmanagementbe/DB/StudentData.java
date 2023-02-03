package com.dilki.classmanagementbe.DB;

import com.dilki.classmanagementbe.models.Response;
import com.dilki.classmanagementbe.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentData {
    public static ArrayList<Student> getStudentDetails() throws SQLException {
        ConnectionDb c1 = new ConnectionDb();
        Connection con = c1.Connect();
        ArrayList<Student> stList = new ArrayList<>();


        PreparedStatement stDetailsStatement = con.prepareStatement("SELECT * FROM students");
        ResultSet stDetails = stDetailsStatement.executeQuery();
        while(stDetails.next()){
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

    public static Boolean setStudentDetails(Student student) throws SQLException {

        ConnectionDb conn = new ConnectionDb();
        Connection con = conn.Connect();
        Boolean status = false;

        PreparedStatement setStatement = con.prepareStatement("insert into students values(?,?,?,?,?)");
        setStatement.setInt(1,student.getStId());
        setStatement.setString(2,student.getStName());
        setStatement.setString(3,student.getStGender());
        setStatement.setString(4,student.getStBday());
        setStatement.setString(5,student.getStClass());

        if((!setStatement.execute())&&(setStatement.getUpdateCount()>0)){
            System.out.println("Inserted count : "+setStatement.getUpdateCount());
            status = true;
        }else{
            status = false;
        }
        return status;

    }

    public static Response getStudentById(int studentId) throws SQLException {
        ConnectionDb condb = new ConnectionDb();
        Connection conn = condb.Connect();
        Response response = new Response();

        PreparedStatement getStudentByIdStmt = conn.prepareStatement("select * from students where stId = ?");
        getStudentByIdStmt.setInt(1,studentId);
        ResultSet studentRecord = getStudentByIdStmt.executeQuery();

//        Map<String,Object> response = new HashMap<>();


        if(studentRecord.next()){
            Student s1 = new Student(studentRecord.getInt(1),studentRecord.getString(2),studentRecord.getString(3),studentRecord.getString(4),studentRecord.getString(5));
            Response res = new Response("Success");
            res.setResult(s1);
            response = res;
//            response.put("Status","Success");
//            response.put("Result",s1);
        }else{
            Response res = new Response("Failed");
            res.setReason("No data found");
            response = res;
//            response.put("Status","Failed");
//            response.put("Reason","No data found");

        }
        return response;

    }

}


