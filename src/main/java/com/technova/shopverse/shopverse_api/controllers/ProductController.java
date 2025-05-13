package com.technova.shopverse.shopverse_api.controllers;

import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.model.Product;
import com.technova.shopverse.shopverse_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    //private final Pro
    @Autowired
    private ProductService prodServ;

    @GetMapping
    public List<Product> getAllProducts() {
        return prodServ.listAllProds();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.getProdById(id);
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) throws InvalidDataFromProductException {
        prodServ.registerProd(product);
        return "Creado";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updProd)
            throws ProductNotFoundException, InvalidDataFromProductException {
        return prodServ.updateProd(id,updProd);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        prodServ.deleteProd(id);
    }


}
