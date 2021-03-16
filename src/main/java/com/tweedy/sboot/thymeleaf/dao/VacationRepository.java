package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

	List<Vacation> findAllByEmplId(int id);

}
