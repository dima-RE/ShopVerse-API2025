package com.technova.shopverse.shopverse_api.dtos;

public class ProductModifyDTO {
    private String name;
    private String description;
    private Double price;
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
