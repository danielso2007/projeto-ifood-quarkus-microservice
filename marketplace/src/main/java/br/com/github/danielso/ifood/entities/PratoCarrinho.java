package br.com.github.danielso.ifood;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PratoCarrinho {

	@NotEmpty(message = "O usuário não pode ser vazio")
	@NotNull(message = "O usuário não pode ser nulo")
	private String usuario;
	@NotEmpty(message = "O prato não pode ser vazio")
	@NotNull(message = "O prato não pode ser nulo")
	private Long prato;

	public PratoCarrinho() {
	}

	public PratoCarrinho(String usuario, Long prato) {
		this.usuario = usuario;
		this.prato = prato;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getPrato() {
		return this.prato;
	}

	public void setPrato(Long prato) {
		this.prato = prato;
	}

	public PratoCarrinho usuario(String usuario) {
		setUsuario(usuario);
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
		return Objects.equals(usuario, pratoCarrinho.usuario) && Objects.equals(prato, pratoCarrinho.prato);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario, prato);
	}

	@Override
	public String toString() {
		return "{" + " usuario='" + getUsuario() + "'" + ", prato='" + getPrato() + "'" + "}";
	}

}
