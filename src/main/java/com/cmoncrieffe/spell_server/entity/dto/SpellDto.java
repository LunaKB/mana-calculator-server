package com.cmoncrieffe.spell_server.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SpellDto {
    private String uuid;
    private String originCasterId;
    private String additionalCasterIdList;
    private Integer spellLevel;
    private Integer primaryEffectId;
    private String secondaryEffectIdList;
    private String codaIdList;
    private String name;
    private String description;
    private EffectCustomizationDto[] spellEffectCustomizations;
    private SpellAlterationDto customSpellBase;

    public SpellDto(
            String uuid, String originCasterId, String additionalCasterIdList, Integer spellLevel,
            Integer primaryEffectId, String secondaryEffectIdList, String codaIdList, String name, String description,
            List<EffectCustomizationDto> spellEffectCustomizations, SpellAlterationDto customSpellBase) {
        this.uuid = uuid;
        this.originCasterId = originCasterId;
        this.additionalCasterIdList = additionalCasterIdList;
        this.spellLevel = spellLevel;
        this.primaryEffectId = primaryEffectId;
        this.secondaryEffectIdList = secondaryEffectIdList;
        this.codaIdList = codaIdList;
        this.name = name;
        this.description = description;
        this.spellEffectCustomizations = spellEffectCustomizations.toArray(new EffectCustomizationDto[0]);
        this.customSpellBase = customSpellBase;
    }

    @Override
    public String toString() {
        return "CustomSpellDTO{" +
                "uuid='" + uuid + '\'' +
                ", originCasterId='" + originCasterId + '\'' +
                ", additionalCasterIdList='" + additionalCasterIdList + '\'' +
                ", spellLevel=" + spellLevel +
                ", primaryEffectId=" + primaryEffectId +
                ", secondaryEffectIdList='" + secondaryEffectIdList + '\'' +
                ", codaIdList='" + codaIdList + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", spellEffectCustomizations=" + Arrays.toString(spellEffectCustomizations) +
                ", customSpellBase=" + customSpellBase +
                '}';
    }
}
