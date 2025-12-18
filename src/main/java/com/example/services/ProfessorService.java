package com.example.services;

import java.util.List;

import com.example.entities.Professor;

public interface ProfessorService {
    List<Professor> findAllProfessors();

    Professor findProfessorById(int professorId);

    Professor saveProfessor(Professor professor);

    Professor updateProfessor(int professorId);
    
    void deleteProfessor(Professor professor);
}
