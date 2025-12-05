package com.example.services;

import java.util.List;

import com.example.entities.Student;

public interface StudentService {

    List<Student> findAllStudents();

    Student findStudentById(int studentId);

    Student saveStudent(Student student);

    Student updateStudent(int studentId);
    
    void deleteStudent(Student student);
}
