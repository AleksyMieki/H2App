package com.example.H2DATABASE.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class NaliczoneNaleznosci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "termin_platnosci")
    private LocalDate terminPlatnosci;

    @Column(name = "kwota_do_zaplaty")
    private BigDecimal kwotaDoZaplaty;

    @Column(name = "miesiac_naliczenia")
    private LocalDate miesiacNaliczenia;

    @ManyToOne
    @JoinColumn(name = "instalacja_id")
    private Instalacja instalacja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTerminPlatnosci() {
        return terminPlatnosci;
    }

    public void setTerminPlatnosci(LocalDate terminPlatnosci) {
        this.terminPlatnosci = terminPlatnosci;
    }

    public BigDecimal getKwotaDoZaplaty() {
        return kwotaDoZaplaty;
    }

    public void setKwotaDoZaplaty(BigDecimal kwotaDoZaplaty) {
        this.kwotaDoZaplaty = kwotaDoZaplaty;
    }

    public LocalDate getMiesiacNaliczenia() {
        return miesiacNaliczenia;
    }

    public void setMiesiacNaliczenia(LocalDate miesiacNaliczenia) {
        this.miesiacNaliczenia = miesiacNaliczenia;
    }

    public Instalacja getInstalacja() {
        return instalacja;
    }

    public void setInstalacja(Instalacja instalacja) {
        this.instalacja = instalacja;
    }
}
