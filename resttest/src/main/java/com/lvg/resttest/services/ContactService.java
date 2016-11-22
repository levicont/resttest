package com.lvg.resttest.services;

import java.util.List;

import com.lvg.resttest.models.Contact;

public interface ContactService {
	
	List<Contact> findAll();
	List<Contact> findByFirstName(String firstName);
	Contact findById(Long id);
	Contact save(Contact contact);
	void delete(Contact contact);

}
