package com.tweedy.sboot.thymeleaf.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private int addrId;
	
	//@Column(name = "empl_id")
	//private int emplId;
	
	@Column(name = "address_label")
	private String addressLabel;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zip")
	private String zip;

	@ManyToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "empl_id")
	private EmployeeDev employee;

	@Transient
	private String idTag; // to remove item with javascript
	
	// utility functions
	public boolean isEmpty() {
		if (addrId == 0 && addressLabel == null && address1 == null &&
				address2 == null && city == null && state == null && zip == null	)
			return true;
		else
			return false;
	}

}// eoc
