package com.app.burenok.repository;

import com.app.burenok.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Long> {

}
