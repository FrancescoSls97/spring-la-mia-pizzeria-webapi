package org.lessons.java.spring_la_mia_pizzeria_webapi.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci un nome per la tua pizza")
    private String name;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "La descrizione è obbligatoria")
    private String description;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "L'immagine è obbligatoria")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "Il prezzo è obbligatorio")
    @Positive(message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal price;

    // relazione tra una pizza e una o piú offerte --> OneToMany
    @OneToMany(mappedBy = "pizza")
    private List<Offerta> offerta;

    // relazione tra tante pizze e tanti ingredienti --> ManyToMany
    @ManyToMany
    @JoinTable(name = "ingrediente_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingrediente> ingredienti;

    // getters & setters

    public List<Ingrediente> getIngredienti() {
        return this.ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<Offerta> getOfferta() {
        return this.offerta;
    }

    public void setOfferta(List<Offerta> offerta) {
        this.offerta = offerta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.name, this.description, this.price);
    }
}
