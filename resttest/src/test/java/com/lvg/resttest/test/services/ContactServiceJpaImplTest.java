package com.lvg.resttest.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lvg.resttest.annotations.DataSet;
import com.lvg.resttest.models.Contact;
import com.lvg.resttest.services.ContactService;
import com.lvg.resttest.test.config.ServiceTestConfig;
import com.lvg.resttest.test.listeners.ServiceTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@TestExecutionListeners(listeners = {ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class ContactServiceJpaImplTest extends
	AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	ContactService contactService;
	
	@PersistenceContext
	EntityManager em;
	
	@DataSet(setUpDataSet="/com/lvg/resttest/test/services/ContactServiceJpaImplTest.xls")
	@Test
	public void testFindAll(){
		List<Contact> contacts = contactService.findAll();
		assertNotNull(contacts);
		assertEquals(2, contacts.size());
	}
	
	@DataSet(setUpDataSet="/com/lvg/resttest/test/services/ContactServiceJpaImplTest.xls")
	@Test
	public void testFindByFirstName_1()throws Exception{
		List<Contact> contacts = contactService.findByFirstName("Tomas");
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
	}
	
	@DataSet(setUpDataSet="/com/lvg/resttest/test/services/ContactServiceJpaImplTest.xls")
	@Test
	public void testFindByFirstName_2()throws Exception{
		List<Contact> contacts = contactService.findByFirstName("Eric");
		assertEquals(0, contacts.size());
		assertNotNull(contacts);
		
	}
	
	@Test
	public void testAddContact()throws Exception{
		deleteFromTables("CONTACT");
		Contact contact = new Contact();
		contact.setFirstName("Jack");
		contact.setLastName("Daniels");
		contactService.save(contact);
		em.flush();
		List<Contact> contacts = contactService.findAll();
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void testAddContactWithJSR349Error(){
		deleteFromTables("CONTACT");
		Contact contact = new Contact();
		contactService.save(contact);
		em.flush();
		List<Contact> contacts = contactService.findAll();		
		assertEquals(0, contacts.size());
	}
	
	
	
}
