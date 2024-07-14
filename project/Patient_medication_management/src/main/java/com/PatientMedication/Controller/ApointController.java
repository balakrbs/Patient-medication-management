package com.PatientMedication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.PatientMedication.Model.Appointments;
import com.PatientMedication.Service.AppointService;

@Controller
public class ApointController {
	
	
	
	@Autowired
	private AppointService appointService;
	
	@GetMapping("/BookAppointments")
	public String appoint(Model model) {
		 model.addAttribute("appointment", new Appointments());
		return "bookappoint";
	}
	
	@PostMapping("/saveAppoint")
	public String saveAppointment(@ModelAttribute Appointments appointment) {
		 appointService.saveAppointment(appointment);
		return "redirect:/BookAppointments";
	}
	
	@GetMapping("/Appointments")
	public String displayAllAppointments(Model model) {
		model.addAttribute("appointments",appointService.getAllAppointments());
		return "appointments";
	}
}
