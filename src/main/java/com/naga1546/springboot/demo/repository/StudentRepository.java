package com.naga1546.springboot.demo.repository;

import com.naga1546.springboot.demo.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StudentRepository {
    // dummy in-memory data store
    Map<Integer, Student> studentMap;

    public StudentRepository() {
        studentMap = new ConcurrentHashMap<>();
        studentMap.put(123, new Student(123, "Shyam"));
    }

    public List<Student> getStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public Optional<Student> getStudentByName(String name) {
        return studentMap.values().stream().filter(student -> student.getName().equals(name)).findFirst();
    }

    public void createStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    public Student getStudent(Integer studentId) {
        return studentMap.get(studentId);
    }
}
