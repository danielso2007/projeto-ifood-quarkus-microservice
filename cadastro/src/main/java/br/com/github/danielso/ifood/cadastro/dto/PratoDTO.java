package br.com.github.danielso.ifood.cadastro.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String desccricao;

    @ManyToOne
    private Restaurante restaurrante;

    private BigDecimal preco;

    public Prato() {
    }

    public Prato(Long id, String nome, String desccricao, Restaurante restaurrante, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.desccricao = desccricao;
        this.restaurrante = restaurrante;
        this.preco = preco;
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

    public String getDesccricao() {
        return this.desccricao;
    }

    public void setDesccricao(String desccricao) {
        this.desccricao = desccricao;
    }

    public Restaurante getRestaurrante() {
        return this.restaurrante;
    }

    public void setRestaurrante(Restaurante restaurrante) {
        this.restaurrante = restaurrante;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Prato id(Long id) {
        setId(id);
        return this;
    }

    public Prato nome(String nome) {
        setNome(nome);
        return this;
    }

    public Prato desccricao(String desccricao) {
        setDesccricao(desccricao);
        return this;
    }

    public Prato restaurrante(Restaurante restaurrante) {
        setRestaurrante(restaurrante);
        return this;
    }

    public Prato preco(BigDecimal preco) {
        setPreco(preco);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Prato)) {
            return false;
        }
        var prato = (Prato) o;
        return Objects.equals(id, prato.id) && Objects.equals(nome, prato.nome) && Objects.equals(desccricao, prato.desccricao) && Objects.equals(restaurrante, prato.restaurrante) && Objects.equals(preco, prato.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, desccricao, restaurrante, preco);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", desccricao='" + getDesccricao() + "'" +
            ", restaurrante='" + getRestaurrante() + "'" +
            ", preco='" + getPreco() + "'" +
            "}";
    }

}
