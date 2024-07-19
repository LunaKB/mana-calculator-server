package com.cmoncrieffe.spell_server.entity;

import com.cmoncrieffe.spell_server.entity.custom.Spell;
import com.cmoncrieffe.spell_server.entity.dto.CasterDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "casters")
@Getter
@Setter
public class Caster {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "mana_points", nullable = false)
    private Integer manaPoints;

    @Column(name = "mind", nullable = false)
    private Integer mind;

    @Column(name = "source", nullable = false)
    private Integer source;

    @Column(name = "will", nullable = false)
    private Integer will;

    @JsonIgnore
    @OneToMany(mappedBy = "originCaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Spell> spell;

    public Caster() {}

    public Caster(String name, Integer manaPoints, Integer mind, Integer source, Integer will) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.manaPoints = manaPoints;
        this.mind = mind;
        this.source = source;
        this.will = will;
    }

    public void update(CasterDto casterDTO) {
        setName(casterDTO.getName());
        setManaPoints(casterDTO.getManaPoints());
        setMind(casterDTO.getMind());
        setSource(casterDTO.getSource());
        setWill(casterDTO.getWill());
    }

    @Override
    public String toString() {
        return "Caster{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", manaPoints=" + manaPoints +
                ", mind=" + mind +
                ", source=" + source +
                ", will=" + will +
                '}';
    }
}
