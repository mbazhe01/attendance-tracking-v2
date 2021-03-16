package com.tweedy.sboot.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_address_label")
public class AddressLabel {
	
	private String label;
	
	public AddressLabel() {}
	
	public AddressLabel(String label) {
		this.label = label;
	}

	@Id
	@Column(name="label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "AddressLabel [label=" + label + "]";
	}
	
	
}
