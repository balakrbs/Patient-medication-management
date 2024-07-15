package com.PatientMedication.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Repository.doctorsRepository;
import com.PatientMedication.exception.ResourceNotFoundException;

@Service
public class DoctorService {
	
	@Autowired
	private doctorsRepository doctorRepo;

	public long getTotalDoct() {
        return doctorRepo.count();
    }
	
	public void saveDoctor(Doctors doctor) {
		doctorRepo.save(doctor);
	    }

	    public List<Doctors> getAllDoctors() {
	        return doctorRepo.findAll();
	    }

		public Object findAll() {
			// TODO Auto-generated method stub
			return null;
		}


		public Doctors getDoctorById(String id) {
		    return doctorRepo.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
		}

}
