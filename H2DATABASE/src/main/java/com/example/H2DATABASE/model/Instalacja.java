package com.example.H2DATABASE.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Instalacja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "adres")
    private String adres;

    @Column(name = "numer_routera")
    private String numerRoutera;

    @Column(name = "typ_uslugi")
    private String typUslugi;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    @OneToMany(mappedBy = "instalacja")
    private List<NaliczoneNaleznosci> naliczoneNaleznosci;

    @OneToMany(mappedBy = "instalacja")
    private List<DokonaneWplaty> dokonaneWplaty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNumerRoutera() {
        return numerRoutera;
    }

    public void setNumerRoutera(String numerRoutera) {
        this.numerRoutera = numerRoutera;
    }

    public String getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(String typUslugi) {
        this.typUslugi = typUslugi;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public List<NaliczoneNaleznosci> getNaliczoneNaleznosci() {
        return naliczoneNaleznosci;
    }

    public void setNaliczoneNaleznosci(List<NaliczoneNaleznosci> naliczoneNaleznosci) {
        this.naliczoneNaleznosci = naliczoneNaleznosci;
    }

    public List<DokonaneWplaty> getDokonaneWplaty() {
        return dokonaneWplaty;
    }

    public void setDokonaneWplaty(List<DokonaneWplaty> dokonaneWplaty) {
        this.dokonaneWplaty = dokonaneWplaty;
    }
}
