package com.tweedy.sboot.thymeleaf.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_note")
public class NoteDev {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private int noteId;

	@Column(name = "note_date")
	@CreationTimestamp
	private LocalDateTime noteDate;

	@Column(name = "note")
	private String note;

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at", updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "created_user")
	private String createdUser;

	@Column(name = "updated_user")
	private String updatedUser;

	@Transient
	private String idTag; // to remove item with javascript

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "empl_id")
	private EmployeeDev employee;

	// utility functions
	public boolean isEmpty() {
		if (noteId == 0 && (note == null ||note=="" ))
			return true;
		else
			return false;
	}// eof
}
