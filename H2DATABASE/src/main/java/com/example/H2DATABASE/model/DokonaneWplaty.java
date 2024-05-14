package com.example.H2DATABASE.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class DokonaneWplaty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "termin_wplaty")
    private LocalDate terminWplaty;

    @Column(name = "kwota_wplaty")
    private BigDecimal kwotaWplaty;

    @ManyToOne
    @JoinColumn(name = "instalacja_id")
    private Instalacja instalacja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTerminWplaty() {
        return terminWplaty;
    }

    public void setTerminWplaty(LocalDate terminWplaty) {
        this.terminWplaty = terminWplaty;
    }

    public BigDecimal getKwotaWplaty() {
        return kwotaWplaty;
    }

    public void setKwotaWplaty(BigDecimal kwotaWplaty) {
        this.kwotaWplaty = kwotaWplaty;
    }

    public Instalacja getInstalacja() {
        return instalacja;
    }

    public void setInstalacja(Instalacja instalacja) {
        this.instalacja = instalacja;
    }
}
