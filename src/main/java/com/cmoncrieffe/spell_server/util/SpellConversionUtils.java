package com.cmoncrieffe.spell_server.util;

import com.cmoncrieffe.spell_server.entity.Caster;
import com.cmoncrieffe.spell_server.entity.PrimaryEffect;
import com.cmoncrieffe.spell_server.entity.SpellLevel;
import com.cmoncrieffe.spell_server.entity.custom.*;
import com.cmoncrieffe.spell_server.entity.dto.SpellAlterationDto;
import com.cmoncrieffe.spell_server.entity.dto.SpellDto;
import com.cmoncrieffe.spell_server.entity.dto.EffectCustomizationDto;
import com.cmoncrieffe.spell_server.repository.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpellConversionUtils {
    public static Spell getSpell(
            CasterRepository casterRepository,
            PrimaryEffectRepository primaryEffectRepository,
            SpellLevelRepository spellLevelRepository,
            SpellDto spellDto) {
        Caster originCaster = getOriginCaster(casterRepository, spellDto.getOriginCasterId());
        SpellLevel spellLevel = getSpellLevel(spellLevelRepository, spellDto.getSpellLevel());
        PrimaryEffect primaryEffect = getPrimaryEffect(primaryEffectRepository, spellDto.getPrimaryEffectId());

        Spell spell = new Spell(
                originCaster,
                spellDto.getAdditionalCasterIdList(),
                spellLevel,
                primaryEffect,
                spellDto.getSecondaryEffectIdList(),
                spellDto.getCodaIdList(),
                spellDto.getName(),
                spellDto.getDescription());

        List<EffectCustomization> effectCustomizations = getEffectCustomizations(
                spellDto.getSpellEffectCustomizations(), spell);
        spell.setSpellEffectCustomizations(effectCustomizations);

        List<SpellAlteration> spellAlterations = getSpellAlterations(spellDto.getCustomSpellBase(), spell);
        spell.setSpellAlterations(spellAlterations);

        return spell;
    }

    public static Spell updateSpell(
            CasterRepository casterRepository,
            EffectCustomizationRepository effectCustomizationRepository,
            PrimaryEffectRepository primaryEffectRepository,
            SpellRepository spellRepository,
            SpellLevelRepository spellLevelRepository,
            SpellDto spellDto) {
        Optional<Spell> repositorySpell = spellRepository.findById(spellDto.getUuid());
        if (repositorySpell.isEmpty())
            return null;

        Spell databaseSpell = repositorySpell.get();
        databaseSpell.setAdditionalCasterIdList(spellDto.getAdditionalCasterIdList());
        databaseSpell.setSecondaryEffectIdList(spellDto.getSecondaryEffectIdList());
        databaseSpell.setCodaIdList(spellDto.getCodaIdList());
        databaseSpell.setName(spellDto.getName());
        databaseSpell.setDescription(spellDto.getDescription());

        Caster originCaster = getOriginCaster(casterRepository, spellDto.getOriginCasterId());
        databaseSpell.setOriginCaster(originCaster);

        PrimaryEffect primaryEffect = getPrimaryEffect(primaryEffectRepository, spellDto.getPrimaryEffectId());
        if (primaryEffect == null)
            return null;
        databaseSpell.setPrimaryEffect(primaryEffect);

        SpellLevel spellLevel = getSpellLevel(spellLevelRepository, spellDto.getSpellLevel());
        if (spellLevel == null)
            return null;
        databaseSpell.setSpellLevel(spellLevel);

        databaseSpell.getSpellAlterations().get(0).setCost(spellDto.getCustomSpellBase().getCost());
        databaseSpell.getSpellAlterations().get(0).setCastingTime(spellDto.getCustomSpellBase().getCastingTime());
        databaseSpell.getSpellAlterations().get(0).setRange(spellDto.getCustomSpellBase().getRange());
        databaseSpell.getSpellAlterations().get(0).setTarget(spellDto.getCustomSpellBase().getTarget());
        databaseSpell.getSpellAlterations().get(0).setArea(spellDto.getCustomSpellBase().getArea());
        databaseSpell.getSpellAlterations().get(0).setAreaSize(spellDto.getCustomSpellBase().getAreaSize());
        databaseSpell.getSpellAlterations().get(0).setDuration(spellDto.getCustomSpellBase().getDuration());

        List<EffectCustomization> customizationsToRemove = new ArrayList<>();
        databaseSpell.getSpellEffectCustomizations().forEach(customization -> {
            Optional<EffectCustomizationDto> customizationOptional = Arrays
                    .stream(spellDto.getSpellEffectCustomizations())
                    .filter(effectCustomization -> effectCustomization.getEffectName().equals(customization.getId().getEffectName()))
                    .findFirst();

            if (customizationOptional.isEmpty())
                customizationsToRemove.add(customization);
            else {
                EffectCustomizationDto customizationDTO = customizationOptional.get();
                customization.setEffectData(customizationDTO.getEffectData());
            }
        });
        databaseSpell.getSpellEffectCustomizations().removeAll(customizationsToRemove);
        effectCustomizationRepository.deleteAll(customizationsToRemove);

        Arrays.stream(spellDto.getSpellEffectCustomizations()).forEach(customizationDTO -> {
            boolean exists = databaseSpell.getSpellEffectCustomizations()
                    .stream()
                    .anyMatch(effectCustomization -> effectCustomization.getId().getEffectName().equals(customizationDTO.getEffectName()));
            if (!exists) {
                EffectCustomization effectCustomization = new EffectCustomization(
                        new EffectCustomizationId(databaseSpell, customizationDTO.getEffectName()),
                        customizationDTO.getEffectData());
                databaseSpell.getSpellEffectCustomizations().add(effectCustomization);
            }
        });

        return databaseSpell;
    }

    public static SpellDto getSpellDTO(Spell spell) {
        SpellAlterationDto spellBaseDTO = getSpellAlterationDto(spell);
        List<EffectCustomizationDto> effectCustomizationDTOs = getEffectCustomizationDtos(spell);
        String originCasterUuid = spell.getOriginCaster() == null ? "" : spell.getOriginCaster().getUuid();

        return new SpellDto(
                spell.getUuid(),
                originCasterUuid,
                spell.getAdditionalCasterIdList(),
                spell.getSpellLevel().getSpellLevel(),
                spell.getPrimaryEffect().getId(),
                spell.getSecondaryEffectIdList(),
                spell.getCodaIdList(),
                spell.getName(),
                spell.getDescription(),
                effectCustomizationDTOs,
                spellBaseDTO);
    }

    private static Caster getOriginCaster(CasterRepository casterRepository, String originCasterId) {
        if (originCasterId.isEmpty())
            return null;

        Optional<Caster> caster =  casterRepository.findById(originCasterId);
        return caster.orElse(null);
    }

    private static SpellLevel getSpellLevel(SpellLevelRepository spellLevelRepository, int spellLevelId) {
        Optional<SpellLevel> spellLevel = spellLevelRepository.findById(spellLevelId);
        return spellLevel.orElse(null);
    }

    private static PrimaryEffect getPrimaryEffect(PrimaryEffectRepository primaryEffectRepository, int primaryEffectId) {
        Optional<PrimaryEffect> primaryEffect = primaryEffectRepository.findById(primaryEffectId);
        return primaryEffect.orElse(null);
    }

    private static SpellAlterationDto getSpellAlterationDto(Spell spell) {
        SpellAlteration spellBase = spell.getSpellAlterations().get(0);
        return new SpellAlterationDto(
                spellBase.getId().getSpell().getUuid(),
                spellBase.getCost(),
                spellBase.getCastingTime(),
                spellBase.getRange(),
                spellBase.getTarget(),
                spellBase.getArea(),
                spellBase.getAreaSize(),
                spellBase.getDuration());
    }

    private static List<SpellAlteration> getSpellAlterations(SpellAlterationDto spellAlterationDto, Spell spell) {
        List<SpellAlteration> spellAlterations = new ArrayList<>();
        spellAlterations.add(new SpellAlteration(
                new SpellAlterationId(spell),
                spellAlterationDto.getCost(),
                spellAlterationDto.getCastingTime(),
                spellAlterationDto.getRange(),
                spellAlterationDto.getTarget(),
                spellAlterationDto.getArea(),
                spellAlterationDto.getAreaSize(),
                spellAlterationDto.getDuration()));
        return spellAlterations;
    }

    private static List<EffectCustomizationDto> getEffectCustomizationDtos(Spell spell) {
        List<EffectCustomizationDto> effectCustomizationDtos = new ArrayList<>();

        spell.getSpellEffectCustomizations().forEach(effectCustomization -> {
            EffectCustomizationDto effectCustomizationDto = new EffectCustomizationDto(
                    effectCustomization.getId().getSpell().getUuid(),
                    effectCustomization.getId().getEffectName(),
                    effectCustomization.getEffectData());
            effectCustomizationDtos.add(effectCustomizationDto);
        });
        return effectCustomizationDtos;
    }

    private static List<EffectCustomization> getEffectCustomizations(EffectCustomizationDto[] effectCustomizationDtos, Spell spell) {
        List<EffectCustomization> spellEffectCustomizations = new ArrayList<>();

        Arrays.stream(effectCustomizationDtos).forEach(dto -> {
            EffectCustomization effectCustomization =  new EffectCustomization(
                    new EffectCustomizationId(spell, dto.getEffectName()), dto.getEffectData());
            spellEffectCustomizations.add(effectCustomization);
        });
        return spellEffectCustomizations;
    }

}
