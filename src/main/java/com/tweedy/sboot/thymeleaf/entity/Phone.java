package com.tweedy.sboot.thymeleaf.entity;

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

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id")
	private long phoneId;

	@Column(name = "phone_label")
	private String phoneLabel;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at", updatable = true)
	@UpdateTimestamp
	private LocalDateTime createddAt;

	@Column(name = "updated_at", updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "created_user")
	private String createdUser;

	@Column(name = "updated_user")
	private String updatedUser;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "empl_id")
	private EmployeeDev employee;

	@Transient
	private String fakeId; // to remove item with javascript

	public Phone(String fakeId) {

		this.fakeId = fakeId;
	}

	// utility functions
	public boolean isEmpty() {
		if (phoneId == 0 && phoneLabel == null && phoneNumber == null)
			return true;
		else
			return false;
	}

}// eoc
