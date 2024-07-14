package com.PatientMedication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.PatientMedication.Controller.UserController;
import com.PatientMedication.Model.Appointments;
import com.PatientMedication.Model.Patients;
import com.PatientMedication.Repository.AppointRepository;
import com.PatientMedication.Repository.UserRepository;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AppointRepository appointmentRepository;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        Patients user = new Patients();
        user.setEmail(email);

        Appointments appointment = new Appointments();
        //appointment.setId("1");
        appointment.setName("Test Appointment");
        appointment.setAge(30);
        appointment.setDate("2023-07-01");
        appointment.setTime("10:00 AM");
        appointment.setReason("Routine Checkup");
        List<Appointments> appointments = Collections.singletonList(appointment);

        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);
        when(appointmentRepository.findByEmail(anyString())).thenReturn(appointments);

        // Act
        String result = userController.loginUser(email, password, model);

        // Assert
        assertEquals("dashboard", result);
        when(model.getAttribute("user")).thenReturn(user);
        when(model.getAttribute("appointments")).thenReturn(appointments);
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Arrange
        String email = "invalid@example.com";
        String password = "wrongpassword";

        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        // Act
        String result = userController.loginUser(email, password, model);

        // Assert
        assertEquals("login", result);
        when(model.getAttribute("error")).thenReturn("Invalid email or password");
    }
}
