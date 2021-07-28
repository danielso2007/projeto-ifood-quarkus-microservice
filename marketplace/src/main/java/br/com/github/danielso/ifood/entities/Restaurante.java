package br.com.github.danielso.ifood.entities;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Restaurante {

	private Long id;
	@NotEmpty(message = "O nome do proprietário não pode ser vazio")
	@NotNull(message = "O nome do proprietário não pode ser nulo")
	private String proprietario;
	@NotEmpty(message = "O CNPJ do proprietário não pode ser vazio")
	@NotNull(message = "O CNPJ do proprietário não pode ser nulo")
	@Min(value = 14, message = "CNPJ não pode ser menor que 14 caracteres")
	@Pattern(regexp = "^[0-9]{14}$", message = "CNPJ inválido")
	private String cnpj;
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;
	private Localizacao localizacao;
	private Set<Prato> pratos;
	private Date dataCriacao;
	private Date dataAtualizacao;

	public Restaurante() {
	}

	public Restaurante(Long id, String proprietario, String cnpj, String nome, Localizacao localizacao,
			Set<Prato> pratos, Date dataCriacao, Date dataAtualizacao) {
		this.id = id;
		this.proprietario = proprietario;
		this.cnpj = cnpj;
		this.nome = nome;
		this.localizacao = localizacao;
		this.pratos = pratos;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProprietario() {
		return this.proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Set<Prato> getPratos() {
		return this.pratos;
	}

	public void setPratos(Set<Prato> pratos) {
		this.pratos = pratos;
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

	public Restaurante id(Long id) {
		setId(id);
		return this;
	}

	public Restaurante proprietario(String proprietario) {
		setProprietario(proprietario);
		return this;
	}

	public Restaurante cnpj(String cnpj) {
		setCnpj(cnpj);
		return this;
	}

	public Restaurante nome(String nome) {
		setNome(nome);
		return this;
	}

	public Restaurante localizacao(Localizacao localizacao) {
		setLocalizacao(localizacao);
		return this;
	}

	public Restaurante pratos(Set<Prato> pratos) {
		setPratos(pratos);
		return this;
	}

	public Restaurante dataCriacao(Date dataCriacao) {
		setDataCriacao(dataCriacao);
		return this;
	}

	public Restaurante dataAtualizacao(Date dataAtualizacao) {
		setDataAtualizacao(dataAtualizacao);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, proprietario, cnpj, nome, localizacao, pratos, dataCriacao, dataAtualizacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Restaurante)) {
			return false;
		}
		Restaurante other = (Restaurante) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(nome, other.nome) && Objects.equals(proprietario, other.proprietario);
	}

	@Override
	public String toString() {
		return "Restaurante [getProprietario()=" + getProprietario() + ", getCnpj()=" + getCnpj() + ", getNome()="
				+ getNome() + ", getLocalizacao()=" + getLocalizacao() + ", getPratos()=" + getPratos()
				+ ", hashCode()=" + hashCode() + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()="
				+ getDataAtualizacao() + ", getId()=" + getId() + "]";
	}

}
