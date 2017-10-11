package com.davioooh.srt.controllers;

import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/contacts")
public class ContactController {

	@GetMapping("/new")
	public String contactForm(Model model) {
		model.addAttribute(new ContactForm());
		return "contact-form";
	}

	@PostMapping("/new")
	public String submitNewContact(@ModelAttribute ContactForm contactForm, Model model) {
		ContactDetails det = new ContactDetails();
		det.setFirstName(contactForm.getFirstName());
		det.setLastName(contactForm.getLastName());
		det.setPhone(contactForm.getPhone());

		model.addAttribute("contact", det);
		return "contact-details";
	}
}
