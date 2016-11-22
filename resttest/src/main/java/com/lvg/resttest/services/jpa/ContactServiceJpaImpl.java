package com.lvg.resttest.services.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lvg.resttest.models.Contact;
import com.lvg.resttest.repository.ContactRepository;
import com.lvg.resttest.services.ContactService;


@Service("contactService")
@Repository
@Transactional
public class ContactServiceJpaImpl implements ContactService
{
	private static final Logger LOG = Logger.getLogger(ContactServiceJpaImpl.class);
	
	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		
		return Lists.newArrayList(contactRepository.findAll());
	}

	@Transactional(readOnly = true)
	public List<Contact> findByFirstName(String firstName) {
		return Lists.newArrayList(contactRepository.findByFirstName(firstName));
	}

	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		
		return contactRepository.findOne(id);
	}

	public Contact save(Contact contact) {
		
		return contactRepository.save(contact);
	}

	public void delete(Contact contact) {
		contactRepository.delete(contact);
		LOG.info("Contact deleted with id: "+ contact.getId());
		
	}
	
	
	

}
