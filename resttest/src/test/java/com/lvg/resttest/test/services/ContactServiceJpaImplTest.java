package com.lvg.resttest.test.services;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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
	
	@DataSet(setUpDataSet="/com/lvg/resttest/test/services/ContactServiceJpaImplTest.xls")
	@Test
	public void testFindAll(){
		List<Contact> contacts = contactService.findAll();
		assertNotNull(contacts);
		assertEquals(2, contacts.size());
	}
	
}
