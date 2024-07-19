package com.cmoncrieffe.spell_server.entity.custom;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "custom_spell_effect_customizations")
@Getter
@Setter
public class EffectCustomization {
    @EmbeddedId
    private EffectCustomizationId id;

    @Column(name = "effect_data", nullable = false)
    private String effectData;

    public EffectCustomization() {}

    public EffectCustomization(EffectCustomizationId id, String effectData) {
        this.id = id;
        this.effectData = effectData;
    }

    @Override
    public String toString() {
        return "CustomSpellEffectCustomization{" +
                "id=" + id.toString() +
                ", effectData='" + effectData + '\'' +
                '}';
    }
}
