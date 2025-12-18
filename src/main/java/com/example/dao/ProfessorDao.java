package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Professor;

@Repository
public interface ProfessorDao extends JpaRepository<Professor, Integer> {

}
