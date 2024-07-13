package com.PatientMedication.Controller;


import com.PatientMedication.Model.Doctors;
import com.PatientMedication.Service.DoctorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

	 @Autowired
	    private DoctorService doctorService;

	    @GetMapping("/add-doctor")
	    public String showAddDoctorForm(Model model) {
	        model.addAttribute("doctor", new Doctors());
	        return "addDoctors";
	    }

	    @PostMapping("/add-doctor")
	    public String addDoctor(@ModelAttribute Doctors doctor) {
	        doctorService.saveDoctor(doctor);
	        return "doctors";
	    }

	    @GetMapping("/doctors")
	    public String listDoctors(Model model) {
	        List<Doctors> doctors = doctorService.getAllDoctors();
	        model.addAttribute("doctors", doctors);
	        return "doctors";
	    }
}
