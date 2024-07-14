package com.PatientMedication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMedication.Model.Appointments;
import com.PatientMedication.Repository.AppointRepository;

@Service
public class AppointService {
    @Autowired
    private AppointRepository appointRepo;

    public long getTotalAppoint() {
        return appointRepo.count();
    }

    public Appointments saveAppointment(Appointments appointment) {
        String dateTimeIdentifier = appointment.getDateTimeIdentifier();
        Optional<Appointments> existingAppointment = appointRepo.findByDateTimeIdentifier(dateTimeIdentifier);
        if (existingAppointment.isPresent()) {
            return existingAppointment.get();
        } else {
            appointment.setDateTimeIdentifier(dateTimeIdentifier);
            return appointRepo.save(appointment);
        }
    }

    public List<Appointments> getAllAppointments() {
        return appointRepo.findAll();
    }

    public Optional<Appointments> getAppointmentById(String id) {
        return appointRepo.findById(id);
    }
}