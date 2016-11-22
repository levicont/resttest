package com.lvg.resttest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lvg.resttest.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findByFirstName(String firstName);
}
