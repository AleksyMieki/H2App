package com.example.H2DATABASE.controller;

import com.example.H2DATABASE.model.Instalacja;
import com.example.H2DATABASE.repository.InstalacjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/instalacje")
public class InstalacjaController {

    @Autowired
    private InstalacjaRepository instalacjaRepository;

    @GetMapping("")
    public ResponseEntity<List<Instalacja>> getAllInstalacje() {
        List<Instalacja> instalacje = instalacjaRepository.findAll();
        return new ResponseEntity<>(instalacje, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instalacja> getInstalacjaById(@PathVariable(value = "id") Long id) {
        Optional<Instalacja> instalacja = instalacjaRepository.findById(id);
        if (instalacja.isPresent()) {
            return new ResponseEntity<>(instalacja.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Instalacja> createInstalacja(@RequestBody Instalacja instalacja) {
        Instalacja createdInstalacja = instalacjaRepository.save(instalacja);
        return new ResponseEntity<>(createdInstalacja, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instalacja> updateInstalacja(@PathVariable(value = "id") Long id, @RequestBody Instalacja instalacjaDetails) {
        Optional<Instalacja> optionalInstalacja = instalacjaRepository.findById(id);
        if (optionalInstalacja.isPresent()) {
            Instalacja instalacja = optionalInstalacja.get();
            instalacja.setAdres(instalacjaDetails.getAdres());
            instalacja.setNumerRoutera(instalacjaDetails.getNumerRoutera());
            instalacja.setTypUslugi(instalacjaDetails.getTypUslugi());
            instalacja.setKlient(instalacjaDetails.getKlient());
            Instalacja updatedInstalacja = instalacjaRepository.save(instalacja);
            return new ResponseEntity<>(updatedInstalacja, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInstalacja(@PathVariable(value = "id") Long id) {
        Optional<Instalacja> optionalInstalacja = instalacjaRepository.findById(id);
        if (optionalInstalacja.isPresent()) {
            Instalacja instalacja = optionalInstalacja.get();
            instalacjaRepository.delete(instalacja);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
