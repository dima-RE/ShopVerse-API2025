package com.technova.shopverse.shopverse_api.services;

import com.technova.shopverse.shopverse_api.dtos.ProductDTO;
import com.technova.shopverse.shopverse_api.dtos.ProductModifyDTO;
import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    //ResponseEntity<List<Product>> listAllProds();
    ResponseEntity<List<ProductDTO>> listAllProdDTOs();
    ResponseEntity<List<ProductDTO>> getByCategoryId(Long categoryId);
    //ResponseEntity<Product> getProdById(Long id) throws ProductNotFoundException;
    ResponseEntity<ProductDTO> getProdDTOById(Long id) throws ProductNotFoundException;
    //ResponseEntity<String> registerProd(Product prod) throws InvalidDataFromProductException, CategoryNotFoundException;
    ResponseEntity<String> registerProd(ProductModifyDTO prod) throws InvalidDataFromProductException, CategoryNotFoundException;
    //ResponseEntity<String> updateProd(Long id, ProductModifyDTO updProd) throws InvalidDataFromProductException, ProductNotFoundException, CategoryNotFoundException;
    ResponseEntity<String> updateProd(Long id, ProductModifyDTO updProd)
            throws InvalidDataFromProductException, ProductNotFoundException, CategoryNotFoundException;
    ResponseEntity<Void> deleteProd(Long id) throws ProductNotFoundException;
}
