package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.PhoneNumberDAO;
import com.example.dao.StudentDAO;
import com.example.entities.PhoneNumber;
import com.example.entities.Student;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final StudentDAO studentDAO;

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

    @Override
    public boolean existsByStudent(Student student) {
        return phoneNumberDao.existsByStudent(student);
    }

    @Override
    @Transactional
    public void deleteByStudent(Student student) {
       phoneNumberDao.deleteByStudent(student);
    }

    @Override
    public List<PhoneNumber> findByStudent(Student student) {
        return phoneNumberDao.findByStudent(student);
    }

}