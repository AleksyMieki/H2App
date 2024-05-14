package com.example.H2DATABASE.repository;

import com.example.H2DATABASE.model.Instalacja;
import com.example.H2DATABASE.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalacjaRepository extends JpaRepository<Instalacja, Long> {
}
