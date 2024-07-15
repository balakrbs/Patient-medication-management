package com.PatientMedication.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.PatientMedication.Model.Medication;

public interface MedicationRepository extends MongoRepository<Medication, String> {


}