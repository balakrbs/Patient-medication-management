package com.PatientMedication.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PatientMedication.Model.Doctors;

public interface doctorsRepository extends MongoRepository<Doctors, String>{

}
