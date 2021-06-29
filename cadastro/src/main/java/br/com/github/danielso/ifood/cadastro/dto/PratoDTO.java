package br.com.github.danielso.ifood.cadastro.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class PratoDTO extends AuditDTO {

	@Schema(description = "O nome do prato", example = "Macarrão ao molhor vermelho")
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	@Length(min = 2, max = 300)
	private String nome;
	@Schema(description = "A desccrição do prato", example = "Feito com os melhores ingredientes")
	@NotEmpty(message = "O descrição não pode ser vazio")
	@NotNull(message = "O descrição não pode ser nulo")
	@Length(min = 5, max = 500)
	private String descricao;
	@Schema(description = "O Restaurante dono do prato", example = "Ver objeto RestauranteDTO", hidden = true)
	@JsonIgnore
	private RestauranteDTO restaurante;
	@Schema(description = "O valor do prato", example = "58.98")
	@NotNull(message = "O preço não pode ser nulo")
	@Min(value = 0, message = "Valor não pode ser menor que 0 (Zero)")
	@Max(value = 9999, message = "Valor não pode ser maior que 9999")
	private BigDecimal preco;

	public PratoDTO() {
	}

	public PratoDTO(String nome, String descricao, RestauranteDTO restaurante, BigDecimal preco) {
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
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public PratoDTO descricao(String descricao) {
		setDescricao(descricao);
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
		if (!(obj instanceof PratoDTO)) {
			return false;
		}
		PratoDTO other = (PratoDTO) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && Objects.equals(restaurante, other.restaurante);
	}

	@Override
	public String toString() {
		return "PratoDTO [nome=" + nome
				+ ", descricao=" + descricao + ", restaurante=" + restaurante + ", preco="
				+ preco + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao()
				+ ", getId()=" + getId() + "]";
	}

}
