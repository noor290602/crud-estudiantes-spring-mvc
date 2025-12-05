package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.FacultyDAO;
import com.example.entities.Faculty;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyDAO facultyDao;

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyDao.findAll();
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyDao.save(faculty);
    }

}
