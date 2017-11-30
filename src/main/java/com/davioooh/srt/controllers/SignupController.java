package com.davioooh.srt.controllers;

import com.davioooh.srt.model.SignupForm;
import com.davioooh.srt.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping
    public String signup(Model model) {
        model.addAttribute(new SignupForm());
        return "signup";
    }

    @PostMapping
    public String signupSubmit(@Valid @ModelAttribute SignupForm signupForm
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        signupService.signup(signupForm);

        return "redirect:/";
    }
}
