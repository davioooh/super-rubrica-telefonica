package com.davioooh.srt.controllers;

import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping("/new")
    public String contactForm(Model model) {
        model.addAttribute(new ContactForm());
        return "contact-form";
    }

    @PostMapping("/new")
    public String submitNewContact(@Valid @ModelAttribute ContactForm contactForm
            , BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "contact-form";
        }

        ContactDetails details = new ContactDetails();
        details.setFirstName(contactForm.getFirstName());
        details.setLastName(contactForm.getLastName());
        details.setPhone(contactForm.getPhone());
        details.setEmail(contactForm.getEmail());

        model.addAttribute("contact", details);
        return "contact-details";
    }
}
