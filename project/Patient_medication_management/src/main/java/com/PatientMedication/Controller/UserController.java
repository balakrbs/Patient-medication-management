package com.PatientMedication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.PatientMedication.Model.Appointments;
import com.PatientMedication.Model.Patients;
import com.PatientMedication.Repository.AppointRepository;
import com.PatientMedication.Repository.UserRepository;
import com.PatientMedication.Service.AppointService;
import com.PatientMedication.Utils.LoggedInUserHolder;



@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AppointRepository appointRepo;

    

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Patients user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
    	Patients user = userRepository.findByEmailAndPassword(email,password);
        if (user != null) {
            LoggedInUserHolder.setLoggedInUser(user);
            model.addAttribute("user",user);
            List<Appointments> appointments = appointRepo.findByEmail(email);
            model.addAttribute("appointments", appointments);
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
    
    @PostMapping("/logout")
    public String logout() {
        LoggedInUserHolder.setLoggedInUser(null);
        return "redirect:/";
    }
}
