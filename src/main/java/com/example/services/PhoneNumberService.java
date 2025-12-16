package com.example.services;

import java.util.List;

import com.example.entities.EmailAddress;
import com.example.entities.PhoneNumber;
import com.example.entities.Student;

public interface PhoneNumberService {

    List<PhoneNumber> findAllPhoneNumbers();

    PhoneNumber savePhoneNumber(PhoneNumber phoneNumber);

    PhoneNumber updatePhoneNumber(int phoneNumberId);
    
    void deletePhoneNumber(int phoneNumberId);

    boolean existsByStudent(Student student);
    
    void deleteByStudent(Student student);

    List<PhoneNumber> findByStudent(Student student);
}
