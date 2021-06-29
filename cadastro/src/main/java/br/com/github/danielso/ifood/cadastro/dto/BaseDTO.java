package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public abstract class BaseDTO {

	@Schema(description = "O identificador do registro", example = "123")
	private Long id;

	protected BaseDTO() {
	}

	protected BaseDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseDTO id(Long id) {
		setId(id);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseDTO)) {
			return false;
		}
		BaseDTO other = (BaseDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BaseEntityDTO [id=" + id + "]";
	}

}