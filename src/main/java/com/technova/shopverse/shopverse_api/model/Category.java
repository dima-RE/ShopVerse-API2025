package com.technova.shopverse.shopverse_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Para generar el ID, no debe estar definido dentro del constructor.
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProduct(Product product) {
        this.products.add(product);
    }
}
