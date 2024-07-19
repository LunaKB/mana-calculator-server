package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.SpellBase;
import com.cmoncrieffe.spell_server.repository.SpellBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/spell-bases")
public class SpellBaseController {
    private final SpellBaseRepository spellBaseRepository;

    @Autowired
    public SpellBaseController(SpellBaseRepository spellBaseRepository) {
        this.spellBaseRepository = spellBaseRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<SpellBase>> getSpellBases() {
        try {
            return ResponseEntity.ok(spellBaseRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
