package com.nicolas.belt.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicolas.belt.models.LoginUser;
import com.nicolas.belt.models.User;
import com.nicolas.belt.services.UserService;
import com.nicolas.belt.validators.UserValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User loginUser) {
        return "index";
    }

    @RequestMapping("/login")
    public String loginForm(@ModelAttribute("loginUser") LoginUser loginUser) {
         System.out.println("sda-0-0-sad-0sda-0sda-0sd-0");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(
            @Valid @ModelAttribute("loginUser") LoginUser loginUser,
            BindingResult result,
            HttpSession session,
            Model model) {
                System.out.println("sda-0-0-sad-0sda-0sda-0sd-0");
        if (result.hasErrors()) {
             System.out.println("sda-0-0-sad-0sda-0sda-0sd-0");
            return "login";
        }

        if (userService.authenticateUser(loginUser)) {
            
            User user = userService.findByEmail(loginUser.getEmail());
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            System.out.println("sda-0-0-sad-0sda-0sda-0sd-0");
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
            Model model) {
        userValidator.validate(user, result);

        if (userService.findByEmail(user.getEmail()) == null) {
            if (result.hasErrors()) {
                return "index";
            } else {
                User u = userService.registerUser(user);
                session.setAttribute("user", u);
                return "redirect:/dashboard";
            }
        } else {
            model.addAttribute("errorRegister", "Email has been already taken");
            return "index";
        }

    }

    @RequestMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		return "dashboard";
	}

    @RequestMapping("/")
    public String dashboard(Principal principal, Model model) {
        String email = principal.getName();
        User u = userService.findByEmail(email);

        model.addAttribute("currentUser", u);
        return "dashboard";

    }

    @RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
