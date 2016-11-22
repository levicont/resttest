package com.lvg.resttest.models;

import java.io.Serializable;
import java.util.List;

public class Contacts implements Serializable{
		
	private static final long serialVersionUID = 1702042904816444893L;
	
	private List<Contact> contacts;

	
	public Contacts(){}
	
	public Contacts(List<Contact> contacts) {		
		this.contacts = contacts;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
		

}
