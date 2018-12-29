package com.bilin.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;

import com.bilin.bo.Student;
import com.bilin.bo.Tel;

public class ValidatorTest {
	
    public static void main(String[] args) {
        Student xiaoming = getBean();
        List<String> validate = validate(xiaoming);
        validate.forEach(row -> {
            System.out.println(row.toString());
        });

    }

    private static Student getBean() {
        Student bean = new Student();
        bean.setName(null);
        bean.setAddress("北京");
        bean.setBirthday(new Date());
        bean.setWeight(new BigDecimal(30));
        bean.setEmail("xiaogangfan163.com");
        List<Tel> tels = new ArrayList<Tel>();
        Tel tel1 = new Tel();
        tels.add(tel1);
        bean.setTels(tels);
        return bean;
    }

    public static <T> List<String> validate(T t) {
    	// 1:
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    	
    	//通过Validation#byProvider()来明确指定使用的是哪个供应商
    	
    	// 2: failFast：true  快速失败返回模式    false 普通模式
    	/*ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
    	        .configure()
    	        .failFast( false )
    	        .buildValidatorFactory();*/
    	
    	// 3: hibernate.validator.fail_fast：true  快速失败返回模式    false 普通模式
    	/*ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
    	        .configure()
    	        .addProperty( "hibernate.validator.fail_fast", "true" )
    	        .buildValidatorFactory();*/
    	
        Validator validator = validatorFactory.getValidator();
        
        
		/**
		 * Validator#validate() 对实体进行验证 
		 * Validator#validateProperty()对实体中某个属性进行验证
		 * Validator#validateValue()对实体中某个属性的值进行验证
		 */
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }
}
