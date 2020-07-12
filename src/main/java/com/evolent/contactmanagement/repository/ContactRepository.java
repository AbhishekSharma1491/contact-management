package com.evolent.contactmanagement.repository;

import com.evolent.contactmanagement.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByPhoneNumber(String phoneNumber);
    Contact findByEmail(String email);
}
