package br.com.github.danielso.ifood.cadastro.commons;

import javax.validation.ConstraintValidatorContext;

public interface DTO {

	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		return true;
	}

}
