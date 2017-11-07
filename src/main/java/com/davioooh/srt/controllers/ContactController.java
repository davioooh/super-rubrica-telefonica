package com.davioooh.srt.controllers;

import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import com.davioooh.srt.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String contactsList(Model model) {
        model.addAttribute("contacts", contactService.getList());
        return "contact-list";
    }

    @GetMapping("/{id}")
    public String contactById(@PathVariable("id") Long id, Model model) {
        ContactDetails contact = contactService.getDetailsById(id);

        if (contact == null) {
            return "redirect:/";
        }

        Object newContactFlag = model.asMap().get("newContact");
        if (newContactFlag != null && (boolean) newContactFlag) {
            model.addAttribute("newContactFlag", true);
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
    public String submitNewContact(@Valid @ModelAttribute ContactForm contactForm
            , BindingResult bindingResult
            , RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "contact-form";
        }

        ContactDetails cnt = contactService.save(contactForm);
        attributes.addFlashAttribute("newContact", true);
        return "redirect:/contacts/" + cnt.getId();
    }
}
