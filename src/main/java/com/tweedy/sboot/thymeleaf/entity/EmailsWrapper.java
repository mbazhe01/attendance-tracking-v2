package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class EmailsWrapper {
	List<Email> emails;
	
	public EmailsWrapper() {}
	
	public EmailsWrapper(List<Email> emails) {
		this.emails = emails;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	
	public void addEmail(Email email) {
        this.emails.add(email);
    }
	
	public int getEmployeeId()  {
		int emplId = -1;
		
		if (emails != null && emails.size() > 0) {
			
			for(Email e : emails) {
				emplId = e.getEmployee().getEmplId();
			}
			
		}
		
		return emplId;
		
	}
	
}//eoc
