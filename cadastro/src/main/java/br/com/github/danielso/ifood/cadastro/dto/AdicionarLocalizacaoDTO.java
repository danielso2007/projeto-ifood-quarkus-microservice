package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class AdicionarLocalizacaoDTO {

	@Schema(description = "Latitude da localização", example = "-22.9014599")
	@NotNull(message = "A latitude não pode ser nula")
	private Double latitude;
	@Schema(description = "Longitude da localização", example = "-43.1068623")
	@NotNull(message = "A longitude não pode ser nula")
	private Double longitude;

	public AdicionarLocalizacaoDTO() {
	}

	public AdicionarLocalizacaoDTO(Double latitude, Double longitude) {
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

	public AdicionarLocalizacaoDTO latitude(Double latitude) {
		setLatitude(latitude);
		return this;
	}

	public AdicionarLocalizacaoDTO longitude(Double longitude) {
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
		if (!(obj instanceof AdicionarLocalizacaoDTO)) {
			return false;
		}
		AdicionarLocalizacaoDTO other = (AdicionarLocalizacaoDTO) obj;
		return Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude);
	}

	@Override
	public String toString() {
		return "LocalizacaoDTO [latitude=" + latitude + ", longitude=" + longitude + ", getDataCriacao()=" + "]";
	}

}
