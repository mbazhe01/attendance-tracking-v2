package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class AddressLabelsWrapper {

	List<AddressLabel> addressLabels;

	public AddressLabelsWrapper(List<AddressLabel> addressLabels) {
		
		this.addressLabels = addressLabels;
	}

	public AddressLabelsWrapper() {
		
	}

	public List<AddressLabel> getAddressLabels() {
		return addressLabels;
	}

	public void setAddressLabels(List<AddressLabel> addressLabels) {
		this.addressLabels = addressLabels;
	}
	
	
	
	
}
