package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class NotesWrapperTL {
	List<NoteTL> notes;
	
	public NotesWrapperTL() {}

	public NotesWrapperTL(List<NoteTL> notes) {
		super();
		this.notes = notes;
	}

	public List<NoteTL> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteTL> notes) {
		this.notes = notes;
	}
	
	public void addNote(NoteTL note) {
        this.notes.add(note);
    }
	
	public int getEmployeeId()  {
		int emplId = -1;
		
		if (notes != null && notes.size() > 0) {
			
			for(NoteTL n : notes) {
				emplId = n.getEmplId();
			}
			
		}
		
		return emplId;
		
	}
	
}
