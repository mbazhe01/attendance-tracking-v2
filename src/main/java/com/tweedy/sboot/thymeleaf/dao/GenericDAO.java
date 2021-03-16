package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Address;

public interface GenericDAO {
	
	// entities -> Address, Phone, Email, Note
	public <T> List<T> getEntities(int theId, String entityName) throws Exception;
	
	public <T> void save(T t);
	
	public void delete(int theId, String entityName, String whereClauseColumn);
	
	public int findEmployeeByEntityId(int theId, String entityName, String whereClauseColumn) 
					throws Exception;
	
}
