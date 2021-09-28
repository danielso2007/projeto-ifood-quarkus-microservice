package br.com.github.danielso.ifood.marketplace.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class PratoCarrinho {

	@NotEmpty(message = "O cliente não pode ser vazio")
	@NotNull(message = "O cliente não pode ser nulo")
	private String cliente;
	@NotEmpty(message = "O prato não pode ser vazio")
	@NotNull(message = "O prato não pode ser nulo")
	private Long prato;

	public PratoCarrinho() {
	}

	public PratoCarrinho(String cliente, Long prato) {
		this.cliente = cliente;
		this.prato = prato;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getPrato() {
		return this.prato;
	}

	public void setPrato(Long prato) {
		this.prato = prato;
	}

	public PratoCarrinho cliente(String cliente) {
		setCliente(cliente);
		return this;
	}

	public PratoCarrinho prato(Long prato) {
		setPrato(prato);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PratoCarrinho)) {
			return false;
		}
		PratoCarrinho pratoCarrinho = (PratoCarrinho) o;
		return Objects.equals(cliente, pratoCarrinho.cliente) && Objects.equals(prato, pratoCarrinho.prato);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, prato);
	}

	@Override
	public String toString() {
		return "{" + " usuario='" + getCliente() + "'" + ", prato='" + getPrato() + "'" + "}";
	}

	public static Uni<Long> save(PgPool client, String cliente, Long prato) {
		return client.preparedQuery("INSERT INTO prato_cliente (cliente, prato) VALUES ($1, $2) RETURNING (cliente)")
				.execute(Tuple.of(cliente, prato))

				.map(pgRowSet -> pgRowSet.iterator().next().getLong("cliente"));
	}

	public static Uni<List<PratoCarrinho>> findCarrinho(PgPool client, String cliente) {
		return client.preparedQuery("select * from prato_cliente where cliente = $1 ").execute(Tuple.of(cliente))
				.map(pgRowSet -> {
					List<PratoCarrinho> list = new ArrayList<>(pgRowSet.size());
					for (Row row : pgRowSet) {
						list.add(toPratoCarrinho(row));
					}
					return list;
				});
	}

	private static PratoCarrinho toPratoCarrinho(Row row) {
		PratoCarrinho pc = new PratoCarrinho();
		pc.cliente = row.getString("cliente");
		pc.prato = row.getLong("prato");
		return pc;
	}

	public static Uni<Boolean> delete(PgPool client, String cliente) {
		return client.preparedQuery("DELETE FROM prato_cliente WHERE cliente = $1").execute(Tuple.of(cliente))
				.map(pgRowSet -> pgRowSet.rowCount() == 1);

	}

}
