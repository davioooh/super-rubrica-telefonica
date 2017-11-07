package com.davioooh.srt.controllers;

import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import com.davioooh.srt.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/contacts")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String contactsList(Model model){
        model.addAttribute("contacts", contactService.getList());
        return "contact-list";
    }

    @GetMapping("/{id}")
    public String contactById(@PathVariable("id") Long id, Model model){
        ContactDetails contact = contactService.getDetailsById(id);

        if(contact == null){
            return "redirect:/";
        }

        model.addAttribute("contact", contact);
        return "contact-details";
    }

    @GetMapping("/new")
    public String contactForm(Model model) {
        model.addAttribute(new ContactForm());
        return "contact-form";
    }

    @PostMapping("/new")
    public String submitNewContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
                                   Model model) {

        if (bindingResult.hasErrors()) {
            return "contact-form";
        }

        model.addAttribute("contact", contactService.save(contactForm));
        return "contact-details";
    }
}
