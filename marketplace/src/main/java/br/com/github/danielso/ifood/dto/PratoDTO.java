package br.com.github.danielso.ifood.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.vertx.mutiny.sqlclient.Row;

public class PratoDTO {

	private Long id;
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;
	@NotEmpty(message = "O descrição não pode ser vazio")
	@NotNull(message = "O descrição não pode ser nulo")
	private String descricao;
	@NotNull(message = "O preço não pode ser nulo")
	@Min(value = 0, message = "Valor não pode ser menor que 0 (Zero)")
	@Max(value = 9999, message = "Valor não pode ser maior que 9999")
	private BigDecimal preco;

	public PratoDTO() {
	}

	public PratoDTO(Long id, String nome, String descricao, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public PratoDTO id(Long id) {
		setId(id);
		return this;
	}

	public PratoDTO nome(String nome) {
		setNome(nome);
		return this;
	}

	public PratoDTO descricao(String descricao) {
		setDescricao(descricao);
		return this;
	}

	public PratoDTO preco(BigDecimal preco) {
		setPreco(preco);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PratoDTO)) {
			return false;
		}
		PratoDTO pratoDTO = (PratoDTO) o;
		return Objects.equals(id, pratoDTO.id) && Objects.equals(nome, pratoDTO.nome)
				&& Objects.equals(descricao, pratoDTO.descricao) && Objects.equals(preco, pratoDTO.preco);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, descricao, preco);
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", descricao='" + getDescricao() + "'"
				+ ", preco='" + getPreco() + "'" + "}";
	}

	public static PratoDTO from(Row row) {
		PratoDTO dto = new PratoDTO();
		return dto.id(213L).nome("Nome").descricao("descrição").preco(BigDecimal.valueOf(233D));
	}
	
}
