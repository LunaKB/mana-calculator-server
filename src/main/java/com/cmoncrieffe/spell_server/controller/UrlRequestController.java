package com.cmoncrieffe.spell_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UrlRequestController {
    @GetMapping("/")
    public ResponseEntity<Boolean> getLive() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
