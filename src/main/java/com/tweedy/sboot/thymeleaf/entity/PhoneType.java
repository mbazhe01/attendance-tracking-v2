package com.tweedy.sboot.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_phone_type")
public class PhoneType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@Column(name="type_desc")
	private String typeDescr;
}
