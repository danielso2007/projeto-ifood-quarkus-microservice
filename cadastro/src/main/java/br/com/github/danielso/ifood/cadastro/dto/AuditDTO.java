package br.com.github.danielso.ifood.cadastro.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AuditDTO extends BaseDTO {

	
	@Schema(description = "A data de criação do registro.", example = "2021-06-17T02:52:29.479316")
	private LocalDateTime dataCriacao;

	@Schema(description = "A data de atualização do registro.", example = "2021-06-17T02:52:29.479316")
	private LocalDateTime dataAtualizacao;

	protected AuditDTO() {
	}

	protected AuditDTO(LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public LocalDateTime getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public AuditDTO dataCriacao(LocalDateTime dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public AuditDTO dataAtualizacao(LocalDateTime dataAtualizacao) {
		setDataAtualizacao(dataAtualizacao);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAtualizacao, dataCriacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AuditDTO)) {
			return false;
		}
		AuditDTO other = (AuditDTO) obj;
		return Objects.equals(dataAtualizacao, other.dataAtualizacao) && Objects.equals(dataCriacao, other.dataCriacao);
	}

	@Override
	public String toString() {
		return "BaseAuditDTO [dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", getId()="
				+ getId() + "]";
	}

}
