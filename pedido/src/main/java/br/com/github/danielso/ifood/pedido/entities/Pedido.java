package br.com.github.danielso.ifood.pedido.entities;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "ifood_pedido", database = "ifood_pedido")
public class Pedido extends PanacheMongoEntity {

	@NotEmpty(message = "O cliente não pode ser vazio")
	@NotNull(message = "O cliente não pode ser nulo")
	private String cliente;
	private List<Prato> pratos;
	private Restaurante restaurante;
	@NotEmpty(message = "O entregador não pode ser vazio")
	@NotNull(message = "O entregador não pode ser nulo")
	private String entregador;
	private Localizacao localizacao;

	public Pedido(String cliente, List<Prato> pratos, Restaurante restaurante, String entregador,
			Localizacao localizacao) {
		this.cliente = cliente;
		this.pratos = pratos;
		this.restaurante = restaurante;
		this.entregador = entregador;
		this.localizacao = localizacao;
	}

	public Pedido() {
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getEntregador() {
		return entregador;
	}

	public void setEntregador(String entregador) {
		this.entregador = entregador;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, entregador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pedido)) {
			return false;
		}
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(entregador, other.entregador);
	}

	@Override
	public String toString() {
		return "Pedido [" + (cliente != null ? "cliente=" + cliente + ", " : "")
				+ (entregador != null ? "entregador=" + entregador + ", " : "")
				+ (localizacao != null ? "localizacao=" + localizacao + ", " : "") + (id != null ? "id=" + id : "")
				+ "]";
	}

}
