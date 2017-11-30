package com.davioooh.srt.services;

import com.davioooh.srt.domain.Contact;
import com.davioooh.srt.domain.User;
import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import com.davioooh.srt.model.ContactListItem;
import com.davioooh.srt.repositories.ContactRepository;
import com.davioooh.srt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ContactListItem> getList() {
        return contactRepository.findByUserId(authenticatedUser().getId()).stream().map(c -> {
            ContactListItem item = new ContactListItem();
            item.setId(c.getId());
            item.setName(c.getFirstName() + " " + c.getLastName());
            return item;
        }).collect(Collectors.toList());
    }

    public ContactDetails getDetailsById(long id) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            return null;
        }
        if(contact.getUser().getId()!= authenticatedUser().getId()){
            return null;
        }

        return convertToDetails(contact);
    }

    public ContactDetails save(ContactForm contactForm) {
        Contact contact = fromContactForm(contactForm);
        User user = userRepository.findOne(authenticatedUser().getId());
        contact.setUser(user);
        return convertToDetails(contactRepository.save(contact));
    }

    //

    private SuperRubricaUserDetailsService.SuperRubricaUser authenticatedUser(){
        return (SuperRubricaUserDetailsService.SuperRubricaUser)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
    }

    private Contact fromContactForm(ContactForm contactForm) {
        Contact contact = new Contact();
        contact.setFirstName(contactForm.getFirstName());
        contact.setLastName(contactForm.getLastName());
        contact.setPhone(contactForm.getPhone());
        contact.setEmail(contactForm.getEmail());
        return contact;
    }

    private ContactDetails convertToDetails(Contact contact) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setId(contact.getId());
        contactDetails.setFirstName(contact.getFirstName());
        contactDetails.setLastName(contact.getLastName());
        contactDetails.setPhone(contact.getPhone());
        contactDetails.setEmail(contact.getEmail());
        return contactDetails;
    }
}
