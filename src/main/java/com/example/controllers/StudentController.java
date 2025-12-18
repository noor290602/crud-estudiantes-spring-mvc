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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final PhoneNumberService phoneNumberService;
    private final EmailAdressService emailAdressService;

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
        model.addAttribute("facultyList", listFaculties);

        return "formStudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student,
            @RequestParam(name = "emailsString", required = false) String emails,
            @RequestParam(name = "phoneNumbersString", required = false) String phoneNumbers,
            @RequestParam(name= "studentPhoto", required = false) MultipartFile studentImage
        ) {

        if (studentImage != null && !studentImage.isEmpty()) {
            Path relativePath = Paths.get("src/main/resources/static/images");
            String absolutePath = relativePath.toFile().getAbsolutePath();
            Path completePath = Paths.get(absolutePath + "/" + studentImage.getOriginalFilename());

            try {
                byte[] recivedByteImage = studentImage.getBytes();
                Files.write(completePath, recivedByteImage);
                student.setPhoto(studentImage.getOriginalFilename());
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Student studentSaved = studentService.saveStudent(student);

        if (phoneNumbers != "" || phoneNumbers != null) {

            List<String> phoneNumbersList = Arrays.stream(phoneNumbers.split(";"))
                    .map(String::trim)
                    .toList();

            // Before creating/modifying phone numbers, you must delete the one associated with that student

            if (phoneNumberService.existsByStudent(student)) {
                phoneNumberService.deleteByStudent(student);
            }

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

            // Before creating/modifying emails, you must delete the one associated with that student

            if (emailAdressService.existsByStudent(student)) {
                emailAdressService.deleteByStudent(student);
            }


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

    @GetMapping("/update/{studentId}")
    public String modifyStudent(@PathVariable("studentId") int studentId, Model model) {

        Student student = studentService.findStudentById(studentId);

        List<Faculty> facultyList = facultyService.findAllFaculties();
        model.addAttribute("facultyList", facultyList);

        /* Email and phone numbers */

        List<PhoneNumber> phoneNumbersList = phoneNumberService.findAllPhoneNumbers().stream()
                .filter(phn -> phn.getStudent().equals(student))
                .toList();

        List<EmailAddress> emailAddressesList = emailAdressService.findAllEmailAddresses().stream()
                .filter(ea -> ea.getStudent().equals(student))
                .toList();

        if (!phoneNumbersList.isEmpty() && !emailAddressesList.isEmpty()) {

            String emails = emailAddressesList.stream()
                    .map(EmailAddress::getEmail)
                    .collect(Collectors.joining(";"));

            String numbers = phoneNumbersList.stream()
                    .map(PhoneNumber::getNumber)
                    .collect(Collectors.joining(";"));

            model.addAttribute("emailsString", emails);
            model.addAttribute("numbersString", numbers);
        }

        studentService.updateStudent(studentId);
        model.addAttribute("student", student);

        return "formStudent";
    }

    @GetMapping("/view/{studentId}")
    public String viewStudent(@PathVariable("studentId") int studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);

        List<PhoneNumber> phoneNumberList = phoneNumberService.findByStudent(student);
        List<String> numbersStringList = phoneNumberList.stream()
            .map(n -> n.getNumber())
            .toList();

        model.addAttribute("numbersStringList", numbersStringList);

        List<EmailAddress> emailAddressesList = emailAdressService.findByStudent(student);
        List<String> emailsStringList = emailAddressesList.stream()
            .map(e -> e.getEmail())
            .toList();

        model.addAttribute("emailsStringList", emailsStringList);

        String studentPhoto = student.getPhoto();
        model.addAttribute("studentPhoto", studentPhoto);

        return "view-student";
    }
    
     @GetMapping("/delete/{studentId}")
    public String eliminarEmpleado(@PathVariable("studentId") int studentId) {

        Student studentToDelete = studentService.findStudentById(studentId);

         if (studentToDelete.getPhoto() != null) {
            Path relativePath = Paths.get("src/main/resources/static/images/" + studentToDelete.getPhoto()); 

            if (Files.exists(relativePath)) {
                try {
                    Files.delete(relativePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        studentService.deleteStudent(studentToDelete);

        return "redirect:/students/list";
    }

}
