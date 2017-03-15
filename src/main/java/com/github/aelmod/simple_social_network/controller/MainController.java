package com.github.aelmod.simple_social_network.controller;

import com.github.aelmod.simple_social_network.model.User;
import com.github.aelmod.simple_social_network.repository.TestRepository;
import com.github.aelmod.simple_social_network.security.CurrentUser;
import com.github.aelmod.simple_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private final TestRepository testRepository;
    private final UserService userService;

    @Autowired
    public MainController(TestRepository testRepository, UserService userService) {
        this.testRepository = testRepository;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String mainPage(Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, path = "register")
    public String registerUser(Model model) {
        model.addAttribute("countries", testRepository.getCountry());
        model.addAttribute("cities", testRepository.getCities());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public String registerUser(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/")
    public
    @ResponseBody
    String getUser(@CurrentUser User user) {
        return user.toString();
    }
}
