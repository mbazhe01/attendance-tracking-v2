package com.tweedy.sboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
