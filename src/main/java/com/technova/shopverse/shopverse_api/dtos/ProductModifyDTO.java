package com.technova.shopverse.shopverse_api.dtos;

public class ProductModifyDTO {
    private String name;
    private String description;
    private Double price;
    private CategoryDTO categoryDTO;

    public ProductModifyDTO(String name, String description, Double price, CategoryDTO categoryDTO) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryDTO = categoryDTO;
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
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }
}
