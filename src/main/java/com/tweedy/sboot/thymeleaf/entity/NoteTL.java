package com.tweedy.sboot.thymeleaf.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NoteTL class for Thymeleaf noteDate field made String so we can format it for
 * the field in Thymeleaf
 * 
 * @author mikeba
 *
 */
@Entity
@Table(name = "tb_note")
public class NoteTL {

	private int noteId;
	private int emplId;
	private String noteDate;
	private String note;

	public NoteTL() {
	}

	public NoteTL(int noteId, int emplId, String noteDate, String note) {

		this.noteId = noteId;
		this.emplId = emplId;
		this.noteDate = noteDate;
		this.note = note;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	@Column(name = "empl_id")
	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	@Column(name = "note_date")
	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "NoteTL [noteId=" + noteId + ", emplId=" + emplId + ", noteDate=" + noteDate + ", note=" + note + "]";
	}

	public Note convertToNote() throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy hh:mm");
		LocalDateTime dateTime = LocalDateTime.parse(noteDate, formatter);

		return new Note(noteId, emplId, Timestamp.valueOf(noteDate), note);

	}
}
