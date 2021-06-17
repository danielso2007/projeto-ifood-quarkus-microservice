package br.com.github.danielso.ifood.cadastro.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "prato")
public class Prato extends BaseAudit {

	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	@Length(min = 2, max = 300)
	@Column(length = 300, nullable = false)
	private String nome;
	@NotEmpty(message = "O descrição não pode ser vazio")
	@NotNull(message = "O descrição não pode ser nulo")
	@Length(min = 5, max = 500)
	@Column(length = 500, nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	private BigDecimal preco;

	public Prato() {
	}

	public Prato(String nome, String descricao, Restaurante restaurante, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.restaurante = restaurante;
		this.preco = preco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Prato descricao(String descricao) {
		setDescricao(descricao);
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
		return "Prato [nome=" + nome
				+ ", descricao=" + descricao + ", restaurante=" + restaurante + ", preco="
				+ preco + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao()
				+ ", getId()=" + getId() + "]";
	}

}
