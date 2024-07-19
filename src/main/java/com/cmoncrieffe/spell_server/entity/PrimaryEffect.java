package com.cmoncrieffe.spell_server.entity;

import com.cmoncrieffe.spell_server.entity.custom.Spell;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "primary_effects")
@Getter
@Setter
public class PrimaryEffect {
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

    @JsonIgnore
    @OneToMany(mappedBy = "primaryEffect", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Spell> spell;

    public PrimaryEffect() {}

    @Override
    public String toString() {
        return "PrimaryEffect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aspect='" + aspect + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
