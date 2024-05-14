package com.example.H2DATABASE.repository;

import com.example.H2DATABASE.model.Cennik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CennikRepository extends JpaRepository<Cennik, Long> {
}
