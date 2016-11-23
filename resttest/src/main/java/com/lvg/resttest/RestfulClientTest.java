package com.lvg.resttest;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.lvg.resttest.models.Contact;
import com.lvg.resttest.models.Contacts;

public class RestfulClientTest {
	private static final Logger LOG = Logger.getLogger(RestfulClientTest.class);
	
	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8080/resttest/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/resttest/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/resttest/restful/contact/";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/resttest/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/resttest/restful/contact/{id}";
	
	public static void main(String[] args) {
		 LOG.info("STARTING TEST APPLICATION");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/restful-client-app-context.xml");
		ctx.refresh();
		
		Contact contact;
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		System.out.println("Testing retrieve all contacts: ");
		Contacts contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		printContacts(contacts.getContacts());
		
		System.out.println("Testing retrieve contact with id=1: ");
		contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
		System.out.println(contact);
		
//		System.out.println("Testing updating contact with id=1: ");
//		contact.setFirstName("Richard");
//		restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
//		System.out.println(contact);
//		
		System.out.println("Testing adding contact: ");
		contact = new Contact();
		contact.setFirstName("Richard");
		contact.setLastName("Snouden");
		contact.setBirthDate(DateTime.now());
		contact = restTemplate.postForObject(URL_CREATE_CONTACT, contact, Contact.class);
		System.out.println(contact);		
		
		 LOG.info("END OF TEST APPLICATION");
		
	}
	
	private static void printContacts(List<Contact> contacts){
		for(Contact c: contacts){
			System.out.println(c);
		}
	}

}
