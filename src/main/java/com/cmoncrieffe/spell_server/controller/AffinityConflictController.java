package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.AffinityConflict;
import com.cmoncrieffe.spell_server.repository.AffinityConflictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/affinity-conflicts")
public class AffinityConflictController {
    private final AffinityConflictRepository affinityConflictRepository;

    @Autowired
    public AffinityConflictController(AffinityConflictRepository affinityConflictRepository) {
        this.affinityConflictRepository = affinityConflictRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<AffinityConflict>> getAffinityConflicts() {
        try {
            return ResponseEntity.ok(affinityConflictRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
