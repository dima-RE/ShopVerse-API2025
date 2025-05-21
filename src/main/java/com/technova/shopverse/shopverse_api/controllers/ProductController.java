package com.technova.shopverse.shopverse_api.controllers;

import com.technova.shopverse.shopverse_api.dtos.ProductDTO;
import com.technova.shopverse.shopverse_api.dtos.ProductModifyDTO;
import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
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

    /*@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return prodServ.listAllProds();
    }*/

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductsWithCategories() {
        return prodServ.listAllProdDTOs();
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategoryId(@PathVariable Long categoryId) {
        return prodServ.getByCategoryId(categoryId);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.getProdById(id);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.getProdDTOById(id);
    }

    @PostMapping
    public ResponseEntity<String> createProd(@RequestBody ProductModifyDTO product) throws InvalidDataFromProductException, CategoryNotFoundException {
        return prodServ.registerProd(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductModifyDTO updProd)
            throws ProductNotFoundException, InvalidDataFromProductException, CategoryNotFoundException {
        return prodServ.updateProd(id,updProd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.deleteProd(id);
    }


}
