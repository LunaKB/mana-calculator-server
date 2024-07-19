package com.cmoncrieffe.spell_server.entity.custom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Embeddable
@Getter
@Setter
public class EffectCustomizationId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spell_id", referencedColumnName = "uuid", nullable = false)
    private Spell spell;

    @Column(name = "effect_name")
    private String effectName;

    public EffectCustomizationId() {
    }

    public EffectCustomizationId(Spell spell, String effectName) {
        this.spell = spell;
        this.effectName = effectName;
    }
}
