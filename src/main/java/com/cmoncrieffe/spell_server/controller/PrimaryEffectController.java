package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.PrimaryEffect;
import com.cmoncrieffe.spell_server.repository.PrimaryEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/primary-effects")
public class PrimaryEffectController {
    private final PrimaryEffectRepository primaryEffectRepository;

    @Autowired
    public PrimaryEffectController(PrimaryEffectRepository primaryEffectRepository) {
        this.primaryEffectRepository = primaryEffectRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<PrimaryEffect>> getPrimaryEffects() {
        try {
            return ResponseEntity.ok(primaryEffectRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
