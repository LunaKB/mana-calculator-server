package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.Caster;
import com.cmoncrieffe.spell_server.entity.custom.Spell;
import com.cmoncrieffe.spell_server.entity.dto.CasterDto;
import com.cmoncrieffe.spell_server.repository.CasterRepository;
import com.cmoncrieffe.spell_server.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/casters")
public class CasterController {
    private final CasterRepository casterRepository;
    private final SpellRepository spellRepository;

    @Autowired
    public CasterController(CasterRepository casterRepository, SpellRepository spellRepository) {
        this.casterRepository = casterRepository;
        this.spellRepository = spellRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Caster>> getAll() {
        try {
            return ResponseEntity.ok(casterRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Caster> create(@RequestBody CasterDto req) {
        try {
            Caster caster = new Caster(req.getName(), req.getManaPoints(), req.getMind(), req.getSource(), req.getWill());
            return ResponseEntity.ok(casterRepository.save(caster));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caster> update(@PathVariable String id, @RequestBody CasterDto req) {
        Optional<Caster> optionalCaster = casterRepository.findById(id);
        if (optionalCaster.isPresent()) {
            Caster caster = optionalCaster.get();
            caster.update((req));
            return ResponseEntity.ok(casterRepository.save(caster));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Caster> optionalCaster = casterRepository.findById(id);
        if (optionalCaster.isPresent()) {
            Caster caster = optionalCaster.get();

            List<Spell> spells = spellRepository.findAllByOriginCaster(caster);
            spells.forEach(spell -> {
                spell.setOriginCaster(null);
                spellRepository.save(spell);
            });

            casterRepository.delete(caster);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
