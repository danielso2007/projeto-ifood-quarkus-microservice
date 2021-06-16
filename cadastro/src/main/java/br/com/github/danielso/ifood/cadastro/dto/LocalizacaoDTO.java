package br.com.github.danielso.ifood.cadastro.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "localizacao")
public class Localizacao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    public Localizacao() {
    }

    public Localizacao(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Localizacao id(Long id) {
        setId(id);
        return this;
    }

    public Localizacao latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    public Localizacao longitude(Double longitude) {
        setLongitude(longitude);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Localizacao)) {
            return false;
        }
        var localizacao = (Localizacao) o;
        return Objects.equals(id, localizacao.id) && Objects.equals(latitude, localizacao.latitude) && Objects.equals(longitude, localizacao.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }

}
