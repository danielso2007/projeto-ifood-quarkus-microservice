package br.com.github.danielso.ifood.cadastro.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prato")
public class Prato extends BaseAudit {

	private String nome;

	private String descricao;

	@ManyToOne
	private Restaurante restaurante;

	private BigDecimal preco;

	public Prato() {
	}

	public Prato(String nome, String desccricao, Restaurante restaurante, BigDecimal preco) {
		this.nome = nome;
		this.descricao = desccricao;
		this.restaurante = restaurante;
		this.preco = preco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesccricao() {
		return this.descricao;
	}

	public void setDesccricao(String desccricao) {
		this.descricao = desccricao;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Prato nome(String nome) {
		setNome(nome);
		return this;
	}

	public Prato desccricao(String desccricao) {
		setDesccricao(desccricao);
		return this;
	}

	public Prato restaurante(Restaurante restaurante) {
		setRestaurante(restaurante);
		return this;
	}

	public Prato preco(BigDecimal preco) {
		setPreco(preco);
		return this;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descricao, nome, preco, restaurante);
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
		if (!(obj instanceof Prato)) {
			return false;
		}
		Prato other = (Prato) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && Objects.equals(restaurante, other.restaurante);
	}

	@Override
	public String toString() {
		return "Prato [nome=" + nome + ", desccricao=" + descricao + ", restaurante=" + restaurante + ", preco="
				+ preco + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao()
				+ ", getId()=" + getId() + "]";
	}

}
