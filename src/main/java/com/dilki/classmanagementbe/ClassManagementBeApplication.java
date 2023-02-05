package com.dilki.classmanagementbe;

import com.dilki.classmanagementbe.DB.StudentData;
import com.dilki.classmanagementbe.models.Response;
import com.dilki.classmanagementbe.models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
@RestController
public class ClassManagementBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassManagementBeApplication.class, args);
    }

    @GetMapping("/getStudentDetails/")
    public ArrayList<Student> getStudentDetails() throws SQLException {

        return StudentData.getStudentDetails();
    }

    @PostMapping(value = "/setStudentDetails/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response setStudentDetails(@RequestBody Student student) throws SQLException {
//        String stName = student.getStName();
//        int stId = student.getStId();
//        String stGender = student.getStGender();
//        String stClass = student.getStClass();
//        String stBday = student.getStBday();

//        System.out.println(stName);
//        System.out.println(stId);
//        System.out.println(stGender);
//        System.out.println(stClass);
//        System.out.println(stBday);

        return StudentData.setStudentDetails(student);

    }

    @GetMapping(value ="/getStudentById/",produces = MediaType.APPLICATION_JSON_VALUE )
    public Response getStudentById(@RequestParam int studentId) throws SQLException {
        return StudentData.getStudentById(studentId);
    }
    @DeleteMapping (value = "/deleteStudentById/", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Response deleteStudentById(@RequestBody Map<String, Integer> studentId) throws SQLException {
        return StudentData.deleteStudentRecord(studentId.get("studentId"));

    }
    @PatchMapping(value ="/updateStudentById/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateStudentById(@RequestBody Student student) throws SQLException {
        return StudentData.updateStudentById(student);
    }
}
