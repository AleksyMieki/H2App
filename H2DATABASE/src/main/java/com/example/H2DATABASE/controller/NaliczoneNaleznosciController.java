package com.example.H2DATABASE.controller;
import com.example.H2DATABASE.model.NaliczoneNaleznosci;
import com.example.H2DATABASE.repository.NaliczoneNaleznosciRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/naliczone_naleznosci")
public class NaliczoneNaleznosciController {

    private final NaliczoneNaleznosciRepository naliczoneNaleznosciRepository;

    public NaliczoneNaleznosciController(NaliczoneNaleznosciRepository naliczoneNaleznosciRepository) {
        this.naliczoneNaleznosciRepository = naliczoneNaleznosciRepository;
    }

    @GetMapping("/")
    public List<NaliczoneNaleznosci> getAllNaliczoneNaleznosci() {
        return naliczoneNaleznosciRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaliczoneNaleznosci> getNaliczoneNaleznosciById(@PathVariable(value = "id") Long id) {
        Optional<NaliczoneNaleznosci> naliczoneNaleznosci = naliczoneNaleznosciRepository.findById(id);
        if (naliczoneNaleznosci.isPresent()) {
            return ResponseEntity.ok().body(naliczoneNaleznosci.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public NaliczoneNaleznosci createNaliczoneNaleznosci(@Valid @RequestBody NaliczoneNaleznosci naliczoneNaleznosci) {
        return naliczoneNaleznosciRepository.save(naliczoneNaleznosci);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaliczoneNaleznosci> updateNaliczoneNaleznosci(@PathVariable(value = "id") Long id,
                                                                         @Valid @RequestBody NaliczoneNaleznosci naliczoneNaleznosciDetails) {
        Optional<NaliczoneNaleznosci> naliczoneNaleznosci = naliczoneNaleznosciRepository.findById(id);
        if (naliczoneNaleznosci.isPresent()) {
            NaliczoneNaleznosci naliczoneNaleznosciToUpdate = naliczoneNaleznosci.get();
            naliczoneNaleznosciToUpdate.setTerminPlatnosci(naliczoneNaleznosciDetails.getTerminPlatnosci());
            naliczoneNaleznosciToUpdate.setKwotaDoZaplaty(naliczoneNaleznosciDetails.getKwotaDoZaplaty());
            naliczoneNaleznosciToUpdate.setMiesiacNaliczenia(naliczoneNaleznosciDetails.getMiesiacNaliczenia());
            naliczoneNaleznosciToUpdate.setInstalacja(naliczoneNaleznosciDetails.getInstalacja());

            NaliczoneNaleznosci updatedNaliczoneNaleznosci = naliczoneNaleznosciRepository.save(naliczoneNaleznosciToUpdate);
            return ResponseEntity.ok(updatedNaliczoneNaleznosci);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNaliczoneNaleznosci(@PathVariable(value = "id") Long id) {
        Optional<NaliczoneNaleznosci> naliczoneNaleznosci = naliczoneNaleznosciRepository.findById(id);
        if (naliczoneNaleznosci.isPresent()) {
            naliczoneNaleznosciRepository.delete(naliczoneNaleznosci.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

