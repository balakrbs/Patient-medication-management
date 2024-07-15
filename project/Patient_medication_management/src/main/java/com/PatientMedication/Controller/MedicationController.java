package com.PatientMedication.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PatientMedication.Service.patientService;
import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Model.Medication;
import com.PatientMedication.Model.Patients;
import com.PatientMedication.Service.DoctorService;
import com.PatientMedication.Service.MedicationService;
@Controller
public class MedicationController {
	 private final MedicationService medicationService;
	    private final patientService patientService;
	    private final DoctorService doctorService;

	    public MedicationController(MedicationService medicationService, patientService patientService, DoctorService doctorService) {
	        this.medicationService = medicationService;
	        this.patientService = patientService;
	        this.doctorService = doctorService;
	    }

	    @GetMapping("/medications")
	    public String getAllMedications(Model model) {
	        List<Medication> medications = medicationService.getAllMedications();
	        model.addAttribute("medications", medications);
	        return "medication";
	    }

	    @GetMapping("/medications/new")
	    public String showNewMedicationForm(Model model) {
	        model.addAttribute("medication", new Medication());
	        model.addAttribute("patients", patientService.getAllPatients());
	        model.addAttribute("doctors", doctorService.getAllDoctors());
	        return "medication-form";
	    }

	    @PostMapping("/medications")
	    public String createMedication(@RequestParam("patientId") String patientId, @RequestParam("doctorId") String doctorId, @ModelAttribute("medication") Medication medication) {
	        Patients patient = patientService.getPatientById(patientId);
	        Doctors doctor = doctorService.getDoctorById(doctorId);
	        medicationService.createMedication(patient, doctor, medication);
	        return "redirect:/medications";
	    }

	    @GetMapping("/medications/{id}/edit")
	    public String showEditMedicationForm(@PathVariable("id") String id, Model model) {
	        Medication medication = medicationService.getMedicationById(id);
	        if (medication.getPatient() == null) {
	            medication.setPatient(new Patients());
	        }
	        if (medication.getDoctor() == null) {
	            medication.setDoctor(new Doctors());
	        }
	        model.addAttribute("medication", medication);
	        model.addAttribute("patients", patientService.getAllPatients());
	        model.addAttribute("doctors", doctorService.getAllDoctors());
	        return "medication-form";
	    }

	    @PostMapping("/medications/{id}")
	    public String updateMedication(@PathVariable("id") String id, @RequestParam("patientId") String patientId, @RequestParam("doctorId") String doctorId, @ModelAttribute("medication") Medication medication, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "medication-form";
	        }

	        Patients patient = patientService.getPatientById(patientId);
	        Doctors doctor = doctorService.getDoctorById(doctorId);
	        medicationService.updateMedication(id, patient, doctor, medication);
	        return "redirect:/medications";
	    }

	    @GetMapping("/medications/{id}/delete")
	    public String deleteMedication(@PathVariable("id") String id) {
	        medicationService.deleteMedication(id);
	        return "redirect:/medications";
	    }
}