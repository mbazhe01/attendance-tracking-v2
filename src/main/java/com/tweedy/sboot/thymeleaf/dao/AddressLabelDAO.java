package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AddressLabel;
import com.tweedy.sboot.thymeleaf.entity.Department;

public interface AddressLabelDAO {

	public List<AddressLabel> getAddressLabels();
}
