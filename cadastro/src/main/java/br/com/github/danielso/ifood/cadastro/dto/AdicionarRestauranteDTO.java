package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class AdicionarRestauranteDTO {

	@Schema(description = "O nome do proprietário do Restaurante", example = "Daniel Oliveira")
	@NotEmpty(message = "O nome do proprietário não pode ser vazio")
	@NotNull(message = "O nome do proprietário não pode ser nulo")
	@Length(min = 2, max = 300)
	private String proprietario;
	@Schema(description = "CNPJ do Restaurante (sem pontuação)", example = "76115833000160")
	@NotEmpty(message = "O CNPJ do proprietário não pode ser vazio")
	@NotNull(message = "O CNPJ do proprietário não pode ser nulo")
	@CNPJ
	private String cnpj;
	@Schema(description = "O nome do Restaurante", example = "Outback")
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser nulo")
	@Length(min = 2, max = 300)
	private String nome;
	@Schema(description = "A localização do Restaurante")
	@NotNull(message = "A localizacao deve ser informada")
	private AdicionarLocalizacaoDTO localizacao;

	public AdicionarRestauranteDTO() {
	}

	public AdicionarRestauranteDTO(String proprietario, String cnpj, String nome, AdicionarLocalizacaoDTO localizacao) {
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

	public AdicionarLocalizacaoDTO getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(AdicionarLocalizacaoDTO localizacao) {
		this.localizacao = localizacao;
	}

	public AdicionarRestauranteDTO proprietario(String proprietario) {
		setProprietario(proprietario);
		return this;
	}

	public AdicionarRestauranteDTO cnpj(String cnpj) {
		setCnpj(cnpj);
		return this;
	}

	public AdicionarRestauranteDTO nome(String nome) {
		setNome(nome);
		return this;
	}

	public AdicionarRestauranteDTO localizacao(AdicionarLocalizacaoDTO localizacao) {
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
		if (!(obj instanceof AdicionarRestauranteDTO)) {
			return false;
		}
		AdicionarRestauranteDTO other = (AdicionarRestauranteDTO) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(nome, other.nome) && Objects.equals(proprietario, other.proprietario);
	}

	@Override
	public String toString() {
		return "AdicionarRestauranteDTO [proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome
				+ ", localizacao=" + localizacao + "]";
	}

}
