package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.PhoneNumber;
import com.example.entities.Student;

@Repository
public interface PhoneNumberDAO extends JpaRepository<PhoneNumber, Integer> {
    boolean existsByStudent(Student student);
    void deleteByStudent(Student student);
    List<PhoneNumber> findByStudent(Student student);
}
