package com.tweedy.sboot.thymeleaf.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_note")
public class Note implements InterfaceGetEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="note_id")
	private int noteId;
	
	@Column(name="empl_id")
	private int emplId;
	
	@Column(name="note_date")
	private Timestamp noteDate;
	
	@Column(name="note")
	private String note;
	
	public Note() {}

	public Note(int noteId, int emplId, Timestamp noteDate, String note) {
	
		this.noteId = noteId;
		this.emplId = emplId;
		this.noteDate = noteDate;
		this.note = note;
	}

	
	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	
	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	
	public Timestamp getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Timestamp noteDate) {
		this.noteDate = noteDate;
	}

	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm");  
		String strDate = dateFormat.format(noteDate); 
		return "Note [noteId=" + noteId + ", emplId=" + emplId + ", noteDate=" + strDate + ", note=" + note + "]";
	}
	
	public NoteTL convertToNoteTL() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(noteDate);  
		
		return new NoteTL(noteId, emplId, strDate , note);
		
	}
	
	public int extractEmployeeId() {
		return emplId;
		
	}
	
}//eoc
