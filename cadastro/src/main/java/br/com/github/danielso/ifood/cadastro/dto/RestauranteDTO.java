package br.com.github.danielso.ifood.cadastro.dto;

import java.util.Objects;

public class RestauranteDTO extends AuditDTO {

	private String proprietario;
	private String cnpj;
	private String nome;
	private LocalizacaoDTO localizacao;

	public RestauranteDTO() {
	}

	public RestauranteDTO(String proprietario, String cnpj, String nome, LocalizacaoDTO localizacao) {
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

	public LocalizacaoDTO getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(LocalizacaoDTO localizacao) {
		this.localizacao = localizacao;
	}

	public RestauranteDTO proprietario(String proprietario) {
		setProprietario(proprietario);
		return this;
	}

	public RestauranteDTO cnpj(String cnpj) {
		setCnpj(cnpj);
		return this;
	}

	public RestauranteDTO nome(String nome) {
		setNome(nome);
		return this;
	}

	public RestauranteDTO localizacao(LocalizacaoDTO localizacao) {
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
		if (!(obj instanceof RestauranteDTO)) {
			return false;
		}
		RestauranteDTO other = (RestauranteDTO) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(localizacao, other.localizacao)
				&& Objects.equals(nome, other.nome) && Objects.equals(proprietario, other.proprietario);
	}

	@Override
	public String toString() {
		return "RestauranteDTO [proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome + ", localizacao="
				+ localizacao + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()="
				+ getDataAtualizacao() + ", getId()=" + getId() + "]";
	}

}
