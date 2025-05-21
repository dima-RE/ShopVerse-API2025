package com.technova.shopverse.shopverse_api.dtos;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> productsNames;

    public CategoryDTO(Long id, String name, String description, List<String> productsNames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productsNames = productsNames;
    }

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

    public List<String> getProductsNames() {
        return productsNames;
    }

    public void setProductsNames(List<String> productsNames) {
        this.productsNames = productsNames;
    }
}
