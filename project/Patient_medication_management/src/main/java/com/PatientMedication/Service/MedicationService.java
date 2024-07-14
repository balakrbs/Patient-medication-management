package com.PatientMedication.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Model.Medication;
import com.PatientMedication.Model.Patients;
import com.PatientMedication.Repository.MedicationRepository;
import com.PatientMedication.Repository.UserRepository;
import com.PatientMedication.Repository.doctorsRepository;
import com.PatientMedication.exception.ResourceNotFoundException;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final UserRepository patientRepository;
    private final doctorsRepository doctorRepository;

    public MedicationService(MedicationRepository medicationRepository, UserRepository patientRepository, doctorsRepository doctorRepository) {
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Medication createMedication(Patients patient, Doctors doctor, Medication medication) {
        medication.setPatient(patient);
        medication.setDoctor(doctor);
        medication.setCreatedAt(LocalDateTime.now());
        medication.setUpdatedAt(LocalDateTime.now());
        return medicationRepository.save(medication);
    }

    public Medication getMedicationById(String id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id: " + id));
    }

    public Medication updateMedication(String id, Patients patient, Doctors doctor, Medication medication) {
        Medication existingMedication = getMedicationById(id);

        existingMedication.setPatient(patient);
        existingMedication.setDoctor(doctor);
        existingMedication.setDescription(medication.getDescription());
        existingMedication.setUpdatedAt(LocalDateTime.now());

        return medicationRepository.save(existingMedication);
    }

    public void deleteMedication(String id) {
        Medication medication = getMedicationById(id);
        medicationRepository.delete(medication);
    }
}