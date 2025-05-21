package com.technova.shopverse.shopverse_api.dtos;

import jakarta.validation.constraints.*;

public class ProductModifyDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String name;

    @NotNull(message = "La descripción no debe ser nulo")
    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Double price;

    @NotNull(message = "La categoría es obligatoria")
    private CategoryDTO category;

    public ProductModifyDTO(String name, String description, Double price, CategoryDTO category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoryDTO getCategoryDTO() {
        return category;
    }

    public void setCategoryDTO(CategoryDTO category) {
        this.category = category;

    }
}
