package com.davioooh.srt.repositories;

import com.davioooh.srt.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findById(long id);
}
