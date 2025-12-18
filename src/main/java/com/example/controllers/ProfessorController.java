package com.example.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Professor;
import com.example.entities.Student;
import com.example.services.ProfessorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfessorController.class);

    private final ProfessorService professorService;
    //private final FacultyService facultyService;

    @GetMapping("/list")
    public String listProfessors(Model model) {
        LOGGER.info("Listing professors");

        List<Professor> professors = professorService.findAllProfessors();

        model.addAttribute("professors", professors);

        return "list-professors";
    }

    
}
