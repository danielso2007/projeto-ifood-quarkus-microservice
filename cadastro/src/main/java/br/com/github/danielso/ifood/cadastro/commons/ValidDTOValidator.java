package br.com.github.danielso.ifood.cadastro.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDTOValidator implements ConstraintValidator<ValidDTO, DTO> {

	@Override
	public void initialize(ValidDTO constraintAnnotation) {
		// Construtor padrão.
	}

	@Override
	public boolean isValid(DTO dto, ConstraintValidatorContext context) {
		return dto.isValid(context);
	}

}
