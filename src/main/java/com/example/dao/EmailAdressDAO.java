package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.EmailAddress;
import com.example.entities.Student;

@Repository
public interface EmailAdressDAO extends JpaRepository<EmailAddress, Integer> {
        boolean existsByStudent(Student student);
        void deleteByStudent(Student student);
        List<EmailAddress> findByStudent(Student student);
}
