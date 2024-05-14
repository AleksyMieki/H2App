package com.example.H2DATABASE.controller;

import com.example.H2DATABASE.model.DokonaneWplaty;
import com.example.H2DATABASE.repository.DokonaneWplatyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dokonanewplaty")
public class DokonaneWplatyController {

    @Autowired
    private DokonaneWplatyRepository dokonaneWplatyRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DokonaneWplaty> getDokonaneWplatyById(@PathVariable(value = "id") Long dokonaneWplatyId) {
        Optional<DokonaneWplaty> dokonaneWplaty = dokonaneWplatyRepository.findById(dokonaneWplatyId);
        if (dokonaneWplaty.isPresent()) {
            return ResponseEntity.ok().body(dokonaneWplaty.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DokonaneWplaty> createDokonaneWplaty(@RequestBody DokonaneWplaty dokonaneWplaty) {
        DokonaneWplaty savedDokonaneWplaty = dokonaneWplatyRepository.save(dokonaneWplaty);
        return ResponseEntity.ok().body(savedDokonaneWplaty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DokonaneWplaty> updateDokonaneWplaty(@PathVariable(value = "id") Long dokonaneWplatyId, @RequestBody DokonaneWplaty dokonaneWplatyDetails) {
        Optional<DokonaneWplaty> dokonaneWplaty = dokonaneWplatyRepository.findById(dokonaneWplatyId);
        if (dokonaneWplaty.isPresent()) {
            DokonaneWplaty dokonaneWplatyToUpdate = dokonaneWplaty.get();
            dokonaneWplatyToUpdate.setTerminWplaty(dokonaneWplatyDetails.getTerminWplaty());
            dokonaneWplatyToUpdate.setKwotaWplaty(dokonaneWplatyDetails.getKwotaWplaty());
            dokonaneWplatyToUpdate.setInstalacja(dokonaneWplatyDetails.getInstalacja());

            DokonaneWplaty updatedDokonaneWplaty = dokonaneWplatyRepository.save(dokonaneWplatyToUpdate);
            return ResponseEntity.ok().body(updatedDokonaneWplaty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDokonaneWplaty(@PathVariable(value = "id") Long dokonaneWplatyId) {
        Optional<DokonaneWplaty> dokonaneWplaty = dokonaneWplatyRepository.findById(dokonaneWplatyId);
        if (dokonaneWplaty.isPresent()) {
            dokonaneWplatyRepository.delete(dokonaneWplaty.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<DokonaneWplaty> getAllDokonaneWplaty() {
        return dokonaneWplatyRepository.findAll();
    }
}
