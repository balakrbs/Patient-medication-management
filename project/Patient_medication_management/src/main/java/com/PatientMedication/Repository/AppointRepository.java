package com.PatientMedication.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PatientMedication.Model.Appointments;

public interface AppointRepository extends MongoRepository<Appointments, String> {

	List<Appointments> findByEmail(String email);

}
