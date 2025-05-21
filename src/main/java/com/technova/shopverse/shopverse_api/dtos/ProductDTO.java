package com.technova.shopverse.shopverse_api.dtos;

/*public record ProductDTO (
    Long id,
    String name,
    String description,
    Double price
) {
//public record ProductsDTO (name = ) {
    //Genera un objeto de forma rapida, como GETTER
    //No genera SETTERS, ya que es un Objeto Inmutable.
    //public ProductDTO createPerson(@RequestBody NewProductDTO prodDTO) {
    //    ProductDTO proDto = new Product(proDTO.name());
    //}
}*/

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String categoryName;

    public ProductDTO(Long id, String name, String description, Double price, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}