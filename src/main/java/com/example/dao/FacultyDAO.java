package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Faculty;

@Repository
public interface FacultyDAO extends JpaRepository<Faculty, Integer> {

}
