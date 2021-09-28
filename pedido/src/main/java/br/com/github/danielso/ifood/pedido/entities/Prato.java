package br.com.github.danielso.ifood.pedido.entities;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.Decimal128;

public class Prato {

	private Long id;
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;
	@NotEmpty(message = "O descrição não pode ser vazio")
	@NotNull(message = "O descrição não pode ser nulo")
	private String descricao;
	private Restaurante restaurante;
	@NotNull(message = "O preço não pode ser nulo")
	@Min(value = 0, message = "Valor não pode ser menor que 0 (Zero)")
	@Max(value = 9999, message = "Valor não pode ser maior que 9999")
	private Decimal128 preco;

	public Prato() {
	}

	public Prato(Long id, String nome, String descricao, Restaurante restaurante, Decimal128 preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.restaurante = restaurante;
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

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Decimal128 getPreco() {
		return this.preco;
	}

	public void setPreco(Decimal128 preco) {
		this.preco = preco;
	}

	public Prato id(Long id) {
		setId(id);
		return this;
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

	public Prato preco(Decimal128 preco) {
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
		return "Prato [" + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (descricao != null ? "descricao=" + descricao + ", " : "") + (preco != null ? "preco=" + preco : "")
				+ "]";
	}

}
