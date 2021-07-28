package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Date;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public abstract class AuditDTO extends BaseDTO {

	
	@Schema(description = "A data de criação do registro.", example = "2021-06-17T02:52:29.479316")
	private Date dataCriacao;

	@Schema(description = "A data de atualização do registro.", example = "2021-06-17T02:52:29.479316")
	private Date dataAtualizacao;

	protected AuditDTO() {
	}

	protected AuditDTO(Date dataCriacao, Date dataAtualizacao) {
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
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

	public AuditDTO dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public AuditDTO dataAtualizacao(Date dataAtualizacao) {
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
