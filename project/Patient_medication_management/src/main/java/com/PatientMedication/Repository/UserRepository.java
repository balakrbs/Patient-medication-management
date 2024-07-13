package com.PatientMedication.Repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.PatientMedication.Model.Patients;

public interface UserRepository extends MongoRepository<Patients, String> {

	Patients findByEmail(String email);

	Patients findByEmailAndPassword(String email, String password);
    

}
