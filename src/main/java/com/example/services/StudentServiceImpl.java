package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.StudentDAO;
import com.example.entities.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDao;

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentDao.findById(studentId).get();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(int studentId) {
        return studentDao.save(findStudentById(studentId));
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

}
