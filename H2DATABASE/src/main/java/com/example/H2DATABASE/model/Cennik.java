package com.example.H2DATABASE.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cennik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "typ_uslugi")
    private String typUslugi;

    @Column(name = "cena")
    private BigDecimal cena;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(String typUslugi) {
        this.typUslugi = typUslugi;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
