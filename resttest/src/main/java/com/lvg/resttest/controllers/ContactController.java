package com.lvg.resttest.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvg.resttest.models.Contact;
import com.lvg.resttest.models.Contacts;
import com.lvg.resttest.services.ContactService;

@Controller
@RequestMapping("/contact")
public class ContactController {
	private static final Logger LOG = Logger.getLogger(ContactController.class);
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value="/listdata", method=RequestMethod.GET)
	@ResponseBody
	public Contacts listData(){
		return new Contacts(contactService.findAll());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Contact findById(@PathVariable Long id){
		return contactService.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Contact create(@RequestBody Contact contact){
		LOG.info("Creating contact: " + contact);
		contactService.save(contact);
		LOG.info("Contact created successfully with info: " + contact);
		return contact;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Contact update(@RequestBody Contact contact, @PathVariable Long id){
		LOG.info("Updating contact: " + contact);
		contactService.save(contact);
		LOG.info("Contact updated successfully with info: " + contact);
		return contact;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestBody Contact contact, @PathVariable Long id){
		LOG.info("Deleting contact: " + contact);
		contactService.delete(contact);
		LOG.info("Contact deleted successfully");		
	}
	
	

}
