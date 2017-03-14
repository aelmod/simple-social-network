package com.github.aelmod.simple_social_network.controller;

import com.github.aelmod.simple_social_network.model.User;
import com.github.aelmod.simple_social_network.repository.TestRepository;
import com.github.aelmod.simple_social_network.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {
    private final TestRepository testRepository;

    @Autowired
    public MainController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @RequestMapping("/login")
    public String mainPage(Model model) {
        model.addAttribute("user", testRepository.getUserById(1));
        return "main";
    }

    @RequestMapping(method = RequestMethod.GET, path = "register")
    public String registerUser(Model model) {
        model.addAttribute("countries", testRepository.getCountry());
        model.addAttribute("cities", testRepository.getCities());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public String registerUser(User user) {
        testRepository.saveUser(user);
        return "main";
    }
    @RequestMapping("user")
    public @ResponseBody String getUser(@CurrentUser Authentication authentication) {
        User user = (User) authentication;
        return user.getName();
    }
}
