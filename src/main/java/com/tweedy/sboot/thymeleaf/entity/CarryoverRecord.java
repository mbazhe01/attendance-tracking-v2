package com.tweedy.sboot.thymeleaf.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * CarryoverRecord class - allocate unused vacation days from prior year as carry over to current year
 * 
 * @author mikeba
 *
 */
@Entity
@Table(name = "tb_vacation_carryover")
@Data
public class CarryoverRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="empl_id")
	private int emplId;
	
	@Column(name="vacation_year")
	private int vacationYear;
	
	@Column(name="carryover_days_from_last_year")
	private Double carryOverDaysFromLastYear;
	
	private String description;
	
	@Column(name="created_at", updatable=false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at", updatable=true)
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name="updated_user")
	private String updatedUser;

	public CarryoverRecord(int emplId) {
		
		this.emplId = emplId;
	}

	public CarryoverRecord() {
		
	}
	
	@Override
    public boolean equals(Object o){
        if(o instanceof CarryoverRecord){
             CarryoverRecord r = (CarryoverRecord) o;
             return this.emplId==r.getEmplId()&&
            		 this.vacationYear==r.getVacationYear();
        } else
             return false;
    }
	
}
