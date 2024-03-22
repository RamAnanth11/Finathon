package com.finathontest.finathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finathontest.finathon.entity.User;
import com.finathontest.finathon.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "LoginPage";  // Make sure this matches your HTML file name
    }
    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        User user = userRepository.findByUsername(username);
        System.out.println(username +" "+password);
        if (user != null && user.getPassword().equals(password)) {
            // Authentication successful, redirect to home page
            return "redirect:/home";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }
    
    @PostMapping("/register")
    public String signUp(@RequestParam String userName, @RequestParam String password) {
    	User user = new User();
    	user.setPassword(password);
    	user.setUsername(userName);
    	userRepository.save(user);
    	return "Saved";
    	
    }
}
