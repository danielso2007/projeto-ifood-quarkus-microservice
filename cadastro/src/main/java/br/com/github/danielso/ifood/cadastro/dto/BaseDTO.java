package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Objects;

public abstract class BaseEntityDTO {

	private Long id;

	protected BaseEntityDTO() {
	}

	protected BaseEntityDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseEntityDTO id(Long id) {
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
		if (!(obj instanceof BaseEntityDTO)) {
			return false;
		}
		BaseEntityDTO other = (BaseEntityDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BaseEntityDTO [id=" + id + "]";
	}

}