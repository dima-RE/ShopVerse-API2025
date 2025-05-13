package com.technova.shopverse.shopverse_api.services;

import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromCategoryException;
import com.technova.shopverse.shopverse_api.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> listAllCats();
    Category getCatById(Long id) throws CategoryNotFoundException;
    Category createCat(Category cat);
    void saveCat(Category cat);
    void registerCat(Category cat) throws InvalidDataFromCategoryException;
    ResponseEntity<String> updateCat(Long id, Category cat) throws CategoryNotFoundException, InvalidDataFromCategoryException;
    void deleteCat(Long id) throws CategoryNotFoundException;
}
