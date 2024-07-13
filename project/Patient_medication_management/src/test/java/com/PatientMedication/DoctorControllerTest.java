package com.PatientMedication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.PatientMedication.Controller.DoctorController;
import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Service.DoctorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @Mock
    private Model model;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowAddDoctorForm() {
        // Act
        String viewName = doctorController.showAddDoctorForm(model);

        // Assert
        assertEquals("addDoctors", viewName);

        // Use ArgumentCaptor to capture the actual argument passed
        ArgumentCaptor<Doctors> doctorCaptor = ArgumentCaptor.forClass(Doctors.class);
        verify(model).addAttribute(eq("doctor"), doctorCaptor.capture());
        
        // Verify the captured argument
        Doctors capturedDoctor = doctorCaptor.getValue();
        assertEquals(null, capturedDoctor.getId());
        assertEquals(null, capturedDoctor.getFirstname());
        assertEquals(null, capturedDoctor.getLastname());
        assertEquals(null, capturedDoctor.getSpecialization());
        assertEquals(null, capturedDoctor.getExperience());
        assertEquals(null, capturedDoctor.getDob());
        assertEquals(null, capturedDoctor.getAddress());
        assertEquals(null, capturedDoctor.getEmail());
        assertEquals(null, capturedDoctor.getPhone());
    }

    @Test
    public void testAddDoctor() {
        // Arrange
        Doctors doctor = new Doctors("1", "John", "Doe", "Cardiology", "10 years", "1980-01-01", "123 Street", "john.doe@example.com", "1234567890");

        // Act
        String viewName = doctorController.addDoctor(doctor);

        // Assert
        assertEquals("doctors", viewName);
        verify(doctorService).saveDoctor(doctor);
    }

    @Test
    public void testListDoctors() {
        // Arrange
        Doctors doctor1 = new Doctors("1", "John", "Doe", "Cardiology", "10 years", "1980-01-01", "123 Street", "john.doe@example.com", "1234567890");
        Doctors doctor2 = new Doctors("2", "Jane", "Smith", "Neurology", "5 years", "1985-05-05", "456 Avenue", "jane.smith@example.com", "0987654321");
        List<Doctors> doctors = Arrays.asList(doctor1, doctor2);

        when(doctorService.getAllDoctors()).thenReturn(doctors);

        // Act
        String viewName = doctorController.listDoctors(model);

        // Assert
        assertEquals("doctors", viewName);
        verify(model).addAttribute("doctors", doctors);
        verify(doctorService).getAllDoctors();
    }
}
