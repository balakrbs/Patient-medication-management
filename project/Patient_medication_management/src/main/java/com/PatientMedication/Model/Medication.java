package com.PatientMedication.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medications")
public class Medication {
	@Id
    private String id;
    private Patients patient;
    private Doctors doctor;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	public Doctors getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Medication(String id, Patients patient, Doctors doctor, String description, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Medication() {
		// TODO Auto-generated constructor stub
	}

    // Getters, setters, and constructors
    
    
}