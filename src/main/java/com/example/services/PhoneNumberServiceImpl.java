package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.PhoneNumberDAO;
import com.example.entities.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberDAO phoneNumberDao;

    @Override
    public List<PhoneNumber> findAllPhoneNumbers() {
        return phoneNumberDao.findAll();
    }

    @Override
    public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberDao.save(phoneNumber);
    }

    @Override
    public void deletePhoneNumber(int phoneNumberId) {
        phoneNumberDao.deleteById(phoneNumberId);
    }

    @Override
    public PhoneNumber updatePhoneNumber(int phoneNumberId) {
        return phoneNumberDao.save(phoneNumberDao.findById(phoneNumberId).get());
    }

}