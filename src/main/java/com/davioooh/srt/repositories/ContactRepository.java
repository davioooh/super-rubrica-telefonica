package com.davioooh.srt.repositories;

import com.davioooh.srt.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findById(long id);

    List<Contact> findByUserId(long userId);
}
