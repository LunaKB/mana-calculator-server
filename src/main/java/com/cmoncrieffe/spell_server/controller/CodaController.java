package com.cmoncrieffe.spell_server.controller;

import com.cmoncrieffe.spell_server.entity.Coda;
import com.cmoncrieffe.spell_server.repository.CodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/codas")
public class CodaController {
    private final CodaRepository codaRepository;

    @Autowired
    public CodaController(CodaRepository codaRepository) {
        this.codaRepository = codaRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Coda>> getCodas() {
        try {
            return ResponseEntity.ok(codaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
