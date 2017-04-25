package com.todoSpring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todoSpring.model.LoginForm;
import com.todoSpring.model.RegistrationForm;
import com.todoSpring.service.Impl.UserServiceImpl;

@Controller
@RequestMapping("user")
@SessionAttributes(value = "user")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        LOGGER.info("--- showRegistrationForm() invoked ---");
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registrationform";
    }    

    @RequestMapping(value = "/doregistration", method = RequestMethod.POST)
    public String processRegistrationForm(
            @ModelAttribute(value = "registrationForm") RegistrationForm registrationForm, Model model) {
        userService.save(registrationForm);
        return "redirect:/user/login.htm";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "loginform";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "loginForm") LoginForm loginForm, Model model) {
        boolean valid = (loginForm.getUserName() != null) && loginForm.getUserName().matches("[A-Za-z0-9_]+");

        if (valid == true && userService.checkUser(loginForm.getUserName(), loginForm.getPassword())) {
            model.addAttribute("message", loginForm);
            model.addAttribute("user", loginForm);
            return "loginsuccess";
        } else {
            model.addAttribute("loginForm", new LoginForm());
            return "loginform";
        }
    }

}
