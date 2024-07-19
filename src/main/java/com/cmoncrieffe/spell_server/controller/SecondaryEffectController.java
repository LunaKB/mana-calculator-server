package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.SecondaryEffect;
import com.cmoncrieffe.spell_server.repository.SecondaryEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/secondary-effects")
public class SecondaryEffectController {
    private final SecondaryEffectRepository secondaryEffectRepository;

    @Autowired
    public SecondaryEffectController(SecondaryEffectRepository secondaryEffectRepository) {
        this.secondaryEffectRepository = secondaryEffectRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<SecondaryEffect>> getSecondaryEffects() {
        try {
            return ResponseEntity.ok(secondaryEffectRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
