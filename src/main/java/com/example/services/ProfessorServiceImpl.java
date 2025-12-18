package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.ProfessorDao;
import com.example.entities.Professor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorDao professorDao;

    @Override
    public List<Professor> findAllProfessors() {
        return professorDao.findAll();
    }

    @Override
    public Professor findProfessorById(int professorId) {
        return professorDao.findById(professorId).get();
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorDao.save(professor);
    }

    @Override
    public Professor updateProfessor(int professorId) {
        return professorDao.save(findProfessorById(professorId));
    }

    @Override
    public void deleteProfessor(Professor professor) {
        professorDao.delete(professor);
    }

}
