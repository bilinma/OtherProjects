package com.bilin.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bilin.bo.Person;

public class ValidPersonNumberValidator implements ConstraintValidator<ValidPersonNumber, Person>{
	 
	@Override
	public void initialize(ValidPersonNumber constraintAnnotation) {
		
	}
 
	@Override
	public boolean isValid(Person value, ConstraintValidatorContext context) {
		if(null == value){
			return true;
		}
		boolean isValid = value.getNumber()<=100;
		if(!isValid){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Person类的总人不不能大于100").addConstraintViolation();
		}
		return isValid;
	}
 
}
