package org.lessons.java.spring_la_mia_pizzeria_webapi.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerta")
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // pizza da cui dipendo
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "Inserisci un nome per la tua offerta")
    private String nomeOfferta;

    @NotNull(message = "Inserisci la data di inizio offerta")
    @FutureOrPresent(message = "L'offerta non puó cominciare nel passato")
    private LocalDate inizioOfferta;

    @NotNull(message = "Inserisci la data di fine offerta")
    @FutureOrPresent(message = "L'offerta non puó finire nel passato ")
    private LocalDate fineOfferta;

    @Lob
    private String dettagli;

    public String getNomeOfferta() {
        return this.nomeOfferta;
    }

    public void setNomeOfferta(String nomeOfferta) {
        this.nomeOfferta = nomeOfferta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getInizioOfferta() {
        return this.inizioOfferta;
    }

    public void setInizioOfferta(LocalDate inizioOfferta) {
        this.inizioOfferta = inizioOfferta;
    }

    public LocalDate getFineOfferta() {
        return this.fineOfferta;
    }

    public void setFineOfferta(LocalDate fineOfferta) {
        this.fineOfferta = fineOfferta;
    }

    public String getDettagli() {
        return this.dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

}
