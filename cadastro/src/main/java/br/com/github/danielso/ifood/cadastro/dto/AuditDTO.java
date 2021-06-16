package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Date;
import java.util.Objects;

import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;

public abstract class BaseAuditDTO extends BaseEntity {

	private Date dataCriacao;

	private Date dataAtualizacao;

	protected BaseAuditDTO() {
	}

	protected BaseAuditDTO(Date dataCriacao, Date dataAtualizacao) {
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

	public BaseAuditDTO dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public BaseAuditDTO dataAtualizacao(Date dataAtualizacao) {
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
		if (!(obj instanceof BaseAuditDTO)) {
			return false;
		}
		BaseAuditDTO other = (BaseAuditDTO) obj;
		return Objects.equals(dataAtualizacao, other.dataAtualizacao) && Objects.equals(dataCriacao, other.dataCriacao);
	}

	@Override
	public String toString() {
		return "BaseAuditDTO [dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", getId()="
				+ getId() + "]";
	}

}
