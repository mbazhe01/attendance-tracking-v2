package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class NotesWrapper {
	List<Note> notes;
	
	public NotesWrapper() {}

	public NotesWrapper(List<Note> notes) {
		this.notes = notes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public void addNote(Note note) {
        this.notes.add(note);
    }
	
	public int getEmployeeId()  {
		int emplId = -1;
		
		if (notes != null && notes.size() > 0) {
			
			for(Note n : notes) {
				emplId = n.getEmplId();
			}
			
		}
		
		return emplId;
		
	}
	
	
}// eoc
