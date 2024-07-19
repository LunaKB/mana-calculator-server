package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.custom.*;
import com.cmoncrieffe.spell_server.entity.dto.*;
import com.cmoncrieffe.spell_server.repository.*;
import com.cmoncrieffe.spell_server.util.SpellConversionUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/custom-spells")
public class CustomSpellController {
    private final SpellRepository spellRepository;
    private final CasterRepository casterRepository;
    private final SpellLevelRepository spellLevelRepository;
    private final PrimaryEffectRepository primaryEffectRepository;
    private final EffectCustomizationRepository effectCustomizationRepository;

    @Autowired
    public CustomSpellController(
            SpellRepository spellRepository,
            CasterRepository casterRepository,
            SpellLevelRepository spellLevelRepository,
            PrimaryEffectRepository primaryEffectRepository,
            EffectCustomizationRepository effectCustomizationRepository) {
        this.spellRepository = spellRepository;
        this.casterRepository = casterRepository;
        this.spellLevelRepository = spellLevelRepository;
        this.primaryEffectRepository = primaryEffectRepository;
        this.effectCustomizationRepository = effectCustomizationRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<SpellDto>> getAllCustomSpells() {
        try {
            List<Spell> spells = spellRepository.findAll();

            List<SpellDto> holder  = new ArrayList<>();
            spells.forEach(spell -> holder.add(SpellConversionUtils.getSpellDTO(spell)));

            return ResponseEntity.ok(holder);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<SpellDto> createCustomSpell(@RequestBody SpellDto spellDto) {
        try {
            Spell spell = SpellConversionUtils.getSpell(
                    casterRepository,
                    primaryEffectRepository,
                    spellLevelRepository,
                    spellDto);
            spellRepository.save(spell);

            SpellDto serverSpellDto = SpellConversionUtils.getSpellDTO(spell);
            return ResponseEntity.ok(serverSpellDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCustomSpell(@PathVariable("id") String id, @RequestBody SpellDto spellDto) {
        try {
            Spell spell = SpellConversionUtils.updateSpell(
                    casterRepository,
                    effectCustomizationRepository,
                    primaryEffectRepository,
                    spellRepository,
                    spellLevelRepository,
                    spellDto);
            if (spell == null)
                return ResponseEntity.notFound().build();

            spellRepository.save(spell);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomSpell(@PathVariable("id") String id) {
        try {
            Optional<Spell> repositorySpell = spellRepository.findById(id);
            if (repositorySpell.isPresent()) {
                spellRepository.delete(repositorySpell.get());
                return ResponseEntity.ok(true);
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
