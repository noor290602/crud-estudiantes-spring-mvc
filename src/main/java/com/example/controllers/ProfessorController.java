package com.example.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.EmailAddress;
import com.example.entities.Faculty;
import com.example.entities.PhoneNumber;
import com.example.entities.Professor;
import com.example.entities.Student;
import com.example.services.FacultyService;
import com.example.services.ProfessorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProfessorController.class);

    private final ProfessorService professorService;
    private final FacultyService facultyService;

    @GetMapping("/list")
    public String listProfessors(Model model) {
        LOGGER.info("Listing professors");

        List<Professor> professors = professorService.findAllProfessors();

        model.addAttribute("professors", professors);

        return "list-professors";
    }

    @GetMapping("/add")
    public String addProfessor(Model model) {
        Professor professor = new Professor();
        model.addAttribute("professor", professor);

        List<Faculty> listFaculties = facultyService.findAllFaculties();
        model.addAttribute("facultyList", listFaculties);

        return "formProfessor";
    }

   
    @PostMapping("/save")
    public String saveProfessor( 
        @ModelAttribute("professor") Professor professor,
        @RequestParam(name = "professorPhoto", required = false) MultipartFile professorImage
    ) {

        // Upload foto si viene en el form
        if (professorImage != null && !professorImage.isEmpty()) {
            Path relativePath = Paths.get("src/main/resources/static/images");
            String absolutePath = relativePath.toFile().getAbsolutePath();
            Path completePath = Paths.get(absolutePath, professorImage.getOriginalFilename());

            try {
                byte[] receivedBytes = professorImage.getBytes();
                Files.write(completePath, receivedBytes);
                professor.setPhoto(professorImage.getOriginalFilename());
            } catch (Exception e) {
                LOGGER.error("Error saving professor image", e);
            }
        }

        // Guardar/actualizar profesor
        professorService.saveProfessor(professor);

        return "redirect:/professors/list";
    }

    @GetMapping("/update/{professorId}")
    public String modifyProfessor(@PathVariable("professorId") int professorId, Model model) {

        Professor professor = professorService.findProfessorById(professorId);

        List<Faculty> facultyList = facultyService.findAllFaculties();
        model.addAttribute("facultyList", facultyList);

        professorService.updateProfessor(professorId);

        model.addAttribute("professor", professor);
        return "formProfessor";
    }

    @GetMapping("/view/{professorId}")
    public String viewProfessor(@PathVariable("professorId") int professorId, Model model) {
        Professor professor = professorService.findProfessorById(professorId);
        model.addAttribute("professor", professor);

        String professorPhoto = professor.getPhoto();
        model.addAttribute("professorPhoto", professorPhoto);

        return "view-professor";
    }

    @GetMapping("/delete/{professorId}")
    public String deleteProfessor(@PathVariable("professorId") int professorId) {

        Professor professorToDelete = professorService.findProfessorById(professorId);

        // Eliminar foto del sistema de ficheros si existe
        if (professorToDelete.getPhoto() != null) {
            Path relativePath = Paths.get("src/main/resources/static/images", professorToDelete.getPhoto());
            try {
                if (Files.exists(relativePath)) {
                    Files.delete(relativePath);
                }
            } catch (Exception e) {
                LOGGER.error("Error deleting professor image", e);
            }
        }


        professorService.deleteProfessor(professorToDelete);

        return "redirect:/professors/list";
    }


}
