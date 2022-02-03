package com.naga1546.springboot.demo.service;

import com.naga1546.springboot.demo.model.Student;
import com.naga1546.springboot.demo.repository.StudentRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name).get();
    }

    public void createStudent(Student student) {
        studentRepository.createStudent(student);
    }

    public Student getStudent(Integer studentId) {
        return studentRepository.getStudent(studentId);
    }
}
