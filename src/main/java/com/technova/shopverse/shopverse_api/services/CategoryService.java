package com.technova.shopverse.shopverse_api.services;

import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromCategoryException;
import com.technova.shopverse.shopverse_api.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<Category>> listAllCats();
    ResponseEntity<Category> getCatById(Long id) throws CategoryNotFoundException;
    Category createCat(Category cat);
    void saveCat(Category cat);
    ResponseEntity<String> registerCat(Category cat) throws InvalidDataFromCategoryException;
    ResponseEntity<String> updateCat(Long id, Category cat) throws CategoryNotFoundException, InvalidDataFromCategoryException;
    ResponseEntity<Void> deleteCat(Long id) throws CategoryNotFoundException;
}
