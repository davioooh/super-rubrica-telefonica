package com.davioooh.srt.services;

import com.davioooh.srt.domain.Contact;
import com.davioooh.srt.model.ContactDetails;
import com.davioooh.srt.model.ContactForm;
import com.davioooh.srt.model.ContactListItem;
import com.davioooh.srt.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<ContactListItem> getList() {
        return contactRepository.findAll().stream().map(c -> {
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

        return convertToDetails(contact);
    }

    public ContactDetails save(ContactForm contactForm) {
        Contact contact = fromContactForm(contactForm);
        return convertToDetails(contactRepository.save(contact));
    }

    //

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
