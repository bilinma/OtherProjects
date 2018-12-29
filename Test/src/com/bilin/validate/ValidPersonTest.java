package com.bilin.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.bilin.bo.Person;

public class ValidPersonTest {

	public static void main(String[] args) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Person person = new Person();
		person.setNumber(110);
		try {
			Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
	        for (ConstraintViolation<Person> constraintViolation : constraintViolations) {
	            System.out.println(constraintViolation.getMessage());
	        }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
}
