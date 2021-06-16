package br.com.github.danielso.ifood.cadastro;

import br.com.github.danielso.ifood.cadastro.entities.Localizacao;

import java.util.Date;

public class RestauranteDTO {

    private Long id;
    private String proprietario;
    private String cnpj;
    private String nome;
    private Localizacao localizacao;

    public RestauranteDTO() {
    }

    public RestauranteDTO(Long id, String proprietario, String cnpj, String nome, Localizacao localizacao) {
        this.id = id;
        this.proprietario = proprietario;
        this.cnpj = cnpj;
        this.nome = nome;
        this.localizacao = localizacao;
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

    public RestauranteDTO id(Long id) {
        setId(id);
        return this;
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

    public RestauranteDTO localizacao(Localizacao localizacao) {
        setLocalizacao(localizacao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RestauranteDTO)) {
            return false;
        }
        RestauranteDTO restauranteDTO = (RestauranteDTO) o;
        return Objects.equals(id, restauranteDTO.id) && Objects.equals(proprietario, restauranteDTO.proprietario) && Objects.equals(cnpj, restauranteDTO.cnpj) && Objects.equals(nome, restauranteDTO.nome) && Objects.equals(localizacao, restauranteDTO.localizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proprietario, cnpj, nome, localizacao);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", proprietario='" + getProprietario() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", nome='" + getNome() + "'" +
            ", localizacao='" + getLocalizacao() + "'" +
            "}";
    }

}
