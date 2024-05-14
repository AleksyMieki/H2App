package com.example.H2DATABASE.controller;

import com.example.H2DATABASE.model.Klient;
import com.example.H2DATABASE.repository.KlientRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KlientController {

    @Autowired
    private KlientRepository klientRepository;

    @GetMapping("/klients")
    public List<Klient> getAllKlients() {
        return klientRepository.findAll();
    }

    @GetMapping("klients/{id}")
    public Optional<Klient> getKlientById(@PathVariable Long id) {
        return klientRepository.findById(id);
    }

    @PostMapping("/klients")
    public ResponseEntity<Klient> createKlient(@RequestBody Klient klient) {
        Klient savedKlient = klientRepository.save(klient);
        return new ResponseEntity<>(savedKlient, HttpStatus.CREATED);
    }


}