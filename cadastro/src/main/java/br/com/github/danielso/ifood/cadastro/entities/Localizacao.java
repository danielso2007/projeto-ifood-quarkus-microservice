package br.com.github.danielso.ifood.cadastro.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "localizacao")
public class Localizacao extends BaseAudit {

	private Double latitude;
	private Double longitude;

	public Localizacao() {
	}

	public Localizacao(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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

	public Localizacao latitude(Double latitude) {
		setLatitude(latitude);
		return this;
	}

	public Localizacao longitude(Double longitude) {
		setLongitude(longitude);
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
