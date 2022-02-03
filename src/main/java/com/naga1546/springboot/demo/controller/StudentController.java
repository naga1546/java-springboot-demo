package com.naga1546.springboot.demo.controller;

import com.naga1546.springboot.demo.model.Student;
import com.naga1546.springboot.demo.service.StudentService;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<Student>> getStudents(@QueryParam("name") String name) {
        try {
            if (StringUtils.isEmpty(name)) {
                return ResponseEntity.ok(studentService.getStudents());
            } else {
                return ResponseEntity.ok(Arrays.asList(studentService.getStudentByName(name)));
            }
        } catch (Exception e) {
            log.error("Error while processing", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "/", produces = "application/json")
    public Response createStudent(Student student) {
        try {
            studentService.createStudent(student);
            return Response.created(URI.create("/students/" + student.getId())).build();
        } catch (Exception e) {
            log.error("Error while processing", e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }

    @GetMapping(path = "/{studentId}", produces = "application/json")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Integer studentId) {
        try {
            return ResponseEntity.ok(studentService.getStudent(studentId));
        } catch (Exception e) {
            log.error("Error while processing", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
