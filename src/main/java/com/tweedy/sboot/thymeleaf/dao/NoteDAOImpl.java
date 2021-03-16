package com.tweedy.sboot.thymeleaf.dao;

import javax.persistence.EntityManager;

public class NoteDAOImpl extends GenericDAOImpl {

	public NoteDAOImpl(EntityManager theEntityManager) {
		super(theEntityManager);
	}

}
