package br.com.github.danielso.ifood.pedido.entities;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import io.quarkus.runtime.annotations.IgnoreProperty;

public class Localizacao {

	private Long id;
	@NotNull(message = "A latitude não pode ser nula")
	private Double latitude;
	@NotNull(message = "A longitude não pode ser nula")
	private Double longitude;
	private Restaurante restaurante;
	@IgnoreProperty
	private Date dataCriacao;
	@IgnoreProperty
	private Date dataAtualizacao;

	public Localizacao() {
	}

	public Localizacao(Long id, Double latitude, Double longitude, Restaurante restaurante, Date dataCriacao,
			Date dataAtualizacao) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.restaurante = restaurante;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Localizacao id(Long id) {
		setId(id);
		return this;
	}

	public Localizacao latitude(Double latitude) {
		setLatitude(latitude);
		return this;
	}

	public Localizacao longitude(Double longitude) {
		setLongitude(longitude);
		return this;
	}

	public Localizacao restaurante(Restaurante restaurante) {
		setRestaurante(restaurante);
		return this;
	}

	public Localizacao dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public Localizacao dataAtualizacao(Date dataAtualizacao) {
		setDataAtualizacao(dataAtualizacao);
		return this;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(latitude, longitude);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Localizacao)) {
			return false;
		}
		Localizacao other = (Localizacao) obj;
		return Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude);
	}

	@Override
	public String toString() {
		return "Localizacao [latitude=" + latitude + ", longitude=" + longitude + ", getDataCriacao()="
				+ getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao() + ", getId()=" + getId() + "]";
	}

}
