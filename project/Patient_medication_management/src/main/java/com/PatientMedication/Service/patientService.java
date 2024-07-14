package com.PatientMedication.Service;

import com.PatientMedication.Model.Patients;
import com.PatientMedication.Repository.UserRepository;
import com.PatientMedication.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class patientService {

    @Autowired
    private UserRepository userRepo;

    public long getTotalPat() {
        return userRepo.count();
    }
    public List<Patients> getAllPatients() {
        return userRepo.findAll();
    }
	
	public Patients getPatientById(String id) {
	    return userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
	}

}
