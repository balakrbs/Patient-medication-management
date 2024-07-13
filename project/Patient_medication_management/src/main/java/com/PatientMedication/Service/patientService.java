package com.PatientMedication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMedication.Repository.UserRepository;

@Service
public class patientService {
	
	@Autowired
	private UserRepository userRepo;
	
	public long getTotalPatient() {
        return userRepo.count();
    }
	

}
