package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.SpellLevel;
import com.cmoncrieffe.spell_server.repository.SpellLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/spell-levels")
public class SpellLevelController {
    private final SpellLevelRepository spellLevelRepository;

    @Autowired
    public SpellLevelController(SpellLevelRepository spellLevelRepository) {
        this.spellLevelRepository = spellLevelRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<SpellLevel>> getSpellLevels() {
        try {
            List<SpellLevel> spellLevels = spellLevelRepository.findAll();
            return ResponseEntity.ok(spellLevels);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
