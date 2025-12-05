package com.example.services;

import java.util.List;

import com.example.entities.PhoneNumber;

public interface PhoneNumberService {

    List<PhoneNumber> findAllPhoneNumbers();

    PhoneNumber savePhoneNumber(PhoneNumber phoneNumber);

    PhoneNumber updatePhoneNumber(int phoneNumberId);
    
    void deletePhoneNumber(int phoneNumberId);
}
