package com.example.H2DATABASE.controller;

import com.example.H2DATABASE.model.Cennik;
import com.example.H2DATABASE.repository.CennikRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cennik")
public class CennikController {

    @Autowired
    private CennikRepository cennikRepository;

    @GetMapping
    public ResponseEntity<List<Cennik>> getAllCennik() {
        List<Cennik> cennikList = cennikRepository.findAll();
        return new ResponseEntity<>(cennikList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cennik> getCennikById(@PathVariable Long id) {
        Optional<Cennik> cennik = cennikRepository.findById(id);
        return cennik.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cennik> createCennik(@RequestBody Cennik cennik) {
        Cennik savedCennik = cennikRepository.save(cennik);
        return new ResponseEntity<>(savedCennik, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cennik> updateCennik(@PathVariable Long id, @RequestBody Cennik updatedCennik) {
        return cennikRepository.findById(id).map(cennik -> {
            cennik.setTypUslugi(updatedCennik.getTypUslugi());
            cennik.setCena(updatedCennik.getCena());
            Cennik savedCennik = cennikRepository.save(cennik);
            return new ResponseEntity<>(savedCennik, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCennik(@PathVariable Long id) {
        if (cennikRepository.existsById(id)) {
            cennikRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
