package com.technova.shopverse.shopverse_api.services;

import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<Product>> listAllProds();
    ResponseEntity<Product> getProdById(Long id) throws ProductNotFoundException;
    Product createProd(Product prod);
    void saveProd(Product prod);
    ResponseEntity<String> registerProd(Product prod) throws InvalidDataFromProductException;
    ResponseEntity<String> updateProd(Long id, Product updProd)
            throws InvalidDataFromProductException, ProductNotFoundException;
    ResponseEntity<Void> deleteProd(Long id) throws ProductNotFoundException;
}
