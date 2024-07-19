package com.cmoncrieffe.spell_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "codas")
@Getter
@Setter
public class Coda {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "aspect", nullable = false, updatable = false)
    private String aspect;

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false, updatable = false)
    private String description;

    @Column(name = "cost", nullable = false, updatable = false)
    private Integer cost;

    public Coda() {}

    @Override
    public String toString() {
        return "Coda{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aspect='" + aspect + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
