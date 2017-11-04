package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by Eduardo on 01/11/17.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("loginCommand", new User());

        return "loginForm";
    }

    @RequestMapping("/logout-success")
    public String yourLoggedOut(){
        return "logout-success";
    }

    public String doLogin(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "loginForm";
        }

        return "redirect:index";
    }
}
