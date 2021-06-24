package br.com.github.danielso.ifood.cadastro.commons.response;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import br.com.github.danielso.ifood.cadastro.commons.ConstraintViolationImpl;

public class ConstraintViolationResponse {

	private final List<ConstraintViolationImpl> errors = new ArrayList<>();

	private ConstraintViolationResponse(ConstraintViolationException exception) {
		exception.getConstraintViolations().forEach(error -> errors.add(ConstraintViolationImpl.of(error)));
	}

	public static ConstraintViolationResponse of(ConstraintViolationException exception) {
		return new ConstraintViolationResponse(exception);
	}

	public List<ConstraintViolationImpl> getErrors() {
		return errors;
	}

}
