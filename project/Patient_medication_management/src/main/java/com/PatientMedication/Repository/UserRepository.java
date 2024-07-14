package com.PatientMedication.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.PatientMedication.Model.Patients;

public interface UserRepository extends MongoRepository<Patients, String> {

    Patients findByEmailAndPassword(String email, String password);

    Patients findByEmail(String email);    
}
