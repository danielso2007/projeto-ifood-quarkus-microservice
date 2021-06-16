package br.com.github.danielso.ifood.cadastro.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Restaurante extends BaseAudit {

	private String proprietario;

	private String cnpj;

	private String nome;

	@ManyToOne
	private Localizacao localizacao;

	public Restaurante() {
	}

	public Restaurante(String proprietario, String cnpj, String nome, Localizacao localizacao) {
		this.proprietario = proprietario;
		this.cnpj = cnpj;
		this.nome = nome;
		this.localizacao = localizacao;
	}

	public String getProprietario() {
		return this.proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Restaurante proprietario(String proprietario) {
		setProprietario(proprietario);
		return this;
	}

	public Restaurante cnpj(String cnpj) {
		setCnpj(cnpj);
		return this;
	}

	public Restaurante nome(String nome) {
		setNome(nome);
		return this;
	}

	public Restaurante localizacao(Localizacao localizacao) {
		setLocalizacao(localizacao);
		return this;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpj, localizacao, nome, proprietario);
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
		if (!(obj instanceof Restaurante)) {
			return false;
		}
		Restaurante other = (Restaurante) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(nome, other.nome) && Objects.equals(proprietario, other.proprietario);
	}

	@Override
	public String toString() {
		return "Restaurante [proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome + ", localizacao="
				+ localizacao + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()="
				+ getDataAtualizacao() + ", getId()=" + getId() + "]";
	}

}
