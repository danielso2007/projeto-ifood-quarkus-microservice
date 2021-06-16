package br.com.github.danielso.ifood.cadastro.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class PratoDTO extends AuditDTO {

	private String nome;
	private String desccricao;
	private RestauranteDTO restaurante;
	private BigDecimal preco;

	public PratoDTO() {
	}

	public PratoDTO(String nome, String desccricao, RestauranteDTO restaurante, BigDecimal preco) {
		this.nome = nome;
		this.desccricao = desccricao;
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
		return this.desccricao;
	}

	public void setDesccricao(String desccricao) {
		this.desccricao = desccricao;
	}

	public RestauranteDTO getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(RestauranteDTO restaurante) {
		this.restaurante = restaurante;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public PratoDTO nome(String nome) {
		setNome(nome);
		return this;
	}

	public PratoDTO desccricao(String desccricao) {
		setDesccricao(desccricao);
		return this;
	}

	public PratoDTO restaurante(RestauranteDTO restaurante) {
		setRestaurante(restaurante);
		return this;
	}

	public PratoDTO preco(BigDecimal preco) {
		setPreco(preco);
		return this;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(desccricao, nome, preco, restaurante);
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
		if (!(obj instanceof PratoDTO)) {
			return false;
		}
		PratoDTO other = (PratoDTO) obj;
		return Objects.equals(desccricao, other.desccricao) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && Objects.equals(restaurante, other.restaurante);
	}

	@Override
	public String toString() {
		return "PratoDTO [nome=" + nome + ", desccricao=" + desccricao + ", restaurante=" + restaurante + ", preco="
				+ preco + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao()
				+ ", getId()=" + getId() + "]";
	}

}
