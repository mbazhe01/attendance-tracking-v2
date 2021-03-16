package com.tweedy.sboot.thymeleaf.dao;


import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;

@Repository
public interface NoteRepository extends JpaRepository<NoteDev, Integer> {

	List<NoteDev> findAllNotesByEmployee(@Valid EmployeeDev employee);
	
}
