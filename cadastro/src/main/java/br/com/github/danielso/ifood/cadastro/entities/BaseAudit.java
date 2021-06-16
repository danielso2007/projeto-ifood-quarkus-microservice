package br.com.github.danielso.ifood.cadastro.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseAudit extends BaseEntity {

	@Schema(description = "A data de criação do registro.", example = "2020-02-16 21:22:27")
	@CreationTimestamp
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Schema(description = "A data de atualização do registro.", example = "2020-02-16 21:22:27")
	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	protected BaseAudit() {
	}

	protected BaseAudit(Date dataCriacao, Date dataAtualizacao) {
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

	public BaseAudit dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public BaseAudit dataAtualizacao(Date dataAtualizacao) {
		setDataAtualizacao(dataAtualizacao);
		return this;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataAtualizacao, dataCriacao);
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
		if (!(obj instanceof BaseAudit)) {
			return false;
		}
		BaseAudit other = (BaseAudit) obj;
		return Objects.equals(dataAtualizacao, other.dataAtualizacao) && Objects.equals(dataCriacao, other.dataCriacao);
	}

	@Override
	public String toString() {
		return "BaseAudit [dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", getId()=" + getId()
				+ "]";
	}

}
