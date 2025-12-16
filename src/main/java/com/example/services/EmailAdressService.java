package com.example.services;

import java.util.List;

import com.example.entities.EmailAddress;
import com.example.entities.Student;

public interface EmailAdressService {
    
    List<EmailAddress> findAllEmailAddresses();

    EmailAddress saveEmailAddress(EmailAddress emailAddress);

    EmailAddress updateEmailAddress(int emailAddressId);
    
    void deleteEmailAddress(int emailAddressId);

    boolean existsByStudent(Student student);

    void deleteByStudent(Student student);

    List<EmailAddress> findByStudent(Student student);
}
