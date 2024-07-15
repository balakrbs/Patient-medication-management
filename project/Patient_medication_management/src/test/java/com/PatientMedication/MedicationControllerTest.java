package com.PatientMedication;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
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

    private List<Medication> medicationList;
    private List<Patients> patientList;
    private List<Doctors> doctorList;

    @BeforeEach
    public void setUp() {
        Medication medication1 = new Medication();
        medication1.setId("1");
        Patients patient1 = new Patients();
        patient1.setId("1");
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        Doctors doctor1 = new Doctors();
        doctor1.setId("1");
        doctor1.setFirstname("Dr.");
        doctor1.setLastname("Smith");
        medication1.setPatient(patient1);
        medication1.setDoctor(doctor1);

        Medication medication2 = new Medication();
        medication2.setId("2");
        Patients patient2 = new Patients();
        patient2.setId("2");
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        Doctors doctor2 = new Doctors();
        doctor2.setId("2");
        doctor2.setFirstname("Dr.");
        doctor2.setLastname("Jones");
        medication2.setPatient(patient2);
        medication2.setDoctor(doctor2);

        medicationList = Arrays.asList(medication1, medication2);
        patientList = Arrays.asList(patient1, patient2);
        doctorList = Arrays.asList(doctor1, doctor2);

        when(medicationService.getAllMedications()).thenReturn(medicationList);
        when(patientService.getAllPatients()).thenReturn(patientList);
        when(doctorService.getAllDoctors()).thenReturn(doctorList);
    }

    @Test
    public void testGetAllMedications() throws Exception {
        mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andExpect(view().name("medication"))
                .andExpect(model().attributeExists("medications"))
                .andExpect(model().attribute("medications", medicationList));
    }

   

    @Test
    public void testCreateMedication() throws Exception {
        Patients patient = patientList.get(0);
        Doctors doctor = doctorList.get(0);

        when(patientService.getPatientById(anyString())).thenReturn(patient);
        when(doctorService.getDoctorById(anyString())).thenReturn(doctor);

        mockMvc.perform(post("/medications")
                .param("patientId", patient.getId())
                .param("doctorId", doctor.getId())
                .flashAttr("medication", new Medication()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService, times(1)).createMedication(any(Patients.class), any(Doctors.class), any(Medication.class));
    }

    @Test
    public void testShowEditMedicationForm() throws Exception {
        Medication medication = medicationList.get(0);
        when(medicationService.getMedicationById(anyString())).thenReturn(medication);

        mockMvc.perform(get("/medications/{id}/edit", medication.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("medication-form"))
                .andExpect(model().attributeExists("medication"))
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().attributeExists("doctors"))
                .andExpect(model().attribute("medication", medication));
    }

    @Test
    public void testUpdateMedication() throws Exception {
        Patients patient = patientList.get(0);
        Doctors doctor = doctorList.get(0);

        when(patientService.getPatientById(anyString())).thenReturn(patient);
        when(doctorService.getDoctorById(anyString())).thenReturn(doctor);

        mockMvc.perform(post("/medications/{id}", "1")
                .param("patientId", patient.getId())
                .param("doctorId", doctor.getId())
                .flashAttr("medication", new Medication()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService, times(1)).updateMedication(anyString(), any(Patients.class), any(Doctors.class), any(Medication.class));
    }

    @Test
    public void testDeleteMedication() throws Exception {
        mockMvc.perform(get("/medications/{id}/delete", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medications"));

        verify(medicationService, times(1)).deleteMedication(anyString());
    }
}
