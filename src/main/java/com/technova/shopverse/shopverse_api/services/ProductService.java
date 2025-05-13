package com.technova.shopverse.shopverse_api.services;

import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> listAllProds();
    Product getProdById(Long id) throws ProductNotFoundException;
    Product createProd(Product prod);
    void saveProd(Product prod);
    void registerProd(Product prod) throws InvalidDataFromProductException;
    ResponseEntity<String> updateProd(Long id, Product updProd)
            throws InvalidDataFromProductException, ProductNotFoundException;
    void deleteProd(Long id) throws ProductNotFoundException;
}
