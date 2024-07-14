package com.PatientMedication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.PatientMedication.Controller.ApointController;
import com.PatientMedication.Model.Appointments;
import com.PatientMedication.Service.AppointService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class AppointControllerTest {

    @Mock
    private AppointService appointService;

    @Mock
    private Model model;

    @InjectMocks
    private ApointController apointController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAppointment() {
        // Arrange
        Appointments appointment = new Appointments();
        //appointment.setId("1");
        appointment.setName("John Doe");
        // Set other fields as needed

        when(appointService.saveAppointment(appointment)).thenReturn(appointment); // Mocking the service call

        // Act
        String viewName = apointController.saveAppointment(appointment);

        // Assert
        assertEquals("redirect:/BookAppointments", viewName); // Updated to match the controller's redirect
        verify(appointService).saveAppointment(appointment);
    }

    // Add other test cases as needed
}
