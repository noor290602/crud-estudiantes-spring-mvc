package com.example.services;

import java.util.List;

import com.example.entities.EmailAddress;

public interface EmailAdressService {
    
    List<EmailAddress> findAllEmailAddresses();

    EmailAddress saveEmailAddress(EmailAddress emailAddress);

    EmailAddress updateEmailAddress(int emailAddressId);
    
    void deleteEmailAddress(int emailAddressId);
}
