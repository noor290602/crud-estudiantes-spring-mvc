package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.EmailAddress;
import com.example.entities.Faculty;
import com.example.entities.PhoneNumber;
import com.example.entities.Student;
import com.example.model.Gender;
import com.example.services.EmailAdressService;
import com.example.services.FacultyService;
import com.example.services.PhoneNumberService;
import com.example.services.StudentService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesSpringMvcApplication implements CommandLineRunner {

	private final StudentService studentService;
	private final FacultyService facultyService;
	private final EmailAdressService emailAdressService;
	private final PhoneNumberService phoneNumberService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/* FACULTIES */
		Faculty f1 = Faculty.builder().name("Engineering").build();
		Faculty f2 = Faculty.builder().name("Arts").build();

		facultyService.saveFaculty(f1);
		facultyService.saveFaculty(f2);

		/* STUDENTS */
		Student s1 = Student.builder()
			.name("Noor")
			.firstLastName("Aloune")
			.secondLastName("Sagouma")
			.facultyMatriculationDate(LocalDate.of(2002, Month.JUNE, 29))
			.gender(Gender.FEMALE)
			.faculty(f1)
			.build();

		Student s2 = Student.builder()
			.name("Pepe")
			.firstLastName("Perez")
			.secondLastName("García")
			.facultyMatriculationDate(LocalDate.of(2001, Month.OCTOBER, 21))
			.gender(Gender.MALE)
			.faculty(f1)
			.build();

		Student s3 = Student.builder()
			.name("Clara")
			.firstLastName("García")
			.secondLastName("Moreno")
			.facultyMatriculationDate(LocalDate.of(1990, Month.JANUARY, 12))
			.gender(Gender.FEMALE)
			.faculty(f2)
			.build();

		Student s4 = Student.builder()
			.name("Carlos")
			.firstLastName("Martínez")
			.secondLastName("Carrasco")
			.facultyMatriculationDate(LocalDate.of(1994, Month.JULY, 15))
			.gender(Gender.MALE)
			.faculty(f2)
			.build();

		studentService.saveStudent(s1);
		studentService.saveStudent(s2);
		studentService.saveStudent(s3);
		studentService.saveStudent(s4);

		/* EMAIL ADRESSES */
		EmailAddress ea1 = EmailAddress.builder().email("nalosag@gmail.com").student(s1).build();
		EmailAddress ea2 = EmailAddress.builder().email("nalosag2@gmail.com").student(s1).build();
		EmailAddress ea3 = EmailAddress.builder().email("pepega@gmail.com").student(s2).build();
		EmailAddress ea4 = EmailAddress.builder().email("pepega2@gmail.com").student(s2).build();
		EmailAddress ea5 = EmailAddress.builder().email("cgarmo@gmail.com").student(s3).build();
		EmailAddress ea6 = EmailAddress.builder().email("cgarmo2@gmail.com").student(s3).build();
		EmailAddress ea7 = EmailAddress.builder().email("camaca@gmail.com").student(s4).build();
		EmailAddress ea8 = EmailAddress.builder().email("cmaca2@gmail.com").student(s4).build();

		emailAdressService.saveEmailAddress(ea1);
		emailAdressService.saveEmailAddress(ea2);
		emailAdressService.saveEmailAddress(ea3);
		emailAdressService.saveEmailAddress(ea4);
		emailAdressService.saveEmailAddress(ea5);
		emailAdressService.saveEmailAddress(ea6);
		emailAdressService.saveEmailAddress(ea7);
		emailAdressService.saveEmailAddress(ea8);
	
		/* PHONE NUMBERS */
		PhoneNumber p1 = PhoneNumber.builder().number("+34123456789").student(s1).build();
		PhoneNumber p2 = PhoneNumber.builder().number("+34987654321").student(s1).build();
		PhoneNumber p3 = PhoneNumber.builder().number("+34111222333").student(s2).build();
		PhoneNumber p4 = PhoneNumber.builder().number("+34999888777").student(s2).build();
		PhoneNumber p5 = PhoneNumber.builder().number("+34222333444").student(s3).build();
		PhoneNumber p6 = PhoneNumber.builder().number("+34988777666").student(s3).build();
		PhoneNumber p7 = PhoneNumber.builder().number("+34333444555").student(s4).build();
		PhoneNumber p8 = PhoneNumber.builder().number("+34977666555").student(s4).build();

		phoneNumberService.savePhoneNumber(p1);
		phoneNumberService.savePhoneNumber(p2);
		phoneNumberService.savePhoneNumber(p3);
		phoneNumberService.savePhoneNumber(p4);
		phoneNumberService.savePhoneNumber(p5);
		phoneNumberService.savePhoneNumber(p6);
		phoneNumberService.savePhoneNumber(p7);
		phoneNumberService.savePhoneNumber(p8);
		
	}

}
