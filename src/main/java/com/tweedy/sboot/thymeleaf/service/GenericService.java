package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

public interface GenericService {
	
	
	// find all entities (Address or Phone or Note) for an employee
	public List<?> findAll(int theId, String entityName) throws Exception;
			
	// save changes made to entity
	public <T> void save(T t);
			
	// delete an entity
	public void delete(int theId, String entityName, String whereClauseColumn);
			
	// get employee by entity id
	public int getEmployeeByEntityId (int theId, String entityName, String whereClauseColumn)
	      throws Exception;
}
