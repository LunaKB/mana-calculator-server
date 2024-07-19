package com.cmoncrieffe.spell_server.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectCustomizationDto {
    private String uuid;
    private String effectName;
    private String effectData;

    public EffectCustomizationDto(String uuid, String effectName, String effectData) {
        this.uuid = uuid;
        this.effectName = effectName;
        this.effectData = effectData;
    }

    @Override
    public String toString() {
        return "SpellEffectCustomizationDTO{" +
                "uuid='" + uuid + '\'' +
                ", effectName='" + effectName + '\'' +
                ", effectData='" + effectData + '\'' +
                '}';
    }
}
