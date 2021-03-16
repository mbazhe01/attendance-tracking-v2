package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class PhonesWrapper {
	List<Phone> phones;

	// default constructor
	public PhonesWrapper() {}
	
	// Parameterized constructor
	public PhonesWrapper(List<Phone> phones) {	
			this.phones = phones;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone phone) {
        this.phones.add(phone);
    }
	
	public int getEmployeeId()  {
		int emplId = -1;
		
		if (phones != null && phones.size() > 0) {
			
			for(Phone p : phones) {
				emplId = p.getEmployee().getEmplId();
			}
			
		}
		
		return emplId;
		
	}
}
