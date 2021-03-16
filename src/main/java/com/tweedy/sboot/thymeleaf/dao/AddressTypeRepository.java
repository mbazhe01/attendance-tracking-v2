package com.tweedy.sboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.AddressType;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Integer>{

}
