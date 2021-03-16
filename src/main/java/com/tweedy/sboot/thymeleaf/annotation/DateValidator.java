package com.tweedy.sboot.thymeleaf.annotation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

	SimpleDateFormat sdfrmt1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat sdfrmt2 = new SimpleDateFormat("MM-dd-yyyy");
	SimpleDateFormat sdfrmt3 = new SimpleDateFormat("yyyy-MM-dd");

	public void initialize() {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean isValid = validateJavaDate(value, sdfrmt1);

		if (!isValid)
			isValid = validateJavaDate(value, sdfrmt2);

		if (!isValid)
			isValid = validateJavaDate(value, sdfrmt3);

		return isValid;
	}

	private boolean validateJavaDate(String strDate, SimpleDateFormat formatter) {
		
		Date javaDate = null;
		
		if (strDate == null)
			return true;
		
		/* Check if date is 'null' */
		if (strDate.trim().equals("")) {
			return true;
		} else {
			/*
			 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
			 */

			formatter.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				javaDate = formatter.parse(strDate);
				String tmp = javaDate.toString();
				if (javaDate == null)
					return false;
			}
			/* Date format is invalid */
			catch (Exception e) {

				return false;
			}
			/* Return true if date format is valid */
			return true;
		}
	}// eof

}
