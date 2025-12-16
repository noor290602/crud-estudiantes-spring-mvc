package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EmailAdressDAO;
import com.example.entities.EmailAddress;
import com.example.entities.Student;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailAdressServiceImpl implements EmailAdressService {

    private final EmailAdressDAO emailAdressDao;

    @Override
    public List<EmailAddress> findAllEmailAddresses() {
        return emailAdressDao.findAll();
    }

    @Override
    public EmailAddress saveEmailAddress(EmailAddress emailAddress) {
        return emailAdressDao.save(emailAddress);
    }

    @Override
    public void deleteEmailAddress(int emailAddressId) {
        emailAdressDao.deleteById(emailAddressId);
    }

    @Override
    public EmailAddress updateEmailAddress(int emailAddressId) {
        return emailAdressDao.save(emailAdressDao.findById(emailAddressId).get());
    }

    @Override
    public boolean existsByStudent(Student student) {
        return emailAdressDao.existsByStudent(student);
    }

    @Override
    @Transactional
    public void deleteByStudent(Student student) {
        emailAdressDao.deleteByStudent(student);
    }

    @Override
    public List<EmailAddress> findByStudent(Student student) {
        return emailAdressDao.findByStudent(student);
    }

}
