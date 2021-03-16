package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;


@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer>{

	List<Address> findAllAddressesByEmployee(EmployeeDev employee);
	
}
