package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {


	List<Phone> findAllPhonesByEmployee(EmployeeDev employee);
}
