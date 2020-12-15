package br.com.gritto.gritto4;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;

public abstract class DefaultSpringUnitTest<T> {
	
	private Validator validator;
	
	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public Set<ConstraintViolation<T>> validate(T object) {
		return validator.validate(object);
	}
	
	public void saveForError(T object) {
		if(!validate(object).isEmpty()) {
			throw new ValidationException();
		}
	}

}

