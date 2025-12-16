package com.example.controllers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.EmailAddress;
import com.example.entities.Faculty;
import com.example.entities.PhoneNumber;
import com.example.entities.Student;
import com.example.services.EmailAdressService;
import com.example.services.FacultyService;
import com.example.services.PhoneNumberService;
import com.example.services.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final PhoneNumberService phoneNumberService;
    private final EmailAdressService emailAdressService;


    @GetMapping("")
    public String index() {
        LOGGER.info("Index page");
        return "index";
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        LOGGER.info("Listing students");

        List<Student> students = studentService.findAllStudents();

        model.addAttribute("students", students);

        return "list-students";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);

        List<Faculty> listFaculties = facultyService.findAllFaculties();
        model.addAttribute("listFaculties", listFaculties);

        return "formStudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student,
            @RequestParam(name = "emailsString", required = false) String emails,
            @RequestParam(name = "phoneNumbersString", required = false) String phoneNumbers) {

        //LOGGER.info("                                     PHONE NUMBERS" + phoneNumbers);

        LOGGER.info("Student recived: " + student);

        Student studentSaved = studentService.saveStudent(student);

        if (phoneNumbers != "" || phoneNumbers != null) {

            List<String> phoneNumbersList = Arrays.stream(phoneNumbers.split(";"))
                    .map(String::trim)
                    .toList();

            phoneNumbersList.stream().forEach(phn -> {
                PhoneNumber phoneNumber = PhoneNumber.builder()
                        .number(phn)
                        .student(studentSaved)
                        .build();

                phoneNumberService.savePhoneNumber(phoneNumber);
            });
        }

        if (emails != "" || emails != null) {

            List<String> emailAddressList = Arrays.stream(emails.split(";"))
                    .map(String::trim)
                    .toList();

            emailAddressList.forEach(email -> {
                EmailAddress emailAddress = EmailAddress.builder()
                        .email(email)
                        .student(studentSaved)
                        .build();
                emailAdressService.saveEmailAddress(emailAddress);
            });
        }

        return "redirect:/students/list";
    }

}
