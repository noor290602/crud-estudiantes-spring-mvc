package com.example.services;

import java.util.List;

import com.example.entities.Faculty;

public interface FacultyService {
    
    List<Faculty> findAllFaculties();

    Faculty saveFaculty(Faculty faculty);
}
