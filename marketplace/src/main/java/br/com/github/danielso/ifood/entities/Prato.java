package br.com.github.danielso.ifood.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.StreamSupport;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.github.danielso.ifood.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.PreparedQuery;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

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
	private BigDecimal preco;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;

	public Prato() {
	}

	public Prato(Long id, String nome, String descricao, Restaurante restaurante, BigDecimal preco,
			LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.restaurante = restaurante;
		this.preco = preco;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
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

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDateTime getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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

	public Prato preco(BigDecimal preco) {
		setPreco(preco);
		return this;
	}

	public Prato dataCriacao(LocalDateTime dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public Prato dataAtualizacao(LocalDateTime dataAtualizacao) {
		setDataAtualizacao(dataAtualizacao);
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
		return "Prato [nome=" + nome + ", descricao=" + descricao + ", restaurante=" + restaurante + ", preco=" + preco
				+ ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()=" + getDataAtualizacao()
				+ ", getId()=" + getId() + "]";
	}

	public static Multi<PratoDTO> findAll(PgPool pgPool) {
		PreparedQuery<RowSet<Row>> preparedQuery = pgPool.preparedQuery("select * from prato_cliente");
		return preparedQuery.execute().onItem().transformToMulti(rowSet -> Multi.createFrom()
				.items(() -> StreamSupport.stream(rowSet.spliterator(), false))).onItem().transform(PratoDTO::from);
	}

}
