package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

}
