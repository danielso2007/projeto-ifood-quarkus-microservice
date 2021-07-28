package br.com.github.danielso.ifood.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.stream.StreamSupport;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.github.danielso.ifood.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.PreparedQuery;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

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
	private Date dataCriacao;
	private Date dataAtualizacao;

	public Prato() {
	}

	public Prato(Long id, String nome, String descricao, Restaurante restaurante, BigDecimal preco,
			Date dataCriacao, Date dataAtualizacao) {
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

	public Prato dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public Prato dataAtualizacao(Date dataAtualizacao) {
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
		PreparedQuery<RowSet<Row>> preparedQuery = pgPool.preparedQuery("SELECT * FROM prato");
		return unitToMulti(preparedQuery.execute());
	}

	public static Multi<PratoDTO> findAll(PgPool pgPool, Long idRestaurante) {
		PreparedQuery<RowSet<Row>> preparedQuery = pgPool.preparedQuery("SELECT * FROM prato WHERE prato.restaurante_id = $1 ORDER BY nome ASC");
		return unitToMulti(preparedQuery.execute(Tuple.of(idRestaurante)));
	}
	
	private static Multi<PratoDTO> unitToMulti(Uni<RowSet<Row>> uni) {
		return uni.onItem().transformToMulti(rowSet -> Multi.createFrom()
				.items(() -> StreamSupport.stream(rowSet.spliterator(), false))).onItem().transform(PratoDTO::from);
	}

}
