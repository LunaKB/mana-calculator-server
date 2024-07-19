package com.cmoncrieffe.spell_server.entity;

import com.cmoncrieffe.spell_server.entity.custom.Spell;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "effective_spell_level")
@Getter
@Setter
public class SpellLevel {
    @Id
    @Column(name = "spell_level")
    private Integer spellLevel;

    @Column(name = "character_level", updatable = false)
    private String characterLevel;

    @Column(name = "single_damage", updatable = false)
    private Integer singleDamage;

    @Column(name = "multiple_damage", updatable = false)
    private Integer multipleDamage;

    @JsonIgnore
    @OneToMany(mappedBy = "spellLevel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Spell> spell;

    public SpellLevel() { }

    @Override
    public String toString() {
        return "SpellLevel{" +
                "spellLevel=" + spellLevel +
                ", characterLevel='" + characterLevel + '\'' +
                ", singleDamage=" + singleDamage +
                ", multipleDamage=" + multipleDamage +
                '}';
    }
}
