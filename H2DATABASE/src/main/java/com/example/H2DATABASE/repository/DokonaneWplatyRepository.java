package com.example.H2DATABASE.repository;

import com.example.H2DATABASE.model.DokonaneWplaty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokonaneWplatyRepository extends JpaRepository<DokonaneWplaty, Long> {
}
