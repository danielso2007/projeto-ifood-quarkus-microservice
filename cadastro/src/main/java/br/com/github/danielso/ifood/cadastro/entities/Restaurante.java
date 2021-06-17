package br.com.github.danielso.ifood.cadastro.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "restaurante")
public class Restaurante extends BaseAudit {

	@NotEmpty(message = "O nome do proprietário não pode ser vazio")
	@NotNull(message = "O nome do proprietário não pode ser nulo")
	@Length(min = 2, max = 300)
	@Column(length = 300, nullable = false)
	private String proprietario;
	@NotEmpty(message = "O CNPJ do proprietário não pode ser vazio")
	@NotNull(message = "O CNPJ do proprietário não pode ser nulo")
	@Min(value = 14, message = "CNPJ não pode ser menor que 14 caracteres")
	@Length(min = 14, max = 14, message = "CNPJ deve ter 14 números")
	@Pattern(regexp = "^[0-9]{14}$", message = "CNPJ inválido")
	@Column(length = 14, nullable = false)
	private String cnpj;
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	@Length(min = 2, max = 300)
	@Column(length = 300, nullable = false)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localizacao_id")
	private Localizacao localizacao;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurante_id")
	private List<Prato> pratos;

	public Restaurante() {
	}

	public Restaurante(String proprietario, String cnpj, String nome, Localizacao localizacao) {
		this.proprietario = proprietario;
		this.cnpj = cnpj;
		this.nome = nome;
		this.localizacao = localizacao;
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

	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
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

	@Override
	public int hashCode() {
		final var prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpj, localizacao, nome, proprietario);
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
		if (!(obj instanceof Restaurante)) {
			return false;
		}
		Restaurante other = (Restaurante) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(nome, other.nome) && Objects.equals(proprietario, other.proprietario);
	}

	@Override
	public String toString() {
		return "Restaurante [proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome + ", localizacao="
				+ localizacao + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()="
				+ getDataAtualizacao() + ", getId()=" + getId() + "]";
	}

}
