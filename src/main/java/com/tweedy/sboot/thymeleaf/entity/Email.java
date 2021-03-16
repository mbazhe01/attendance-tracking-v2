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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_email")
public class Email implements InterfaceGetEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="email_id")
	private int emailId;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "empl_id")
	private EmployeeDev employee;

	
	@Override
	public int extractEmployeeId() {
		
		return employee.getEmplId();
	}
	
	
}
