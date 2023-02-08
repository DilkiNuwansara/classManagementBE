package com.dilki.classmanagementbe;

import com.dilki.classmanagementbe.Services.StudentServices;
import com.dilki.classmanagementbe.Services.TeacherServices;
import com.dilki.classmanagementbe.models.Response;
import com.dilki.classmanagementbe.models.Student;
import com.dilki.classmanagementbe.models.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
         return StudentServices.getStudentDetails();
    }

    @PostMapping("/setStudentDetails/")
    public Response setStudentDetails(@RequestBody Student student) throws SQLException {
         return StudentServices.setStudentDetails(student);
    }

    @GetMapping("/getStudentById/")
    public Response getStudentById(@RequestParam int studentId) throws SQLException {
        return StudentServices.getStudentById(studentId);
    }

    @DeleteMapping ("/deleteStudentById/")
    public Response deleteStudentById(@RequestBody Map<String, Integer> studentId) throws SQLException {
        return StudentServices.deleteStudentRecord(studentId.get("studentId"));
    }

    @PatchMapping("/updateStudentById/")
    public Response updateStudentById(@RequestBody Student student) throws SQLException {
        return StudentServices.updateStudentById(student);
    }
    @PostMapping("/setTeacher/")
    public Response setTeacher(@RequestBody Teacher teacher) throws SQLException {
        return TeacherServices.setTeacher(teacher);
    }
    @GetMapping("/getTeachers/")
    public Response getTeachers() throws SQLException {
        return TeacherServices.getTeachers();
    }

    @GetMapping("/getTeacherById/")
    public Response getTeacherById(@RequestParam int teacherId) throws SQLException {
        return TeacherServices.getTeacherById(teacherId);
    }

    @DeleteMapping("/deleteTeacherById/")
    public Response deleteTeacherById(@RequestBody Map<String,Integer> teacherId) throws SQLException {
        return TeacherServices.deleteTeacherById(teacherId.get("teacherId"));
    }

    @PatchMapping("/updateTeacherById/")
    public Response updateTeacherById(@RequestBody Teacher teacher) throws SQLException {
        return TeacherServices.updateTeacherById(teacher);
    }
}
