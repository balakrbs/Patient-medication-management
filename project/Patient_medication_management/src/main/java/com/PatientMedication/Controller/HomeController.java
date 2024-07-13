package com.PatientMedication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.PatientMedication.Model.Patients;
import com.PatientMedication.Service.AppointService;
import com.PatientMedication.Service.DoctorService;
import com.PatientMedication.Service.patientService;
import com.PatientMedication.Utils.LoggedInUserHolder;


@Controller
public class HomeController {
	@Autowired
	private patientService patService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private DoctorService doctService;

    @GetMapping("/")
    public String homePage(Model model) {
    	model.addAttribute("totalP", patService.getTotalPatient());
		model.addAttribute("totalA", appointService.getTotalAppoint());
		model.addAttribute("totalD", doctService.getTotalDoct());
    	Patients loggedInUser = LoggedInUserHolder.getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "home";
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new Patients());
        return "register";
    }
    
    @GetMapping("/appointments")
    public String appointments() {
        return "appointments";
    }
    
    @GetMapping("/bookappoint")
    public String bookappoint() {
        return "bookappoint";
    }
    
    @GetMapping("/doctorsRepository")
    public String doctors() {
        return "doctorsRepository";
    }
}

