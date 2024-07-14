package com.PatientMedication.Service;

import com.PatientMedication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class patientService {

    @Autowired
    private UserRepository userRepo;

    public long getTotalPat() {
        return userRepo.count();
    }
}
