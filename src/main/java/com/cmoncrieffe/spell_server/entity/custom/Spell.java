package com.cmoncrieffe.spell_server.entity.custom;

import com.cmoncrieffe.spell_server.entity.Caster;
import com.cmoncrieffe.spell_server.entity.PrimaryEffect;
import com.cmoncrieffe.spell_server.entity.SpellLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "custom_spells")
@Getter
@Setter
public class Spell {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_caster_id")
    private Caster originCaster;

    @Column(name = "additional_caster_id_list", columnDefinition = "LONGTEXT", nullable = false)
    private String additionalCasterIdList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spell_level_id", nullable = false)
    private SpellLevel spellLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_effect_id", nullable = false)
    private PrimaryEffect primaryEffect;

    @Column(name = "secondary_effect_id_list", nullable = false)
    private String secondaryEffectIdList;

    @Column(name = "coda_id_list", nullable = false)
    private String codaIdList;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "id.spell", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EffectCustomization> spellEffectCustomizations;

    @OneToMany(mappedBy = "id.spell", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpellAlteration> spellAlterations;

    public Spell() {}

    public Spell(
            Caster originCaster, String additionalCasterIdList, SpellLevel spellLevel, PrimaryEffect primaryEffect,
            String secondaryEffectIdList, String codaIdList, String name, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.originCaster = originCaster;
        this.additionalCasterIdList = additionalCasterIdList;
        this.spellLevel = spellLevel;
        this.primaryEffect = primaryEffect;
        this.secondaryEffectIdList = secondaryEffectIdList;
        this.codaIdList = codaIdList;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "CustomSpell{" +
                "uuid='" + uuid + '\'' +
                ", originCaster=" + originCaster +
                ", additionalCasterIdList='" + additionalCasterIdList + '\'' +
                ", spellLevel=" + spellLevel +
                ", primaryEffect=" + primaryEffect +
                ", secondaryEffectIdList='" + secondaryEffectIdList + '\'' +
                ", codaIdList='" + codaIdList + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
