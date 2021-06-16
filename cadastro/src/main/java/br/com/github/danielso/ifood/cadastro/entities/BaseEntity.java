package br.com.github.danielso.ifood.cadastro.entities;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected BaseEntity() {
	}

	protected BaseEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public br.com.github.danielso.ifood.cadastro.entities.BaseEntity id(Long id) {
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
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + "]";
	}

}