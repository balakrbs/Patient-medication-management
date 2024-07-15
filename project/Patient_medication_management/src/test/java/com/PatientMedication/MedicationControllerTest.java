package com.PatientMedication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.PatientMedication.Controller.MedicationController;
import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Model.Medication;
import com.PatientMedication.Model.Patients;
import com.PatientMedication.Service.DoctorService;
import com.PatientMedication.Service.MedicationService;
import com.PatientMedication.Service.patientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;


@WebMvcTest(MedicationController.class)
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    @MockBean
    private patientService patientService;

    @MockBean
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMedications() throws Exception {
        Medication medication = new Medication("1", new Patients(), new Doctors(), "Test Description", LocalDateTime.now(), LocalDateTime.now());
        when(medicationService.getAllMedications()).thenReturn(Arrays.asList(medication));

        mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("medications"))
                .andExpect(view().name("medication"));

        verify(medicationService).getAllMedications();
    }

    @Test
    public void testShowNewMedicationForm() throws Exception {
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(new Patients()));
        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(new Doctors()));

        mockMvc.perform(get("/medications/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("medication"))
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().attributeExists("doctors"))
                .andExpect(view().name("medication-form"));
    }

    @Test
    public void testCreateMedication() throws Exception {
        Patients patient = new Patients();
        Doctors doctor = new Doctors();

        when(patientService.getPatientById(anyString())).thenReturn(patient);
        when(doctorService.getDoctorById(anyString())).thenReturn(doctor);

        mockMvc.perform(post("/medications")
                .param("patientId", "1")
                .param("doctorId", "1")
                .param("description", "Test Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService).createMedication(any(Patients.class), any(Doctors.class), any(Medication.class));
    }

    @Test
    public void testShowEditMedicationForm() throws Exception {
        Medication medication = new Medication("1", new Patients(), new Doctors(), "Test Description", LocalDateTime.now(), LocalDateTime.now());

        when(medicationService.getMedicationById(anyString())).thenReturn(medication);
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(new Patients()));
        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(new Doctors()));

        mockMvc.perform(get("/medications/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("medication"))
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().attributeExists("doctors"))
                .andExpect(view().name("medication-form"));

        verify(medicationService).getMedicationById(anyString());
    }

    @Test
    public void testUpdateMedication() throws Exception {
        Medication medication = new Medication("1", new Patients(), new Doctors(), "Test Description", LocalDateTime.now(), LocalDateTime.now());

        when(patientService.getPatientById(anyString())).thenReturn(new Patients());
        when(doctorService.getDoctorById(anyString())).thenReturn(new Doctors());

        mockMvc.perform(post("/medications/1")
                .param("patientId", "1")
                .param("doctorId", "1")
                .param("description", "Updated Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService).updateMedication(anyString(), any(Patients.class), any(Doctors.class), any(Medication.class));
    }

    @Test
    public void testDeleteMedication() throws Exception {
        mockMvc.perform(get("/medications/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService).deleteMedication(anyString());
    }
}
